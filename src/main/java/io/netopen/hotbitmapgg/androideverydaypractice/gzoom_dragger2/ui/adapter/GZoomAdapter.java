package io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import javax.inject.Inject;

import io.netopen.hotbitmapgg.androideverydaypractice.R;

/**
 * Created by gzoom on 2016/9/12.
 */
public class GZoomAdapter extends RecyclerView.Adapter<MViewHolder>{
    List<String>datas;
    LayoutInflater layoutInflater;

    @Inject
    public GZoomAdapter(Context context,List<String>Datas)
    {
        layoutInflater=LayoutInflater.from(context);
        datas=Datas;
    }
    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.gzoom_item,parent,false);
        return new MViewHolder(v);
//        return null;/
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        holder.textView.setText(datas.get(position)+position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
