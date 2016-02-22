package org.iflab.wecenterandroid.modal.home;

/**
 * Created by Lyn on 15/12/17.
 * 评论文章
 */
public class Home503 implements Home{

    /**
     * history_id : 4473
     * associate_action : 503
     * add_time : 1449396281
     * user_info : {"uid":63,"user_name":"billhu1996","signature":"","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/63_avatar_mid.jpg"}
     * article_info : {"id":50,"title":"这是一个文章","message":"这是一个文章文章\n\n\n\n文章\n\n 62 ","comments":2,"views":4,"add_time":1449396270}
     * comment_info : {"id":23,"uid":63,"article_id":50,"message":"住的","add_time":1449396281,"at_uid":0,"votes":0}
     */

    private int history_id;
    private int associate_action;
    private int add_time;
    /**
     * uid : 63
     * user_name : billhu1996
     * signature :
     * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/63_avatar_mid.jpg
     */

    private UserInfoEntity user_info;
    /**
     * id : 50
     * title : 这是一个文章
     * message : 这是一个文章文章



     文章

     62
     * comments : 2
     * views : 4
     * add_time : 1449396270
     */

    private ArticleInfoEntity article_info;
    /**
     * id : 23
     * uid : 63
     * article_id : 50
     * message : 住的
     * add_time : 1449396281
     * at_uid : 0
     * votes : 0
     */

    private CommentInfoEntity comment_info;

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

    public void setComment_info(CommentInfoEntity comment_info) {
        this.comment_info = comment_info;
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

    public CommentInfoEntity getComment_info() {
        return comment_info;
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

    public static class CommentInfoEntity {
        private int id;
        private int uid;
        private int article_id;
        private String message;
        private int add_time;
        private int at_uid;
        private int votes;

        public void setId(int id) {
            this.id = id;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setArticle_id(int article_id) {
            this.article_id = article_id;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public void setAt_uid(int at_uid) {
            this.at_uid = at_uid;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }

        public int getId() {
            return id;
        }

        public int getUid() {
            return uid;
        }

        public int getArticle_id() {
            return article_id;
        }

        public String getMessage() {
            return message;
        }

        public int getAdd_time() {
            return add_time;
        }

        public int getAt_uid() {
            return at_uid;
        }

        public int getVotes() {
            return votes;
        }
    }
}
