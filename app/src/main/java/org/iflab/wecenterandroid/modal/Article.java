package org.iflab.wecenterandroid.modal;

import java.util.List;

/**
 * Created by Lyn on 15/12/26.
 */
public class Article {


    /**
     * article_info : {"id":12,"uid":2,"title":"未来的工作是分布式的","message":"编者按：随","comments":2,"views":13,"add_time":1456559565,"has_attach":0,"lock":0,"votes":0,"title_fulltext":"2641026469 2403720316 209982406724335","category_id":1,"is_recommend":0,"chapter_id":null,"sort":0,"url":"http://we.edustack.org/article/12","user_info":{"uid":2,"user_name":"lyn","signature":"","avatar_file":"http://we.edustack.org/uploads/avatar/000/00/00/02_avatar_mid.jpg","has_focus":0},"vote_users":null,"user_follow_check":0,"is_favorite":0}
     * article_topics : []
     * comments : [{"id":1,"uid":2,"article_id":12,"message":"看起来好厉害","add_time":1456559580,"at_uid":0,"votes":0,"user_info":{"uid":2,"user_name":"lyn","avatar_file":"http://we.edustack.org/uploads/avatar/000/00/00/02_avatar_mid.jpg"},"at_user_info":null},{"id":8,"uid":4,"article_id":12,"message":"Are u ok？","add_time":1457092703,"at_uid":0,"votes":0,"user_info":{"uid":4,"user_name":"nicheng","avatar_file":"http://we.edustack.org/static/common/avatar-mid-img.png"},"at_user_info":null}]
     */

    private RsmEntity rsm;
    /**
     * rsm : {"article_info":{"id":12,"uid":2,"title":"未来的工作是分布式的","message":"编者按：随","comments":2,"views":13,"add_time":1456559565,"has_attach":0,"lock":0,"votes":0,"title_fulltext":"2641026469 2403720316 209982406724335","category_id":1,"is_recommend":0,"chapter_id":null,"sort":0,"url":"http://we.edustack.org/article/12","user_info":{"uid":2,"user_name":"lyn","signature":"","avatar_file":"http://we.edustack.org/uploads/avatar/000/00/00/02_avatar_mid.jpg","has_focus":0},"vote_users":null,"user_follow_check":0,"is_favorite":0},"article_topics":[],"comments":[{"id":1,"uid":2,"article_id":12,"message":"看起来好厉害","add_time":1456559580,"at_uid":0,"votes":0,"user_info":{"uid":2,"user_name":"lyn","avatar_file":"http://we.edustack.org/uploads/avatar/000/00/00/02_avatar_mid.jpg"},"at_user_info":null},{"id":8,"uid":4,"article_id":12,"message":"Are u ok？","add_time":1457092703,"at_uid":0,"votes":0,"user_info":{"uid":4,"user_name":"nicheng","avatar_file":"http://we.edustack.org/static/common/avatar-mid-img.png"},"at_user_info":null}]}
     * errno : 1
     * err : null
     */

    private int errno;
    private Object err;

    public void setRsm(RsmEntity rsm) {
        this.rsm = rsm;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public void setErr(Object err) {
        this.err = err;
    }

    public RsmEntity getRsm() {
        return rsm;
    }

    public int getErrno() {
        return errno;
    }

    public Object getErr() {
        return err;
    }

    public static class RsmEntity {
        /**
         * id : 12
         * uid : 2
         * title : 未来的工作是分布式的
         * message : 编者按：随
         * comments : 2
         * views : 13
         * add_time : 1456559565
         * has_attach : 0
         * lock : 0
         * votes : 0
         * title_fulltext : 2641026469 2403720316 209982406724335
         * category_id : 1
         * is_recommend : 0
         * chapter_id : null
         * sort : 0
         * url : http://we.edustack.org/article/12
         * user_info : {"uid":2,"user_name":"lyn","signature":"","avatar_file":"http://we.edustack.org/uploads/avatar/000/00/00/02_avatar_mid.jpg","has_focus":0}
         * vote_users : null
         * user_follow_check : 0
         * is_favorite : 0
         */

        private ArticleInfoEntity article_info;
        private List<?> article_topics;
        /**
         * id : 1
         * uid : 2
         * article_id : 12
         * message : 看起来好厉害
         * add_time : 1456559580
         * at_uid : 0
         * votes : 0
         * user_info : {"uid":2,"user_name":"lyn","avatar_file":"http://we.edustack.org/uploads/avatar/000/00/00/02_avatar_mid.jpg"}
         * at_user_info : null
         */

        private List<CommentsEntity> comments;

        public void setArticle_info(ArticleInfoEntity article_info) {
            this.article_info = article_info;
        }

        public void setArticle_topics(List<?> article_topics) {
            this.article_topics = article_topics;
        }

        public void setComments(List<CommentsEntity> comments) {
            this.comments = comments;
        }

        public ArticleInfoEntity getArticle_info() {
            return article_info;
        }

        public List<?> getArticle_topics() {
            return article_topics;
        }

        public List<CommentsEntity> getComments() {
            return comments;
        }

        public static class ArticleInfoEntity {
            private int id;
            private int uid;
            private String title;
            private String message;
            private int comments;
            private int views;
            private int add_time;
            private int has_attach;
            private int lock;
            private int votes;
            private String title_fulltext;
            private int category_id;
            private int is_recommend;
            private Object chapter_id;
            private int sort;
            private String url;
            /**
             * uid : 2
             * user_name : lyn
             * signature :
             * avatar_file : http://we.edustack.org/uploads/avatar/000/00/00/02_avatar_mid.jpg
             * has_focus : 0
             */

            private UserInfoEntity user_info;
            private VoteInfoEntity vote_info;
            private Object vote_users;
            private int user_follow_check;
            private int is_favorite;

            public void setId(int id) {
                this.id = id;
            }

            public void setUid(int uid) {
                this.uid = uid;
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

            public void setHas_attach(int has_attach) {
                this.has_attach = has_attach;
            }

            public void setLock(int lock) {
                this.lock = lock;
            }

            public void setVotes(int votes) {
                this.votes = votes;
            }

            public void setTitle_fulltext(String title_fulltext) {
                this.title_fulltext = title_fulltext;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public void setIs_recommend(int is_recommend) {
                this.is_recommend = is_recommend;
            }

            public void setChapter_id(Object chapter_id) {
                this.chapter_id = chapter_id;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setUser_info(UserInfoEntity user_info) {
                this.user_info = user_info;
            }

            public void setVote_info(VoteInfoEntity vote_info) {
                this.vote_info = vote_info;
            }


            public void setVote_users(Object vote_users) {
                this.vote_users = vote_users;
            }

            public void setUser_follow_check(int user_follow_check) {
                this.user_follow_check = user_follow_check;
            }

            public void setIs_favorite(int is_favorite) {
                this.is_favorite = is_favorite;
            }

            public int getId() {
                return id;
            }

            public int getUid() {
                return uid;
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

            public int getHas_attach() {
                return has_attach;
            }

            public int getLock() {
                return lock;
            }

            public int getVotes() {
                return votes;
            }

            public String getTitle_fulltext() {
                return title_fulltext;
            }

            public int getCategory_id() {
                return category_id;
            }

            public int getIs_recommend() {
                return is_recommend;
            }

            public Object getChapter_id() {
                return chapter_id;
            }

            public int getSort() {
                return sort;
            }

            public String getUrl() {
                return url;
            }

            public UserInfoEntity getUser_info() {
                return user_info;
            }

            public VoteInfoEntity getVote_info() {
                return vote_info;
            }

            public Object getVote_users() {
                return vote_users;
            }

            public int getUser_follow_check() {
                return user_follow_check;
            }

            public int getIs_favorite() {
                return is_favorite;
            }

            public static class UserInfoEntity {
                private int uid;
                private String user_name;
                private String signature;
                private String avatar_file;
                private int has_focus;

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

                public void setHas_focus(int has_focus) {
                    this.has_focus = has_focus;
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

                public int getHas_focus() {
                    return has_focus;
                }
            }

            public static class VoteInfoEntity {
                private int id;
                private int uid;
                private String type;
                private int item_id;
                private int rating;
                private int time;
                private int reputation_factor;
                private int item_uid;

                public void setId(int id) {
                    this.id = id;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public void setItem_id(int item_id) {
                    this.item_id = item_id;
                }

                public void setRating(int rating) {
                    this.rating = rating;
                }

                public void setTime(int time) {
                    this.time = time;
                }

                public void setReputation_factor(int reputation_factor) {
                    this.reputation_factor = reputation_factor;
                }

                public void setItem_uid(int item_uid) {
                    this.item_uid = item_uid;
                }

                public int getId() {
                    return id;
                }

                public int getUid() {
                    return uid;
                }

                public String getType() {
                    return type;
                }

                public int getItem_id() {
                    return item_id;
                }

                public int getRating() {
                    return rating;
                }

                public int getTime() {
                    return time;
                }

                public int getReputation_factor() {
                    return reputation_factor;
                }

                public int getItem_uid() {
                    return item_uid;
                }
            }
        }

        public static class CommentsEntity {
            private int id;
            private int uid;
            private int article_id;
            private String message;
            private int add_time;
            private int at_uid;
            private int votes;
            /**
             * uid : 2
             * user_name : lyn
             * avatar_file : http://we.edustack.org/uploads/avatar/000/00/00/02_avatar_mid.jpg
             */

            private UserInfoEntity user_info;
            private Object at_user_info;

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

            public void setUser_info(UserInfoEntity user_info) {
                this.user_info = user_info;
            }

            public void setAt_user_info(Object at_user_info) {
                this.at_user_info = at_user_info;
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

            public UserInfoEntity getUser_info() {
                return user_info;
            }

            public Object getAt_user_info() {
                return at_user_info;
            }

            public static class UserInfoEntity {
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
    }
}