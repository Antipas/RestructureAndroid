package org.iflab.wecenterandroid.modal.api;

import android.util.ArrayMap;

import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.modal.home.Home;
import org.iflab.wecenterandroid.modal.search.Search;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Lyn on 15/12/9.
 */
public interface HomeService {
    @GET("api/home/")
    Observable<ArrayMap> getHome(@Query("page") int page, @Query("mobile_sign") String sign);

    @GET("api/home/")
    Observable<ArrayMap> getMyZaidu(@Query("page") int page, @Query("mobile_sign") String sign,@Query("uid") String uid);
}
