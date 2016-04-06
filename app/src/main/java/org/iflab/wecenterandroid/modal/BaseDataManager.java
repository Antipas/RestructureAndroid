package org.iflab.wecenterandroid.modal;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import org.iflab.wecenterandroid.Constant;
import org.iflab.wecenterandroid.modal.api.ArticleService;
import org.iflab.wecenterandroid.modal.api.ExploreService;
import org.iflab.wecenterandroid.modal.api.HomeService;
import org.iflab.wecenterandroid.modal.api.SearchService;
import org.iflab.wecenterandroid.modal.api.TopicService;
import org.iflab.wecenterandroid.modal.api.UserService;
import org.iflab.wecenterandroid.modal.api.converter.ExploreDataConverterFactory;
import org.iflab.wecenterandroid.modal.api.converter.HomeDataConverterFactory;
import org.iflab.wecenterandroid.modal.api.converter.SearchDataConverterFactory;
import org.iflab.wecenterandroid.modal.prefs.UserPrefs;
import org.iflab.wecenterandroid.util.PersistentCookieStore;

import java.net.CookieManager;
import java.net.CookiePolicy;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Lyn on 15/11/16.
 */
abstract class BaseDataManager {
    Context context;
    Retrofit retrofit;
    UserPrefs userPrefs;
    UserService userService;
    ExploreService exploreService;
    HomeService homeService;
    TopicService topicService;
    ArticleService articleService;
    SearchService searchService;

    OkHttpClient client = new OkHttpClient();

    public BaseDataManager(Context context){
        this.context = context;
        userPrefs = UserPrefs.getInstance(context);

        client.setCookieHandler(new CookieManager(
                new PersistentCookieStore(context),
                CookiePolicy.ACCEPT_ALL));

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        createUserService();
        createExploreService();
        createHomeService();
        createHotTopicsService();
        createArticleService();
        createSearchService();
    }

    protected void createArticleService(){
        articleService = retrofit.create(ArticleService.class);
    }

    protected void createHotTopicsService(){
        topicService = retrofit.create((TopicService.class));
    }

    protected void createHomeService(){
        homeService = new Retrofit.Builder()
                .baseUrl(Constant.BASE)
                .client(client)
                .addConverterFactory(HomeDataConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        .create(HomeService.class);
    }

    protected void createSearchService(){
        searchService = new Retrofit.Builder()
                .baseUrl(Constant.BASE)
                .client(client)
                .addConverterFactory(SearchDataConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(SearchService.class);
    }

    private void createUserService( ) {
        userService = retrofit.create((UserService.class));
    }

    private void createExploreService( ){
        exploreService = new Retrofit.Builder()
                .baseUrl(Constant.BASE)
                .client(client)
                .addConverterFactory(ExploreDataConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ExploreService.class);
    }

    public ExploreService getExploreService(){
        return exploreService;
    }

    public UserService getUserService(){
        return userService;
    }

    public HomeService getHomeService(){
        return homeService;
    }

    public TopicService getTopcService(){return topicService;}

    public UserPrefs getUserPrefs(){
        return userPrefs;
    }

    public ArticleService getArticleSerivce(){return articleService;}

    public SearchService getSearchService(){return searchService;}
}
