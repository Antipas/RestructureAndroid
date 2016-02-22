package org.iflab.wecenterandroid.modal.api.auth;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Lyn on 15/11/15.
 */
interface UserAuthService {
    String ENDPOINT = "we.edustack.org/";

    @POST("/oauth/token")
    public void getAccessToken(@Query("client_id") String client_id,
                               @Query("client_secret") String client_secret,
                               @Query("code") String code,
                               @Body String unused,
                               Callback<AccessToken> callback);
}


