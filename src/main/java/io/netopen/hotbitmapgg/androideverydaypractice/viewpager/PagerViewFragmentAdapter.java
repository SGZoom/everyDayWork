package io.netopen.hotbitmapgg.androideverydaypractice.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gzoom on 2016/9/10.
 */
public class PagerViewFragmentAdapter extends FragmentPagerAdapter implements CardAdapter{
    List<CardFragment>list = new ArrayList<>();
    float baseElevation;
    public PagerViewFragmentAdapter(FragmentManager fm,float elevetion)
    {
        super(fm);
        baseElevation=elevetion;
        for(int i=0;i<5;i++)
        {
            list.add(new CardFragment());
        }
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public CardView getCardView(int position) {
        return list.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container,position);
        list.set(position,(CardFragment) fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View)object);
        list.set(position,null);
//        super.destroyItem(container, position, object);
    }
}
