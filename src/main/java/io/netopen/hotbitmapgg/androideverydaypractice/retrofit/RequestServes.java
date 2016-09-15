package io.netopen.hotbitmapgg.androideverydaypractice.retrofit;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by gzoom on 2016/9/4.
 */
public interface RequestServes {
    @POST()
    Call<String> getString(@Query("loginname")String name,@Query("nlogpsw") String password);
}
