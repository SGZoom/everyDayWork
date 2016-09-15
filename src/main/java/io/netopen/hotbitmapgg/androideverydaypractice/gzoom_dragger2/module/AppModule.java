package io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.app.MyApplication;

/**
 * Created by gzoom on 2016/9/12.
 */
@Module
public class AppModule {
    private MyApplication myApplication;
    private AppModule(MyApplication application)
    {
        myApplication=application;
    }

    @Provides
    @Singleton
    Application providesApplication()
    {
        return  myApplication;
    }

    @Provides
    @Singleton
    Context providesContext()
    {
        return myApplication;

    }


}
