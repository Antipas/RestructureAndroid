package org.iflab.wecenterandroid.modal.api;

import org.iflab.wecenterandroid.modal.AnswerComment;
import org.iflab.wecenterandroid.modal.Answer;
import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.modal.QuestionFocus;
import org.iflab.wecenterandroid.modal.SaveComment;

import java.io.File;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Lyn on 15/12/21.
 */
public interface QuestionService {
    @GET("api/question/")
    Observable<Question> getQuestion(@Query("id") int id,@Query("page") int page,@Query("sort_key") String sort_key,@Query("mobile_sign") String sign);

    @FormUrlEncoded
    @POST("question/ajax/focus/?mobile_sign=a1e2ff633a92a7380772b0c2cc988f37")
    Observable<QuestionFocus> addFocus(@Field("question_id")int id);

    @GET("api/question/answer/")
    Observable<Answer> getAnswer(@Query("answer_id")int answer_id,@Query("mobile_sign") String sign);

    @FormUrlEncoded
    @POST("question/ajax/answer_vote/?mobile_sign=a1e2ff633a92a7380772b0c2cc988f37")
    Observable<QuestionFocus> voteorFuckAnswer(@Field("answer_id")int id,@Field("value")int value);

    @GET("api/question/answer_comments/")
    Observable<AnswerComment> loadAnswerComment(@Query("answer_id") int id,@Query("mobile_sign")String mobile_sign);

    @FormUrlEncoded
    @POST("api/question/save_answer_comment/?mobile_sign=db84618cbdee67c8afe17a5dc0d31186")
    Observable<SaveComment> sendAnswerComment(@Field("answer_id")int id,@Field("message")String message);

    @FormUrlEncoded
    @POST("api/publish/publish_question/?mobile_sign=e9d5d69977d030d81455b4d31c0b8c64")
    Observable<SaveComment> uploadQuestionText(@Field("question_content")String title, @Field("attach_access_key")String attach_access_key,
                                              @Field("question_detail") String content,
                                              @Field("topics") String topics);

    @FormUrlEncoded
    @POST("api/publish/save_answer/?mobile_sign=e9d5d69977d030d81455b4d31c0b8c64")
    Observable<SaveComment> uploadAnwerText(@Field("question_id")String id, @Field("attach_access_key")String attach_access_key,
                                               @Field("answer_content") String content);

}
