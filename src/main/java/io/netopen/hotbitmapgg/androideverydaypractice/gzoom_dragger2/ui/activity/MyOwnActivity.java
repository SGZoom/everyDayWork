package io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.components.DaggerGZoomCompent;
import io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.module.GZoomModule;
import io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.ui.adapter.GZoomAdapter;

/**
 * Created by gzoom on 2016/9/12.
 */
public class MyOwnActivity extends AppCompatActivity{

    @Bind(R.id.gzoom_main_recycleview)
    RecyclerView recyclerView;

    @Inject
    GZoomAdapter adapter;

    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gzoom_main);
        ButterKnife.bind(this);
        DaggerGZoomCompent.builder().gZoomModule(new GZoomModule(this)).build().inject(this);
        recyclerView.setAdapter(adapter);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
    }
}
