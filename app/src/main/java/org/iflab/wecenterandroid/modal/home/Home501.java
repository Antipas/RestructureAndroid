package org.iflab.wecenterandroid.modal.home;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.util.RoundedTransformation;

/**
 * Created by Lyn on 15/12/16.
 * 添加文章
 */
public class Home501 implements Home{

    /**
     * history_id : 4222
     * associate_action : 501
     * add_time : 1447398636
     * user_info : {"uid":3,"user_name":"BugFree","signature":"绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"}
     * article_info : {"id":43,"title":"中国万岁 万万岁","message":"中国万岁 万万岁中国万岁 万万岁中国万岁 万万岁。","comments":0,"views":5,"add_time":1447398636}
     */

    private int history_id;
    private int associate_action;
    private int add_time;
    /**
     * uid : 3
     * user_name : BugFree
     * signature : 绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。
     * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg
     */

    private UserInfoEntity user_info;
    /**
     * id : 43
     * title : 中国万岁 万万岁
     * message : 中国万岁 万万岁中国万岁 万万岁中国万岁 万万岁。
     * comments : 0
     * views : 5
     * add_time : 1447398636
     */

    private ArticleInfoEntity article_info;

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public void setAssociate_action(int associate_action) {
        this.associate_action = associate_action;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public void setUser_info(UserInfoEntity user_info) {
        this.user_info = user_info;
    }

    public void setArticle_info(ArticleInfoEntity article_info) {
        this.article_info = article_info;
    }

    public int getHistory_id() {
        return history_id;
    }

    public int getAssociate_action() {
        return associate_action;
    }

    public int getAdd_time() {
        return add_time;
    }

    public UserInfoEntity getUser_info() {
        return user_info;
    }

    public ArticleInfoEntity getArticle_info() {
        return article_info;
    }

    @BindingAdapter({"bind:homeAvatar"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
    }

    public static class UserInfoEntity {
        private int uid;
        private String user_name;
        private String signature;
        private String avatar_file;

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }

        public int getUid() {
            return uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getSignature() {
            return signature;
        }

        public String getAvatar_file() {
            return avatar_file;
        }
    }

    public static class ArticleInfoEntity {
        private int id;
        private String title;
        private String message;
        private int comments;
        private int views;
        private int add_time;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getMessage() {
            return message;
        }

        public int getComments() {
            return comments;
        }

        public int getViews() {
            return views;
        }

        public int getAdd_time() {
            return add_time;
        }
    }
}
