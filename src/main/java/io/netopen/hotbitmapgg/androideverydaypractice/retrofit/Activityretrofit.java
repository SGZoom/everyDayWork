package io.netopen.hotbitmapgg.androideverydaypractice.retrofit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Activityretrofit extends AppCompatActivity {

    @Bind(R.id.retrofit_button)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityretrofit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);


        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("www.baidu.com")
                .addConverterFactory(ScalarsConverterFactory.create())//string
                .addConverterFactory(GsonConverterFactory.create())//Gson
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//rxjava
                .build();

        RequestServes requestServes = retrofit.create(RequestServes.class);
       final Call<String>call=requestServes.getString("username","1234");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                            showSucceed();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        showFailed();
                    }
                });
            }


        });
        


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void showFailed() {


    }

    private void showSucceed() {
    }

}
