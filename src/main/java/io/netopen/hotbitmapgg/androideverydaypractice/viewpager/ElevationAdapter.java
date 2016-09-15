package io.netopen.hotbitmapgg.androideverydaypractice.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

import io.netopen.hotbitmapgg.androideverydaypractice.utils.GZoomLog;

/**
 * Created by gzoom on 2016/9/9.
 */
public class ElevationAdapter implements ViewPager.PageTransformer,ViewPager
            .OnPageChangeListener {

    float lastPosition;
    CardAdapter cardAdapter;

    public ElevationAdapter(CardAdapter cardAdapter,ViewPager viewPager)
    {
        this.cardAdapter=cardAdapter;
        viewPager.addOnPageChangeListener(this);
    }

    /**positionOffset左边为0右边为1，比较好理解*/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        boolean isLeft=lastPosition>positionOffset;
        CardView cardView = cardAdapter.getCardView(position);
        GZoomLog.LogYellow("onPageScrolled","position:"+position+"   offset:"+positionOffset);
        int nextPosition;
        float trueOffset;
        /**失去焦点的定位*/
        int loseIndex;
        /**即将上位的定位*/
        int nextIndex;
        float baseElevation = cardAdapter.getBaseElevation();
        //这里取名lose和next其实不太准确，一开始理解的不准确，这里的lose是渐渐出现的那个
        //next是渐渐消失的那个
        if(isLeft)
        {
            loseIndex=position+1;
            nextIndex=position;
            nextPosition=position;
            trueOffset=1-positionOffset;
        }else{
            loseIndex=position;
            nextIndex=position+1;
            nextPosition=position+1;
            trueOffset=positionOffset;
        }
        int count=cardAdapter.getCount()-1;
        if(nextIndex>count||loseIndex>count)
        {
            return;
        }
        CardView currentCard = cardAdapter.getCardView(loseIndex);
        if(currentCard!=null)
        {
            currentCard.setScaleX((float)(1+0.1*(1-trueOffset)));
            currentCard.setScaleY((float)(1+0.1*(1-trueOffset)));

            currentCard.setCardElevation(baseElevation+baseElevation*7*(1-trueOffset));
        }

        CardView nextCard = cardAdapter.getCardView(nextIndex);
        if(nextCard!=null)
        {
            nextCard.setScaleX((float)(1+0.1*(trueOffset)));
            nextCard.setScaleY((float)(1+0.1*(trueOffset)));

            nextCard.setCardElevation(baseElevation+baseElevation*7*(trueOffset));
        }

        lastPosition=positionOffset;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void transformPage(View page, float position) {

    }
}
