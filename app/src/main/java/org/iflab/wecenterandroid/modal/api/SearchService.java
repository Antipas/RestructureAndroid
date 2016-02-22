package org.iflab.wecenterandroid.modal.api;

import org.iflab.wecenterandroid.modal.search.Search;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Lyn on 16/2/10.
 */
public interface SearchService {
    @GET("api/search/")
    Observable<Search> getSearch(@Query("q")String query, @Query("page")int page, @Query("mobile_sign") String sign);
}
