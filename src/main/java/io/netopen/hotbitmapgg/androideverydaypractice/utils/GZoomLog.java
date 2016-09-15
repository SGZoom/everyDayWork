package io.netopen.hotbitmapgg.androideverydaypractice.utils;

import android.util.Log;

/**
 * Created by gzoom on 2016/9/10.
 * 这个是方便log打印的，调了颜色比较顺眼
 */
public class GZoomLog {
    public static void LogRed(String name ,String value)
    {
        Log.e(name,value);
    }
    public static void LogYellow(String name ,String value)
    {
        Log.w(name,value);
    }
    public static void LogBlue(String name ,String value)
    {
        Log.d(name,value);
    }
    public static void LogPurple(String name ,String value)
    {
        Log.v(name,value);
    }
}
