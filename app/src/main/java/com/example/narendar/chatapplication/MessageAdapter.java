package com.example.narendar.chatapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.narendar.chatapplication.model.Message;

import java.util.ArrayList;

/**
 * Created by Naren Wadhwa on 7/18/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Message> mMessageArr;

    public MessageAdapter(Context context, ArrayList<Message> messageArr) {
        this.mContext = context;
        this.mMessageArr = messageArr;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Message message = mMessageArr.get(position);
        if (MainActivity.SENDER.equalsIgnoreCase(message.getIsSender())) {
            holder.tv_sender.setText(message.getMessage());
            holder.tv_sender.setVisibility(View.VISIBLE);
            holder.tv_receiver.setVisibility(View.GONE);
        } else {
            holder.tv_receiver.setText(message.getMessage());
            holder.tv_receiver.setVisibility(View.VISIBLE);
            holder.tv_sender.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mMessageArr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_receiver, tv_sender;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_receiver = (TextView) itemView.findViewById(R.id.tv_receiver);
            tv_sender = (TextView) itemView.findViewById(R.id.tv_sender);
        }
    }
}
