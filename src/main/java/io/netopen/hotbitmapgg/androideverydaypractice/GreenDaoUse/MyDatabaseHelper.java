package io.netopen.hotbitmapgg.androideverydaypractice.GreenDaoUse;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gzoom on 2016/9/6.
 * 这种是正常情况下的数据库使用助手，写下来有助记忆
 * 使用方法：按照正常情况完成创建，
 * 然后getWriteableDatabase就行了
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_BOOK="create table book ("+
            "id integer primary key autoincrement"
            +"author text"
            +"price real"
            +"pages integer,"
            +"name text)";
    private Context contextl;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
        this.contextl=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
