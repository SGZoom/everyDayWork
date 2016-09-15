package io.netopen.hotbitmapgg.androideverydaypractice.gzoom_changetheme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.netopen.hotbitmapgg.androideverydaypractice.R;

public class GzoomchangethemeActivity extends AppCompatActivity {

    @Bind(R.id.gzoom_changetheme_daybut)
    Button buttonDay;

    @Bind(R.id.gzoom_changetheme_nightbut)
    Button buttonNight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gzoomchangetheme);
        ButterKnife.bind(this);

        buttonDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("fish","buttonDay.setOnClickListener");
               UnitForChange.changeTheme(GzoomchangethemeActivity.this,AppCompatDelegate.MODE_NIGHT_NO);
            }
        });


        buttonNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fish","buttonNight.setOnClickListener");
//                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                recreate();
                UnitForChange.changeTheme(GzoomchangethemeActivity.this,AppCompatDelegate.MODE_NIGHT_YES);
            }
        });
    }
}
