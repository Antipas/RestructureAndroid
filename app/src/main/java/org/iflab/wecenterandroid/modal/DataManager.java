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
    public Observable loadExplore(int page){
        return getExploreService().getExplore(MD5Util.MD5("explore" + Constant.SIGN),page)
                .compose(applySchedulers());
    }

    public Observable loadFamous(int page){
        return getUserService().getFamous(MD5Util.MD5("famous" + Constant.SIGN),page)
                .compose(applySchedulers());
    }

    public Observable loadMedia(int page){
        return getUserService().getMedia(MD5Util.MD5("media" + Constant.SIGN),page)
                .compose(applySchedulers());
    }

    public Observable loadHome(int page){
        return getHomeService().getHome(page, MD5Util.MD5("home" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadMyZaidu(int page,String uid){
        return getHomeService().getMyZaidu(page, MD5Util.MD5("home" + Constant.SIGN),uid)
                .compose(applySchedulers());
    }

    public Observable loadTopics(int page,String type){
        return getTopcService().getTopics(type, page, MD5Util.MD5("topic" + Constant.SIGN))
                .compose(applySchedulers());
    }


    public Observable loadArticle(int id,int page){
        return getArticleSerivce().getArticle(id, page, MD5Util.MD5("article" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadComments(int id,int page){
        return getArticleSerivce().getComments(id, page, MD5Util.MD5("article" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable loadUserInfo(String uid){
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

    public Observable favorite(int itemId,String type){
        return getArticleSerivce().favortie(itemId, type, "")
                .compose(applySchedulers());
    }

    public Observable voteOrFuckArticle(int itemId,String type,int rating){
        return getArticleSerivce().voteOrFuckArticle(itemId, type, rating)
                .compose(applySchedulers());
    }

    public Observable addFocusPeople(String uid){
        return getUserService().addFocusPeople(uid,MD5Util.MD5("ajax" + Constant.SIGN))
                .compose(applySchedulers());
    }

    public Observable addQRCodeArticle(String url){
        return getArticleSerivce().addQRCodeArticle(url)
                .compose(applySchedulers());
    }

    public <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }


}
