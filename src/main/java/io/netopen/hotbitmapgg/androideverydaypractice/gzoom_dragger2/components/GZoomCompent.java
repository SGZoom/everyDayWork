package io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.components;


import dagger.Component;
import io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.ActivityScope;
import io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.module.GZoomModule;
import io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.ui.activity.MyOwnActivity;

/**
 * Created by gzoom on 2016/9/12.
 */
@ActivityScope
@Component(modules = {GZoomModule.class})
public interface GZoomCompent {
    void inject(MyOwnActivity act);
}
