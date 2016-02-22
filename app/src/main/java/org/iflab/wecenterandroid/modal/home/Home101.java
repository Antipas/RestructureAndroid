package org.iflab.wecenterandroid.modal.home;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.util.RoundedTransformation;

/**
 * Created by Lyn on 15/12/16.
 * 添加问题
 */
public class Home101 implements Home{

    /**
     * history_id : 4085
     * associate_action : 101
     * add_time : 1444039751
     * user_info : {"uid":5,"user_name":"滚雪球","signature":"滚雪球，不是一个人能完成的事儿...","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/05_avatar_mid.jpg"}
     * question_info : {"question_id":168,"question_content":"你觉得下一个会再火10年的行业是什么？","add_time":1444039751,"update_time":1444039809,"answer_count":1,"agree_count":2}
     */

    private int history_id;
    private int associate_action;
    private int add_time;
    /**
     * uid : 5
     * user_name : 滚雪球
     * signature : 滚雪球，不是一个人能完成的事儿...
     * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/05_avatar_mid.jpg
     */

    private UserInfoEntity user_info;
    /**
     * question_id : 168
     * question_content : 你觉得下一个会再火10年的行业是什么？
     * add_time : 1444039751
     * update_time : 1444039809
     * answer_count : 1
     * agree_count : 2
     */

    private QuestionInfoEntity question_info;

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

    public void setQuestion_info(QuestionInfoEntity question_info) {
        this.question_info = question_info;
    }

    public int getHistory_id() {
        return history_id;
    }

    @BindingAdapter({"bind:homeAvatar"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
    }

    @Override
    public int getAssociate_action() {
        return associate_action;
    }

    public int getAdd_time() {
        return add_time;
    }

    public UserInfoEntity getUser_info() {
        return user_info;
    }

    public QuestionInfoEntity getQuestion_info() {
        return question_info;
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

    public static class QuestionInfoEntity {
        private int question_id;
        private String question_content;
        private int add_time;
        private int update_time;
        private int answer_count;
        private int agree_count;

        public void setQuestion_id(int question_id) {
            this.question_id = question_id;
        }

        public void setQuestion_content(String question_content) {
            this.question_content = question_content;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public void setAnswer_count(int answer_count) {
            this.answer_count = answer_count;
        }

        public void setAgree_count(int agree_count) {
            this.agree_count = agree_count;
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

        public int getUpdate_time() {
            return update_time;
        }

        public int getAnswer_count() {
            return answer_count;
        }

        public int getAgree_count() {
            return agree_count;
        }
    }
}
