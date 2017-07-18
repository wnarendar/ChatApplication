package com.example.narendar.chatapplication;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.narendar.chatapplication.databinding.ActivityMainBinding;
import com.example.narendar.chatapplication.db.queries.InsertQueries;
import com.example.narendar.chatapplication.db.queries.SelectQueries;
import com.example.narendar.chatapplication.model.Message;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private ArrayList<Message> messageList = new ArrayList<Message>();
    private ArrayList<Message> tempList = new ArrayList<Message>();
    private MessageAdapter messageAdapter;
    public static final String SENDER = "sender";
    public static final String RECEIVER = "receiver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        messageAdapter = new MessageAdapter(MainActivity.this, messageList);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mBinding.recyclerView.setAdapter(messageAdapter);

        getMessagesFromDB();

        mBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

    }

    private void sendMessage() {
        StringBuilder sbMsg = new StringBuilder(mBinding.edtEnter.getText().toString());
        if (!TextUtils.isEmpty(sbMsg.toString())) {
            mBinding.edtEnter.setText("");

            Message senderMsg = new Message();
            senderMsg.setMessage(sbMsg.toString());
            senderMsg.setIsSender(SENDER);
            messageList.add(senderMsg);
            tempList.add(senderMsg);

            Message receiverMsg = new Message();
            receiverMsg.setMessage(sbMsg.reverse().toString());
            receiverMsg.setIsSender(RECEIVER);
            messageList.add(receiverMsg);
            tempList.add(receiverMsg);

            messageAdapter.notifyDataSetChanged();
            mBinding.recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);

        } else {
            Toast.makeText(MainActivity.this, "Enter some text to send!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        showConfirmDialog();
        storeMessagesInDB();
    }

    private void showConfirmDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        alertDialog.show();
    }

    private void storeMessagesInDB() {
        InsertQueries.insertMessages(MainActivity.this, tempList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {

                    }
                });
    }

    private void getMessagesFromDB() {
        SelectQueries.getMessages(MainActivity.this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Message>>() {
                    @Override
                    public void accept(@NonNull ArrayList<Message> messages) throws Exception {
                        if (messages.size() > 0) {
                            messageList.addAll(messages);
                            messageAdapter.notifyDataSetChanged();
                            mBinding.recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                        }
                    }
                });
    }
}
