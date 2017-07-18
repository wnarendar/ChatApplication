package com.example.narendar.chatapplication.db.queries;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.narendar.chatapplication.model.Message;
import com.example.narendar.chatapplication.db.DatabaseHandler;
import com.example.narendar.chatapplication.db.tables.MESSAGE_TBL;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Naren Wadhwa on 7/15/2017.
 */

public class InsertQueries {

    private static final String TAG = InsertQueries.class.getSimpleName();

    public static Observable<Integer> insertMessages(Context context, List<Message> messagesArr) {
        if (messagesArr.size() > 0) {
            Log.d(TAG, "insertMessages");
            DatabaseHandler db = new DatabaseHandler(context);
            long retVal = -1;
            ContentValues values = null;
            for (Message msg : messagesArr) {
                values = new ContentValues();
                values.put(MESSAGE_TBL.MESSAGE, msg.getMessage());
                values.put(MESSAGE_TBL.IS_SENDER, msg.getIsSender());
                retVal = db.getDB().insert(MESSAGE_TBL.TABLE_NAME, null, values);
            }

            Log.d(TAG, "insertMessages retVal:" + retVal);
        }
        return Observable.just(1);
    }

}
