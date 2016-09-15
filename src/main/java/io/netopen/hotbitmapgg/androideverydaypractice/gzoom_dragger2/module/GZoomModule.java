package io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.module;

import android.content.Context;


import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.ActivityScope;

/**
 * Created by gzoom on 2016/9/12.
 */
@Module
public class GZoomModule {
    private Context context;
    public GZoomModule(Context c)
    {
        this.context=c;
    }
    @Provides
    @ActivityScope
    Context providesContext()
    {
        return context;
    }

    @Provides
    @ActivityScope
    List<String> providesDatas()
    {
        List<String>datas=  new ArrayList<>();
        for(int i=0;i<25;i++)
        {
            datas.add("第"+i+"次说许家榆好帅");
        }
        return datas;
    }
}
