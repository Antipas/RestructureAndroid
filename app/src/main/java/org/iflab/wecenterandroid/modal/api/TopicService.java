package org.iflab.wecenterandroid.modal.api;

import org.iflab.wecenterandroid.modal.Topic;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Lyn on 15/12/12.
 */
public interface TopicService {
    @GET("api/topic/hot_topics/")
    Observable<Topic> getTopics(@Query("day") String type,@Query("page") int page,@Query("mobile_sign") String sign);
}
