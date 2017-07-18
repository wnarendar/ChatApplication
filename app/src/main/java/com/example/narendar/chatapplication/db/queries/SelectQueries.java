package com.example.narendar.chatapplication.db.queries;

import android.content.Context;
import android.database.Cursor;

import com.example.narendar.chatapplication.db.DatabaseHandler;
import com.example.narendar.chatapplication.db.tables.MESSAGE_TBL;
import com.example.narendar.chatapplication.model.Message;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Naren Wadhwa on 7/18/2017.
 */

public class SelectQueries {

    public static Observable<ArrayList<Message>> getMessages(Context context) {
        DatabaseHandler db = new DatabaseHandler(context);
        Cursor cursor = db.getDB().query(MESSAGE_TBL.TABLE_NAME, null, null, null, null, null, null);

        int count = cursor.getCount();
        ArrayList<Message> messagesList = new ArrayList<>();
        if (count > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                Message message = new Message();
                message.setMessage(cursor.getString(cursor.getColumnIndex(MESSAGE_TBL.MESSAGE)));
                message.setIsSender(cursor.getString(cursor.getColumnIndex(MESSAGE_TBL.IS_SENDER)));
                messagesList.add(message);
                cursor.moveToNext();
            }

            cursor.close();
        }
        return Observable.just(messagesList);
    }
}
