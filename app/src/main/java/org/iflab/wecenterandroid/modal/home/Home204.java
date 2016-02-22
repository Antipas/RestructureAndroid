package org.iflab.wecenterandroid.modal.home;

/**
 * Created by Lyn on 15/12/16.
 * 赞同答案
 */
public class Home204 implements Home{

    /**
     * history_id : 4229
     * associate_action : 204
     * add_time : 1447743138
     * user_info : {"uid":3,"user_name":"BugFree","signature":"绝望中新生，迟疑中上涨，欢乐中死亡，企盼中下跌。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/03_avatar_mid.jpg"}
     * answer_info : {"answer_id":242,"answer_content":"如果高位出现放量滞涨，并且换手率大，就是有资金出场。","add_time":1441768273,"against_count":0,"agree_count":9}
     * question_info : {"question_id":137,"question_content":"请教：为什么有时候换手率比较低，主力却成功出货了？","add_time":1441706611,"update_time":1441768273,"answer_count":2,"agree_count":12}
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
     * answer_id : 242
     * answer_content : 如果高位出现放量滞涨，并且换手率大，就是有资金出场。
     * add_time : 1441768273
     * against_count : 0
     * agree_count : 9
     */

    private AnswerInfoEntity answer_info;
    /**
     * question_id : 137
     * question_content : 请教：为什么有时候换手率比较低，主力却成功出货了？
     * add_time : 1441706611
     * update_time : 1441768273
     * answer_count : 2
     * agree_count : 12
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

    public void setAnswer_info(AnswerInfoEntity answer_info) {
        this.answer_info = answer_info;
    }

    public void setQuestion_info(QuestionInfoEntity question_info) {
        this.question_info = question_info;
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

    public AnswerInfoEntity getAnswer_info() {
        return answer_info;
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

    public static class AnswerInfoEntity {
        private int answer_id;
        private String answer_content;
        private int add_time;
        private int against_count;
        private int agree_count;

        public void setAnswer_id(int answer_id) {
            this.answer_id = answer_id;
        }

        public void setAnswer_content(String answer_content) {
            this.answer_content = answer_content;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public void setAgainst_count(int against_count) {
            this.against_count = against_count;
        }

        public void setAgree_count(int agree_count) {
            this.agree_count = agree_count;
        }

        public int getAnswer_id() {
            return answer_id;
        }

        public String getAnswer_content() {
            return answer_content;
        }

        public int getAdd_time() {
            return add_time;
        }

        public int getAgainst_count() {
            return against_count;
        }

        public int getAgree_count() {
            return agree_count;
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
