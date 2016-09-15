package io.netopen.hotbitmapgg.androideverydaypractice.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.netopen.hotbitmapgg.androideverydaypractice.R;

public class ViewPagerMain extends AppCompatActivity {

    @Bind(R.id.view_pager_main_view)
    ViewPager viewPager;
    ElevationAdapter elevationAdapter;
    PagerViewAdapter pagerViewAdapter;
    PagerViewFragmentAdapter viewFragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_main);
        ButterKnife.bind(this);
        pagerViewAdapter = new PagerViewAdapter(6);

        viewFragmentAdapter = new PagerViewFragmentAdapter(getSupportFragmentManager(),6);
//        elevationAdapter=new ElevationAdapter(viewFragmentAdapter,viewPager);

        elevationAdapter=new ElevationAdapter(pagerViewAdapter,viewPager);
        viewPager.setAdapter(pagerViewAdapter);
//        viewPager.setAdapter(viewFragmentAdapter);
        viewPager.setPageTransformer(false,elevationAdapter);//第一个参数是需不需要反过来从最后一个画到第一个
        viewPager.setOffscreenPageLimit(3);
    }
}
