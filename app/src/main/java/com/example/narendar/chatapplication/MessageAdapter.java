package com.example.narendar.chatapplication;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.narendar.chatapplication.databinding.MessageItemBinding;
import com.example.narendar.chatapplication.model.Message;

import java.util.ArrayList;

/**
 * Created by Naren Wadhwa on 7/18/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Message> mMessageArr;
    private MessageItemBinding itemBinding;

    public MessageAdapter(Context context, ArrayList<Message> messageArr) {
        this.mContext = context;
        this.mMessageArr = messageArr;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.message_item, parent, false);
        return new MyViewHolder(itemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Message message = mMessageArr.get(position);
        if (MainActivity.SENDER.equalsIgnoreCase(message.getIsSender())) {
            itemBinding.tvSender.setText(message.getMessage());
            itemBinding.tvSender.setVisibility(View.VISIBLE);
            itemBinding.tvReceiver.setVisibility(View.GONE);
        } else {
            itemBinding.tvReceiver.setText(message.getMessage());
            itemBinding.tvReceiver.setVisibility(View.VISIBLE);
            itemBinding.tvSender.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mMessageArr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
