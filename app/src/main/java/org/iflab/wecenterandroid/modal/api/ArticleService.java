package org.iflab.wecenterandroid.modal.api;

import com.squareup.okhttp.RequestBody;

import org.iflab.wecenterandroid.modal.Article;
import org.iflab.wecenterandroid.modal.Attach;
import org.iflab.wecenterandroid.modal.Comments;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.modal.article.QRCodeArticle;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Lyn on 15/12/26.
 */
public interface ArticleService {
    @GET("api/article/")
    Observable<Article>  getArticle(@Query("id") int id,@Query("page") int page,@Query("mobile_sign") String sign);

    @GET("api/article/article_comments/")
    Observable<Comments> getComments(@Query("id")int id,@Query("page") int page,@Query("mobile_sign") String sign);

    @FormUrlEncoded
    @POST("api/article/save_comment/?mobile_sign=0ef1e04df1ae988fb7a8cbf59c541c63")
    Observable<SaveComment> sendComment(@Field("article_id") int id,@Field("message") String message);

    @FormUrlEncoded
    @POST("api/article/save_comment/?mobile_sign=0ef1e04df1ae988fb7a8cbf59c541c63")
    Observable<SaveComment> sendCommentAtUid(@Field("article_id") int id,@Field("message") String message,@Field("at_uid") int uid);

    @FormUrlEncoded
    @POST("favorite/ajax/update_favorite_tag/?mobile_sign=a1e2ff633a92a7380772b0c2cc988f37")
    Observable<SaveComment> favortie(@Field("item_id")int item_id, @Field("item_type")String item_type,@Field("tags") String tags);

    @FormUrlEncoded
    @POST("article/ajax/article_vote/?mobile_sign=a1e2ff633a92a7380772b0c2cc988f37")
    Observable<SaveComment> voteOrFuckArticle(@Field("item_id")int item_id, @Field("type")String type,@Field("rating") int rating);

    @FormUrlEncoded
    @POST("api/publish/publish_article_by_url/?mobile_sign=e9d5d69977d030d81455b4d31c0b8c64")
    Observable<QRCodeArticle> addQRCodeArticle(@Field("url") String url);

    @Multipart
    @POST("api/publish/attach_upload/?mobile_sign=e9d5d69977d030d81455b4d31c0b8c64")
    Observable <Attach> uploadAttach(@Part("id")RequestBody id,@Part("attach_access_key")RequestBody key,
                                     @Part("qqfile\"; filename=\"qqfile.jpg\" ")RequestBody file);

    @FormUrlEncoded
    @POST("api/publish/publish_article/?mobile_sign=e9d5d69977d030d81455b4d31c0b8c64")
    Observable<SaveComment> uploadArticleText(@Field("title")String title, @Field("attach_access_key")String attach_access_key,
                                              @Field("message") String message,
                                              @Field("topics") String topics);
}
