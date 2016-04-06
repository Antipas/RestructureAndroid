package org.iflab.wecenterandroid.modal.explore;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.util.RoundedTransformation;

import java.util.List;

/**
 * Created by Lyn on 16/1/23.
 */
public class ExploreArticle implements Explore{

    /**
     * id : 43
     * title : 中国万岁 万万岁
     * message : 中国万
     * views : 5
     * add_time : 1447398636
     * votes : 0
     * post_type : article
     * topics : [{"topic_id":210,"topic_title":"中概股"}]
     * user_info : {"uid":3,"user_name":"BugFree","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"}
     * answer_users : [{"uid":36,"user_name":"假装生在翰林院","avatar_file":"http://wecenter.dev.hihwei.com/static/common/avatar-mid-img.png"}]
     */

    private int id;
    private String title;
    private String message;
    private int views;
    private int add_time;
    private int votes;
    private String post_type;
    private UserInfoEntity user_info;
    private List<TopicsEntity> topics;
    private List<AnswerUsersEntity> answer_users;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public void setUser_info(UserInfoEntity user_info) {
        this.user_info = user_info;
    }

    public void setTopics(List<TopicsEntity> topics) {
        this.topics = topics;
    }

    public void setAnswer_users(List<AnswerUsersEntity> answer_users) {
        this.answer_users = answer_users;
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

    public int getViews() {
        return views;
    }

    public int getAdd_time() {
        return add_time;
    }

    public int getVotes() {
        return votes;
    }

    public String getPost_type() {
        return Explore.ARTICLE;
    }

    public UserInfoEntity getUser_info() {
        return user_info;
    }

    public List<TopicsEntity> getTopics() {
        return topics;
    }

    public List<AnswerUsersEntity> getAnswer_users() {
        return answer_users;
    }

    public static class UserInfoEntity {
        /**
         * uid : 3
         * user_name : BugFree
         * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg
         */

        private int uid;
        private String user_name;
        private String avatar_file;

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
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

        public String getAvatar_file() {
            return avatar_file;
        }
    }

    public static class TopicsEntity {
        /**
         * topic_id : 210
         * topic_title : 中概股
         */

        private int topic_id;
        private String topic_title;

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }

        public void setTopic_title(String topic_title) {
            this.topic_title = topic_title;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public String getTopic_title() {
            return topic_title;
        }
    }

    public static class AnswerUsersEntity {
        /**
         * uid : 36
         * user_name : 假装生在翰林院
         * avatar_file : http://wecenter.dev.hihwei.com/static/common/avatar-mid-img.png
         */

        private int uid;
        private String user_name;
        private String avatar_file;

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
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

        public String getAvatar_file() {
            return avatar_file;
        }
    }
}
