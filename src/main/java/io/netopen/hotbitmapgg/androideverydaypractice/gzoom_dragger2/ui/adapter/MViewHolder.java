package io.netopen.hotbitmapgg.androideverydaypractice.gzoom_dragger2.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import butterknife.Bind;
import butterknife.ButterKnife;
import io.netopen.hotbitmapgg.androideverydaypractice.R;

/**
 * Created by gzoom on 2016/9/12.
 */
public class MViewHolder extends RecyclerView.ViewHolder{
    @Bind(R.id.gzoom_item_textview)
    TextView textView;
    public MViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
