package io.netopen.hotbitmapgg.androideverydaypractice.viewpager;

import android.support.v7.widget.CardView;

/**
 * Created by gzoom on 2016/9/9.
 */
public interface CardAdapter {
    /**最大阴影倍数*/
    int MAX_ELEVATION_FACTOR=8;
    CardView getCardView(int position);
    int getCount();
    float getBaseElevation();
}
