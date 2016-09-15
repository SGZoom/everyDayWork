package io.netopen.hotbitmapgg.androideverydaypractice.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.utils.GZoomLog;

/**
 * Created by gzoom on 2016/9/10.
 */
public class PagerViewAdapter extends PagerAdapter implements CardAdapter{
    List<CardView>lists=new ArrayList<CardView>();
    float baseElevation;
    String LogName;
    public PagerViewAdapter(int n)
    {
        for(int i=0;i<n;i++)
        {
            lists.add(null);
        }
        LogName=this.getClass().getName();
    }
    @Override
    public CardView getCardView(int position) {
        return lists.get(position);
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.viewpager_card,container,false);
        container.addView(view);

        CardView cardView = (CardView) view.findViewById(R.id.pager_cardview);
        if(baseElevation==0)
        {
            if(cardView!=null) {
                baseElevation = cardView.getCardElevation();
            }else{
                baseElevation=10;
            }
        }
        cardView.setMaxCardElevation(baseElevation*MAX_ELEVATION_FACTOR);
        lists.set(position,cardView);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
        lists.set(position,null);
        GZoomLog.LogYellow(LogName,"destory "+position);
    }
}
