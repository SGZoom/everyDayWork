package io.netopen.hotbitmapgg.androideverydaypractice.GreenDaoUse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.everydaypratic.DaoMaster;
import com.everydaypratic.DaoSession;

/**
 * Created by gzoom on 2016/9/6.
 */
public class GreenDaoImpl {

    /*默认的DaoMaster.OpenHelper在碰到数据库升级的时候会删
    除旧的表来创建新的表，这样就会导致旧表的数据全部丢失了，所以一定要封装DaoMaster.OpenHelper来实现数据库升级*/

    /**初始化数据库*/
    public static void initDatabase(Context context)
    {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"databaseName",null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        DaoSession session= daoMaster.newSession();

    }
    /**一般的数据库写法*/
    



}
