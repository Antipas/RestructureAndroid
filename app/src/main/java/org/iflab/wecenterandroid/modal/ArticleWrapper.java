package org.iflab.wecenterandroid.modal;

import android.text.Spanned;

/**
 * Created by Lyn on 15/12/26.
 */
public class ArticleWrapper {

    Article article;
    Spanned Spanned;
    Question.RsmEntity.QuestionInfoEntity.UserInfoEntity userInfoEntity;

    public Spanned getSpanned() {
        return Spanned;
    }

    public void setSpanned(Spanned Spanned) {
        this.Spanned = Spanned;
    }

    public Question.RsmEntity.QuestionInfoEntity.UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(Question.RsmEntity.QuestionInfoEntity.UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    public Article getArticle(){
        return article;
    }

    public ArticleWrapper(Article article){
        this.article = article;
    }
    
    public static class Builder{
        
        ArticleWrapper articleWrapper;

        
        public Builder (Article article){
            articleWrapper = new ArticleWrapper(article);
        }
        
        public Builder setSpan(Spanned Spanned){
            articleWrapper.setSpanned(Spanned);
            return this;
        }
        
        public Builder setUserInfo(Question.RsmEntity.QuestionInfoEntity.UserInfoEntity userInfoEntity){
            articleWrapper.setUserInfoEntity(userInfoEntity);
            return this;
        }
        
        public ArticleWrapper builder(){
            return articleWrapper;
        }
    }
}
