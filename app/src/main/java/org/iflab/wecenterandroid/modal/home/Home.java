package org.iflab.wecenterandroid.modal.home;

/**
 * Created by Lyn on 15/11/21.
 */
public interface Home {
    int getAssociate_action();
    String getOutline();
    String getImgUrl();
    String getUrl();
    int getAdd_time();

    Home.ArticleInfo getArticle_info();
    Home.Userinfo getUser_info();

    interface ArticleInfo{
        int getViews();
        int getId();
        String getTitle();
    }
    interface Userinfo{
        String getAvatar_file();
        String getUser_name();
        int getUid();
    }
}
