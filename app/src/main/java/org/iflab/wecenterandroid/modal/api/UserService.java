package org.iflab.wecenterandroid.modal.api;


import org.iflab.wecenterandroid.modal.LoginInfo;
import org.iflab.wecenterandroid.modal.User;
import org.iflab.wecenterandroid.modal.UserAPIModal;
import org.iflab.wecenterandroid.modal.explore.Famous;
import org.iflab.wecenterandroid.modal.person.PersonFollow;
import org.iflab.wecenterandroid.modal.person.PersonInfo;
import org.iflab.wecenterandroid.modal.person.PersonalAnswer;
import org.iflab.wecenterandroid.modal.person.PersonalArticle;
import org.iflab.wecenterandroid.modal.person.PersonalQuestion;
import org.iflab.wecenterandroid.view.activity.PersonInfoActivity;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Lyn on 15/11/14.
 */
public interface UserService{

    @FormUrlEncoded
    @POST("api/account/login_process/?mobile_sign=efeaf5de519976c71ec1204e331a1fd5")
    Observable<Response<LoginInfo>> doLogin(@Field("user_name") String name,@Field("password") String password);

    @GET("api/account/get_userinfo/")
    Observable<UserAPIModal> getUserInfo(@Query("uid") String uid,@Query("mobile_sign") String sign);

    @GET("api/people/follows/")
    Observable<PersonFollow> getPersonFollowOrFans(@Query("uid")String uid,@Query("type")String follows,@Query("page")int page,@Query("mobile_sign") String sign);

    @GET("api/people/user_actions/")
    Observable<PersonalArticle> getUserArticle(@Query("actions") int actions,@Query("uid") String uid,@Query("page")int page,@Query("mobile_sign") String sign);

    @GET("api/people/user_actions/")
    Observable<PersonalQuestion> getUserQuestion(@Query("actions") int actions,@Query("uid") String uid,@Query("page")int page,@Query("mobile_sign") String sign);

    @GET("api/people/user_actions/")
    Observable<PersonalAnswer> getUserAnswer(@Query("actions") int actions,@Query("uid") String uid,@Query("page")int page,@Query("mobile_sign") String sign);

    @GET("api/famous/famous_users/")
    Observable<Famous> getFamous(@Query("mobile_sign") String sign,@Query("page") int page);

    @GET("api/media/media_users/")
    Observable<Famous> getMedia(@Query("mobile_sign") String sign,@Query("page") int page);
}
