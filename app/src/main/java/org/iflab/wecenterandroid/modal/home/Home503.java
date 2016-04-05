package org.iflab.wecenterandroid.modal.home;

/**
 * Created by Lyn on 15/12/17.
 * 评论文章
 */
public class Home503 implements Home{


    /**
     * history_id : 79
     * uid : 1
     * associate_action : 503
     * add_time : 1458127635
     * user_info : {"uid":1,"user_name":"root","signature":"?A?SDF?DSAF?DSA?F","has_focus":0,"avatar_file":"http://we.edustack.org/uploads/avatar/000/00/00/01_avatar_mid.jpg"}
     * article_info : {"id":69,"title":"phpQuery\u2014基于jQuery的PHP实现 - Falling Leaves - 博客园","message":"","comments":1,"views":13,"add_time":1457860788,"category_id":2,"url":""}
     * comment_info : {"id":14,"message":"a:3:{s:3:","add_time":1458127635,"votes":0}
     * url : http://www.cnblogs.com/in-loading/archive/2012/04/11/2442697.html
     * outline : phpQuery—基于jQuery的PHP实现 - Falling Leaves - 博客园...
     * imgUrl : http://we.edustack.org/uploads/share/20160313171945703.png
     */

    private int history_id;
    private int uid;
    private int associate_action;
    private int add_time;
    /**
     * uid : 1
     * user_name : root
     * signature : ?A?SDF?DSAF?DSA?F
     * has_focus : 0
     * avatar_file : http://we.edustack.org/uploads/avatar/000/00/00/01_avatar_mid.jpg
     */

    private UserInfoEntity user_info;
    /**
     * id : 69
     * title : phpQuery—基于jQuery的PHP实现 - Falling Leaves - 博客园
     * message :
     * comments : 1
     * views : 13
     * add_time : 1457860788
     * category_id : 2
     * url :
     */

    private ArticleInfoEntity article_info;
    /**
     * id : 14
     * message : a:3:{s:3:
     * add_time : 1458127635
     * votes : 0
     */

    private CommentInfoEntity comment_info;
    private String url;
    private String outline;
    private String imgUrl;

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getHistory_id() {
        return history_id;
    }

    public int getUid() {
        return uid;
    }

    public int getAssociate_action() {
        return associate_action;
    }

    public int getAdd_time() {
        return add_time;
    }

    @Override
    public UserInfoEntity getUser_info() {
        return user_info;
    }

    @Override
    public ArticleInfoEntity getArticle_info() {
        return article_info;
    }

    public CommentInfoEntity getComment_info() {
        return comment_info;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getOutline() {
        return outline;
    }

    @Override
    public String getImgUrl() {
        return imgUrl;
    }

    public static class UserInfoEntity implements Home.Userinfo{
        private int uid;
        private String user_name;
        private String signature;
        private int has_focus;
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

        public void setHas_focus(int has_focus) {
            this.has_focus = has_focus;
        }

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }

        @Override
        public int getUid() {
            return uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getSignature() {
            return signature;
        }

        public int getHas_focus() {
            return has_focus;
        }

        @Override
        public String getAvatar_file() {
            return avatar_file;
        }
    }

    public static class ArticleInfoEntity implements Home.ArticleInfo{
        private int id;
        private String title;
        private String message;
        private int comments;
        private int views;
        private int add_time;
        private int category_id;
        private String url;

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

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public void setUrl(String url) {
            this.url = url;
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

        @Override
        public int getViews() {
            return views;
        }

        public int getAdd_time() {
            return add_time;
        }

        public int getCategory_id() {
            return category_id;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class CommentInfoEntity {
        private int id;
        private String message;
        private int add_time;
        private int votes;

        public void setId(int id) {
            this.id = id;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }

        public int getId() {
            return id;
        }

        public String getMessage() {
            return message;
        }

        public int getAdd_time() {
            return add_time;
        }

        public int getVotes() {
            return votes;
        }
    }
}
