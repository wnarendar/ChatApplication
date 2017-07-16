package com.example.narendar.chatapplication;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.narendar.chatapplication.databinding.ActivityMainBinding;
import com.example.narendar.chatapplication.db.queries.InsertQueries;
import com.example.narendar.chatapplication.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private List<Message> messageList = new ArrayList<Message>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });


    }

    private void sendMessage() {
        StringBuilder sbMsg = new StringBuilder(mBinding.edtEnter.getText().toString());
        if (!TextUtils.isEmpty(sbMsg.toString())) {
            setLabel(sbMsg.toString(), "sender");
            setLabel(sbMsg.reverse().toString(), "receiver");
            mBinding.edtEnter.setText("");
            Message message = new Message();
            message.setSenderMessage(sbMsg.toString());
            message.setReceiverMessage(sbMsg.reverse().toString());
            messageList.add(message);
            mBinding.scrollbar.scrollTo(0, mBinding.llMessageBar.getHeight());

        } else {
            Toast.makeText(MainActivity.this, "Enter some text to send!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setLabel(String strMsg, String strAlignment) {
        TextView textView = new TextView(MainActivity.this);
        textView.setText(strMsg);
        textView.setTextSize(20);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if ("sender".equalsIgnoreCase(strAlignment))
            layoutParams.gravity = Gravity.RIGHT;

        textView.setLayoutParams(layoutParams);
        mBinding.llMessageBar.addView(textView);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        showConfirmDialog();
        try {
            InsertQueries.insertMessages(MainActivity.this, messageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
