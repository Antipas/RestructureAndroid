package org.iflab.wecenterandroid.modal.explore;

import java.util.List;

/**
 * Created by Lyn on 16/1/23.
 */
public class ExploreArticle implements Explore{


    /**
     * id : 126
     * title : 当巨头纷纷以重金砸向邮轮旅游，已深耕十年的垂直平台“世界邮轮网”如何护城河？_36氪
     * message :
     * views : 2
     * add_time : 1459429504
     * votes : 0
     * category_id : 2
     * url : http://36kr.com/p/5045368.html
     * post_type : article
     * topics : []
     * user_info : {"uid":13,"user_name":"MsrButterfly","has_focus":0,"avatar_file":"http://we.edustack.org/uploads/avatar/000/00/00/13_avatar_mid.jpg"}
     * outline : 邮轮就像是.
     * imgUrl : http://we.edustack.org/uploads/share/20160331210504399.png
     */

    private int id;
    private String title;
    private String message;
    private int views;
    private long add_time;
    private int votes;
    private int category_id;
    private String url;
    private String post_type;
    /**
     * uid : 13
     * user_name : MsrButterfly
     * has_focus : 0
     * avatar_file : http://we.edustack.org/uploads/avatar/000/00/00/13_avatar_mid.jpg
     */

    private UserInfoBean user_info;
    private String outline;
    private String imgUrl;
    private List<?> topics;

    public String getPost_type() {
        return Explore.ARTICLE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<?> getTopics() {
        return topics;
    }

    public void setTopics(List<?> topics) {
        this.topics = topics;
    }

    public static class UserInfoBean {
        private int uid;
        private String user_name;
        private int has_focus;
        private String avatar_file;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getHas_focus() {
            return has_focus;
        }

        public void setHas_focus(int has_focus) {
            this.has_focus = has_focus;
        }

        public String getAvatar_file() {
            return avatar_file;
        }

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }
    }
}
