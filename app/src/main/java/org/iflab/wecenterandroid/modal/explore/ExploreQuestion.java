package org.iflab.wecenterandroid.modal.explore;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.util.RoundedTransformation;

import java.util.List;

/**
 * Created by Lyn on 16/1/23.
 */
public class ExploreQuestion implements Explore {

    /**
     * question_id : 162
     * question_content : 阿里九月份宣布杭州北京双总部，意图何在？
     * add_time : 1443680912
     * answer_count : 3
     * answer_users : [{"uid":19,"user_name":"放肆","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/19_avatar_mid.jpg"},{"uid":2,"user_name":"小韭菜","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/02_avatar_mid.jpg"}]
     * view_count : 88
     * agree_count : 5
     * against_count : 0
     * post_type : question
     * topics : [{"topic_id":247,"topic_title":"阿里"}]
     * user_info : {"uid":4,"user_name":"Carol","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/04_avatar_mid.jpg"}
     */

    private int question_id;
    private String question_content;
    private int add_time;
    private int answer_count;
    private int view_count;
    private int agree_count;
    private int against_count;
    private String post_type;
    private UserInfoEntity user_info;
    private List<AnswerUsersEntity> answer_users;
    private List<TopicsEntity> topics;

    @BindingAdapter({"bind:explore_avatar"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public void setAgree_count(int agree_count) {
        this.agree_count = agree_count;
    }

    public void setAgainst_count(int against_count) {
        this.against_count = against_count;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public void setUser_info(UserInfoEntity user_info) {
        this.user_info = user_info;
    }

    public void setAnswer_users(List<AnswerUsersEntity> answer_users) {
        this.answer_users = answer_users;
    }

    public void setTopics(List<TopicsEntity> topics) {
        this.topics = topics;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public int getAdd_time() {
        return add_time;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public int getView_count() {
        return view_count;
    }

    public int getAgree_count() {
        return agree_count;
    }

    public int getAgainst_count() {
        return against_count;
    }

    public String getPost_type() {
        return post_type;
    }

    public UserInfoEntity getUser_info() {
        return user_info;
    }

    public List<AnswerUsersEntity> getAnswer_users() {
        return answer_users;
    }

    public List<TopicsEntity> getTopics() {
        return topics;
    }

    public static class UserInfoEntity {
        /**
         * uid : 4
         * user_name : Carol
         * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/04_avatar_mid.jpg
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

    public static class AnswerUsersEntity {
        /**
         * uid : 19
         * user_name : 放肆
         * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/19_avatar_mid.jpg
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
         * topic_id : 247
         * topic_title : 阿里
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
}
