package org.iflab.wecenterandroid.modal.api;

import org.iflab.wecenterandroid.modal.explore.Explore;
import org.iflab.wecenterandroid.modal.explore.Famous;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Lyn on 15/11/21.
 */
public interface ExploreService {

    @GET("api/explore/")
    Observable<Explore> getExplore(@Query("page") int page,
                                   @Query("mobile_sign") String sign);

}
