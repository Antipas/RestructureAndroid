package org.iflab.wecenterandroid.modal;

import android.content.Context;

import com.squareup.okhttp.RequestBody;

import org.iflab.wecenterandroid.Constant;
import org.iflab.wecenterandroid.util.MD5Util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lyn on 15/11/14.
 */
public class DataManager extends BaseDataManager {

    public static DataManager dataManager;
    Observable.Transformer schedulersTransformer;

    private DataManager(Context context){
        super(context);

        schedulersTransformer = new  Observable.Transformer() {
            @Override public Object call(Object observable) {
                return ((Observable)  observable).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static DataManager getInstance(Context context){
        synchronized (DataManager.class){
            if(dataManager == null){
                dataManager = new DataManager(context);
            }
            return dataManager;
        }
    }

    public Observable login(String name,String pwd){
        return getUserService().doLogin(name,pwd)
                .compose(applySchedulers());
    }

    // load ..
    public Observable loadExplore(int page,int day, int recommend, String type){
        return getExploreService().getExplore(page, day, recommend, type, MD5Util.MD5("explore" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadHome(int page){
        return getHomeService().getHome(page, MD5Util.MD5("home" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadTopics(int page,String type){
        return getTopcService().getTopics(type, page, MD5Util.MD5("topic" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadQuestion(int id,int page){
        return getQuestionService().getQuestion(id, page, "add_time", MD5Util.MD5("question" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadArticle(int id,int page){
        return getArticleSerivce().getArticle(id, page, MD5Util.MD5("article" + Constant.SIGN));
    }

    public Observable loadComments(int id,int page){
        return getArticleSerivce().getComments(id, page, MD5Util.MD5("article" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadAnswerComments(int id){
        return getQuestionService().loadAnswerComment(id, MD5Util.MD5("question" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadAnswer(int id){
        return getQuestionService().getAnswer(id, MD5Util.MD5("question" + Constant.SIGN));
    }

    public Observable loadUserinfo(String uid){
        return getUserService().getUserInfo(uid, MD5Util.MD5("account" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadUserFollow(String uid,int page){
        return getUserService().getPersonFollowOrFans(uid, "follows", page, MD5Util.MD5("people" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadUserFans(String uid,int page){
        return getUserService().getPersonFollowOrFans(uid,"fans",page,MD5Util.MD5("people" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadUserArticle(String uid,int page){
        return getUserService().getUserArticle(501, uid, page, MD5Util.MD5("people" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadUserQuestion(String uid,int page){
        return getUserService().getUserQuestion(101, uid, page, MD5Util.MD5("people" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadUserAnswer(String uid,int page){
        return getUserService().getUserAnswer(201,uid,page,MD5Util.MD5("people" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable doQuery(String query,int page){
        return getSearchService().getSearch(query,page,MD5Util.MD5("search" + Constant.SIGN))
                .compose(applySchedulers());
    }

    // push..
    public Observable sendComment(int id,String comment){
        return getArticleSerivce().sendComment(id, comment)
                .compose(applySchedulers());
    }

    public Observable sendAnswerComment(int id,String comment){
        return getQuestionService().sendAnswerComment(id, comment)
                .compose(applySchedulers());
    }

    public Observable favorite(int itemId,String type){
        return getArticleSerivce().favortie(itemId, type, "")
                .compose(applySchedulers());
    }

    public Observable voteOrFuckArticle(int itemId,String type,int rating){
        return getArticleSerivce().voteOrFuckArticle(itemId, type, rating)
                .compose(applySchedulers());
    }

    public Observable addFocus(int id){
        return getQuestionService().addFocus(id)
                .compose(applySchedulers());
    }

    public Observable voteOrFuckAnswer(int id,int value){
        return getQuestionService().voteorFuckAnswer(id, value)
                .compose(applySchedulers());
    }

    public Observable uploadAttach(RequestBody file,RequestBody key,RequestBody id){
        return getArticleSerivce().uploadAttach(id, key,file)
                .compose(applySchedulers());
    }

    public Observable uploadArticleText(String title,String attach_access_key,String message,String topics){
        return getArticleSerivce().uploadArticleText(title, attach_access_key, message, topics)
                .compose(applySchedulers());
    }

    public Observable uploadQuestionext(String title,String attach_access_key,String content,String topics){
        return getQuestionService().uploadQuestionText(title, attach_access_key, content, topics)
                .compose(applySchedulers());
    }

    public Observable uploadAnswerText(String id,String attach_access_key,String content){
        return getQuestionService().uploadAnwerText(id, attach_access_key, content)
                .compose(applySchedulers());
    }

    public <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }


}
