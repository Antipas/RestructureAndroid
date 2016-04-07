package org.iflab.wecenterandroid.modal.explore;

import java.util.List;

/**
 * Created by lyn on 16-4-6.
 */
public class Famous {

    /**
     * total_rows : 6
     * rows : [{"uid":9,"user_name":"iflab.org","email":"1457769286@example.com","mobile":"","avatar_file":null,"sex":0,"birthday":null,"province":" ","city":null,"job_id":0,"reg_time":1457769286,"reg_ip":2093971546,"last_login":1457769286,"last_ip":2093971546,"online_time":0,"last_active":null,"notification_unread":11,"inbox_unread":0,"inbox_recv":0,"fans_count":2,"friend_count":1,"invite_count":0,"article_count":1,"question_count":0,"answer_count":0,"topic_focus_count":0,"invitation_available":5,"group_id":100,"reputation_group":0,"forbidden":0,"valid_email":1,"is_first_login":0,"agree_count":0,"thanks_count":0,"views_count":10,"reputation":0,"reputation_update_time":1459709299,"weibo_visit":1,"integral":2000,"draft_count":null,"common_email":null,"url_token":"iflab.org","url_token_update":0,"verified":null,"default_timezone":null,"email_settings":{"FOLLOW_ME":"N","NEW_ANSWER":"N"},"weixin_settings":"","recent_topics":null,"has_focus":0}]
     */

    private RsmBean rsm;
    /**
     * rsm : {"total_rows":6,"rows":[{"uid":9,"user_name":"iflab.org","email":"1457769286@example.com","mobile":"","avatar_file":null,"sex":0,"birthday":null,"province":" ","city":null,"job_id":0,"reg_time":1457769286,"reg_ip":2093971546,"last_login":1457769286,"last_ip":2093971546,"online_time":0,"last_active":null,"notification_unread":11,"inbox_unread":0,"inbox_recv":0,"fans_count":2,"friend_count":1,"invite_count":0,"article_count":1,"question_count":0,"answer_count":0,"topic_focus_count":0,"invitation_available":5,"group_id":100,"reputation_group":0,"forbidden":0,"valid_email":1,"is_first_login":0,"agree_count":0,"thanks_count":0,"views_count":10,"reputation":0,"reputation_update_time":1459709299,"weibo_visit":1,"integral":2000,"draft_count":null,"common_email":null,"url_token":"iflab.org","url_token_update":0,"verified":null,"default_timezone":null,"email_settings":{"FOLLOW_ME":"N","NEW_ANSWER":"N"},"weixin_settings":"","recent_topics":null,"has_focus":0}]}
     * errno : 1
     * err : null
     */

    private int errno;
    private Object err;

    public RsmBean getRsm() {
        return rsm;
    }

    public void setRsm(RsmBean rsm) {
        this.rsm = rsm;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public Object getErr() {
        return err;
    }

    public void setErr(Object err) {
        this.err = err;
    }


    public static class RsmBean {
        private int total_rows;
        /**
         * uid : 9
         * user_name : iflab.org
         * email : 1457769286@example.com
         * mobile :
         * avatar_file : null
         * sex : 0
         * birthday : null
         * province :
         * city : null
         * job_id : 0
         * reg_time : 1457769286
         * reg_ip : 2093971546
         * last_login : 1457769286
         * last_ip : 2093971546
         * online_time : 0
         * last_active : null
         * notification_unread : 11
         * inbox_unread : 0
         * inbox_recv : 0
         * fans_count : 2
         * friend_count : 1
         * invite_count : 0
         * article_count : 1
         * question_count : 0
         * answer_count : 0
         * topic_focus_count : 0
         * invitation_available : 5
         * group_id : 100
         * reputation_group : 0
         * forbidden : 0
         * valid_email : 1
         * is_first_login : 0
         * agree_count : 0
         * thanks_count : 0
         * views_count : 10
         * reputation : 0
         * reputation_update_time : 1459709299
         * weibo_visit : 1
         * integral : 2000
         * draft_count : null
         * common_email : null
         * url_token : iflab.org
         * url_token_update : 0
         * verified : null
         * default_timezone : null
         * email_settings : {"FOLLOW_ME":"N","NEW_ANSWER":"N"}
         * weixin_settings :
         * recent_topics : null
         * has_focus : 0
         */

        private List<RowsBean> rows;

        public int getTotal_rows() {
            return total_rows;
        }

        public void setTotal_rows(int total_rows) {
            this.total_rows = total_rows;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean  implements Explore{
            private int uid;
            private String user_name;
            private String email;
            private String mobile;
            private String avatar_file;
            private int sex;
            private Object birthday;
            private String province;
            private Object city;
            private int job_id;
            private int reg_time;
            private int reg_ip;
            private int last_login;
            private int last_ip;
            private int online_time;
            private Object last_active;
            private int notification_unread;
            private int inbox_unread;
            private int inbox_recv;
            private int fans_count;
            private int friend_count;
            private int invite_count;
            private int article_count;
            private int question_count;
            private int answer_count;
            private int topic_focus_count;
            private int invitation_available;
            private int group_id;
            private int reputation_group;
            private int forbidden;
            private int valid_email;
            private int is_first_login;
            private int agree_count;
            private int thanks_count;
            private int views_count;
            private int reputation;
            private int reputation_update_time;
            private int weibo_visit;
            private int integral;
            private Object draft_count;
            private Object common_email;
            private String url_token;
            private int url_token_update;
            private Object verified;
            private Object default_timezone;
            /**
             * FOLLOW_ME : N
             * NEW_ANSWER : N
             */

            private EmailSettingsBean email_settings;
            private String weixin_settings;
            private Object recent_topics;
            private int has_focus;

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

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAvatar_file() {
                return avatar_file;
            }

            public void setAvatar_file(String avatar_file) {
                this.avatar_file = avatar_file;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public int getJob_id() {
                return job_id;
            }

            public void setJob_id(int job_id) {
                this.job_id = job_id;
            }

            public int getReg_time() {
                return reg_time;
            }

            public void setReg_time(int reg_time) {
                this.reg_time = reg_time;
            }

            public int getReg_ip() {
                return reg_ip;
            }

            public void setReg_ip(int reg_ip) {
                this.reg_ip = reg_ip;
            }

            public int getLast_login() {
                return last_login;
            }

            public void setLast_login(int last_login) {
                this.last_login = last_login;
            }

            public int getLast_ip() {
                return last_ip;
            }

            public void setLast_ip(int last_ip) {
                this.last_ip = last_ip;
            }

            public int getOnline_time() {
                return online_time;
            }

            public void setOnline_time(int online_time) {
                this.online_time = online_time;
            }

            public Object getLast_active() {
                return last_active;
            }

            public void setLast_active(Object last_active) {
                this.last_active = last_active;
            }

            public int getNotification_unread() {
                return notification_unread;
            }

            public void setNotification_unread(int notification_unread) {
                this.notification_unread = notification_unread;
            }

            public int getInbox_unread() {
                return inbox_unread;
            }

            public void setInbox_unread(int inbox_unread) {
                this.inbox_unread = inbox_unread;
            }

            public int getInbox_recv() {
                return inbox_recv;
            }

            public void setInbox_recv(int inbox_recv) {
                this.inbox_recv = inbox_recv;
            }

            public int getFans_count() {
                return fans_count;
            }

            public void setFans_count(int fans_count) {
                this.fans_count = fans_count;
            }

            public int getFriend_count() {
                return friend_count;
            }

            public void setFriend_count(int friend_count) {
                this.friend_count = friend_count;
            }

            public int getInvite_count() {
                return invite_count;
            }

            public void setInvite_count(int invite_count) {
                this.invite_count = invite_count;
            }

            public int getArticle_count() {
                return article_count;
            }

            public void setArticle_count(int article_count) {
                this.article_count = article_count;
            }

            public int getQuestion_count() {
                return question_count;
            }

            public void setQuestion_count(int question_count) {
                this.question_count = question_count;
            }

            public int getAnswer_count() {
                return answer_count;
            }

            public void setAnswer_count(int answer_count) {
                this.answer_count = answer_count;
            }

            public int getTopic_focus_count() {
                return topic_focus_count;
            }

            public void setTopic_focus_count(int topic_focus_count) {
                this.topic_focus_count = topic_focus_count;
            }

            public int getInvitation_available() {
                return invitation_available;
            }

            public void setInvitation_available(int invitation_available) {
                this.invitation_available = invitation_available;
            }

            public int getGroup_id() {
                return group_id;
            }

            public void setGroup_id(int group_id) {
                this.group_id = group_id;
            }

            public int getReputation_group() {
                return reputation_group;
            }

            public void setReputation_group(int reputation_group) {
                this.reputation_group = reputation_group;
            }

            public int getForbidden() {
                return forbidden;
            }

            public void setForbidden(int forbidden) {
                this.forbidden = forbidden;
            }

            public int getValid_email() {
                return valid_email;
            }

            public void setValid_email(int valid_email) {
                this.valid_email = valid_email;
            }

            public int getIs_first_login() {
                return is_first_login;
            }

            public void setIs_first_login(int is_first_login) {
                this.is_first_login = is_first_login;
            }

            public int getAgree_count() {
                return agree_count;
            }

            public void setAgree_count(int agree_count) {
                this.agree_count = agree_count;
            }

            public int getThanks_count() {
                return thanks_count;
            }

            public void setThanks_count(int thanks_count) {
                this.thanks_count = thanks_count;
            }

            public int getViews_count() {
                return views_count;
            }

            public void setViews_count(int views_count) {
                this.views_count = views_count;
            }

            public int getReputation() {
                return reputation;
            }

            public void setReputation(int reputation) {
                this.reputation = reputation;
            }

            public int getReputation_update_time() {
                return reputation_update_time;
            }

            public void setReputation_update_time(int reputation_update_time) {
                this.reputation_update_time = reputation_update_time;
            }

            public int getWeibo_visit() {
                return weibo_visit;
            }

            public void setWeibo_visit(int weibo_visit) {
                this.weibo_visit = weibo_visit;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public Object getDraft_count() {
                return draft_count;
            }

            public void setDraft_count(Object draft_count) {
                this.draft_count = draft_count;
            }

            public Object getCommon_email() {
                return common_email;
            }

            public void setCommon_email(Object common_email) {
                this.common_email = common_email;
            }

            public String getUrl_token() {
                return url_token;
            }

            public void setUrl_token(String url_token) {
                this.url_token = url_token;
            }

            public int getUrl_token_update() {
                return url_token_update;
            }

            public void setUrl_token_update(int url_token_update) {
                this.url_token_update = url_token_update;
            }

            public Object getVerified() {
                return verified;
            }

            public void setVerified(Object verified) {
                this.verified = verified;
            }

            public Object getDefault_timezone() {
                return default_timezone;
            }

            public void setDefault_timezone(Object default_timezone) {
                this.default_timezone = default_timezone;
            }

            public EmailSettingsBean getEmail_settings() {
                return email_settings;
            }

            public void setEmail_settings(EmailSettingsBean email_settings) {
                this.email_settings = email_settings;
            }

            public String getWeixin_settings() {
                return weixin_settings;
            }

            public void setWeixin_settings(String weixin_settings) {
                this.weixin_settings = weixin_settings;
            }

            public Object getRecent_topics() {
                return recent_topics;
            }

            public void setRecent_topics(Object recent_topics) {
                this.recent_topics = recent_topics;
            }

            public int getHas_focus() {
                return has_focus;
            }

            public void setHas_focus(int has_focus) {
                this.has_focus = has_focus;
            }

            @Override
            public String getPost_type() {
                return Explore.FAMOUS_MEDIA;
            }

            public static class EmailSettingsBean {
                private String FOLLOW_ME;
                private String NEW_ANSWER;

                public String getFOLLOW_ME() {
                    return FOLLOW_ME;
                }

                public void setFOLLOW_ME(String FOLLOW_ME) {
                    this.FOLLOW_ME = FOLLOW_ME;
                }

                public String getNEW_ANSWER() {
                    return NEW_ANSWER;
                }

                public void setNEW_ANSWER(String NEW_ANSWER) {
                    this.NEW_ANSWER = NEW_ANSWER;
                }
            }
        }
    }
}
