package com.example.narendar.chatapplication.db.tables;

/**
 * Created by Naren Wadhwa on 7/15/2017.
 */

public class MESSAGE_TBL {

    private static final String TAG = MESSAGE_TBL.class.getSimpleName();
    public static final String TABLE_NAME = "MESSAGE_TABLE";
    public static final String SENDER_MSG = "SENDER_MSG";
    public static final String RECEIVER_MSG = "RECEIVER_MSG";

    private static final String MESSAGE_TBL_CREATE =
            "create table " + TABLE_NAME +
                    "(" + SENDER_MSG + " text," +
                    RECEIVER_MSG + " text );";

    private static final String MESSAGE_TBL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static String createTable() {
        return MESSAGE_TBL_CREATE;
    }

    public static String dropTable() {
        return MESSAGE_TBL_DROP;
    }
}
