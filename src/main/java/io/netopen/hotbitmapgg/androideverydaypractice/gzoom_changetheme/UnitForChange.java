package io.netopen.hotbitmapgg.androideverydaypractice.gzoom_changetheme;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by gzoom on 2016/9/13.
 */
public class UnitForChange {
    public  static void changeTheme(AppCompatActivity activity, int mode)
    {
        activity.getDelegate().setLocalNightMode(mode);
        activity.recreate();
    }
}
