package org.iflab.wecenterandroid.modal;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.util.RoundedTransformation;

import java.util.List;

/**
 * Created by Lyn on 15/12/21.
 */
public class Question {

    /**
     * rsm : {"question_info":{"question_id":162,"question_content":"阿里九月份宣布杭州北京双总部，意图何在？","question_detail":"","add_time":1443680912,"update_time":1447898441,"answer_count":3,"view_count":75,"focus_count":4,"comment_count":0,"agree_count":5,"against_count":0,"thanks_count":0,"user_info":{"uid":4,"user_name":"Carol","signature":"审计民工","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/04_avatar_mid.jpg"},"user_answered":0,"user_follow_check":1,"user_question_focus":0,"user_thanks":0},"question_topics":[{"topic_id":247,"topic_title":"阿里"}],"answers":[{"answer_id":280,"question_id":162,"answer_content":"阿里宣布\u201c杭州北京\u201d双主场战略后，准备在北京为中心的北方地区大干一场。此举的目的普遍被解读为针对京东而来，但阿里官方给出的说法是其业务的三分一都已在北京，包括阿里音乐、阿里云、UC优视、高德地图、阿里","add_time":1443681524,"against_count":0,"agree_count":3,"uid":19,"comment_count":0,"uninterested_count":0,"thanks_count":1,"category_id":1,"has_attach":0,"ip":1034167026,"force_fold":0,"anonymous":0,"publish_source":null,"user_info":{"uid":19,"user_name":"放肆","signature":"好困。。。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/19_avatar_mid.jpg"},"user_rated_thanks":null,"user_rated_uninterested":null,"agree_status":"0"}]}
     * errno : 1
     * err : null
     */

    private RsmEntity rsm;
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

    public static class RsmEntity implements IAdapterType {
        /**
         * question_info : {"question_id":162,"question_content":"阿里九月份宣布杭州北京双总部，意图何在？","question_detail":"","add_time":1443680912,"update_time":1447898441,"answer_count":3,"view_count":75,"focus_count":4,"comment_count":0,"agree_count":5,"against_count":0,"thanks_count":0,"user_info":{"uid":4,"user_name":"Carol","signature":"审计民工","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/04_avatar_mid.jpg"},"user_answered":0,"user_follow_check":1,"user_question_focus":0,"user_thanks":0}
         * question_topics : [{"topic_id":247,"topic_title":"阿里"}]
         * answers : [{"answer_id":280,"question_id":162,"answer_content":"阿里宣布\u201c杭州北京\u201d双主场战略后，准备在北京为中心的北方地区大干一场。此举的目的普遍被解读为针对京东而来，但阿里官方给出的说法是其业务的三分一都已在北京，包括阿里音乐、阿里云、UC优视、高德地图、阿里","add_time":1443681524,"against_count":0,"agree_count":3,"uid":19,"comment_count":0,"uninterested_count":0,"thanks_count":1,"category_id":1,"has_attach":0,"ip":1034167026,"force_fold":0,"anonymous":0,"publish_source":null,"user_info":{"uid":19,"user_name":"放肆","signature":"好困。。。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/19_avatar_mid.jpg"},"user_rated_thanks":null,"user_rated_uninterested":null,"agree_status":"0"}]
         */

        private QuestionInfoEntity question_info;
        private List<QuestionTopicsEntity> question_topics;
        private List<AnswersEntity> answers;

        public void setQuestion_info(QuestionInfoEntity question_info) {
            this.question_info = question_info;
        }

        public void setQuestion_topics(List<QuestionTopicsEntity> question_topics) {
            this.question_topics = question_topics;
        }

        public void setAnswers(List<AnswersEntity> answers) {
            this.answers = answers;
        }

        public QuestionInfoEntity getQuestion_info() {
            return question_info;
        }

        public List<QuestionTopicsEntity> getQuestion_topics() {
            return question_topics;
        }

        public List<AnswersEntity> getAnswers() {
            return answers;
        }

        @Override
        public int getType() {
            return IAdapterType.HEADER;
        }

        public static class QuestionInfoEntity {
            /**
             * question_id : 162
             * question_content : 阿里九月份宣布杭州北京双总部，意图何在？
             * question_detail :
             * add_time : 1443680912
             * update_time : 1447898441
             * answer_count : 3
             * view_count : 75
             * focus_count : 4
             * comment_count : 0
             * agree_count : 5
             * against_count : 0
             * thanks_count : 0
             * user_info : {"uid":4,"user_name":"Carol","signature":"审计民工","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/04_avatar_mid.jpg"}
             * user_answered : 0
             * user_follow_check : 1
             * user_question_focus : 0
             * user_thanks : 0
             */

            private int question_id;
            private String question_content;
            private String question_detail;
            private int add_time;
            private int update_time;
            private int answer_count;
            private int view_count;
            private int focus_count;
            private int comment_count;
            private int agree_count;
            private int against_count;
            private int thanks_count;
            private UserInfoEntity user_info;
            private int user_answered;
            private int user_follow_check;
            private int user_question_focus;
            private int user_thanks;

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
            }

            public void setQuestion_content(String question_content) {
                this.question_content = question_content;
            }

            public void setQuestion_detail(String question_detail) {
                this.question_detail = question_detail;
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

            public void setView_count(int view_count) {
                this.view_count = view_count;
            }

            public void setFocus_count(int focus_count) {
                this.focus_count = focus_count;
            }

            public void setComment_count(int comment_count) {
                this.comment_count = comment_count;
            }

            public void setAgree_count(int agree_count) {
                this.agree_count = agree_count;
            }

            public void setAgainst_count(int against_count) {
                this.against_count = against_count;
            }

            public void setThanks_count(int thanks_count) {
                this.thanks_count = thanks_count;
            }

            public void setUser_info(UserInfoEntity user_info) {
                this.user_info = user_info;
            }

            public void setUser_answered(int user_answered) {
                this.user_answered = user_answered;
            }

            public void setUser_follow_check(int user_follow_check) {
                this.user_follow_check = user_follow_check;
            }

            public void setUser_question_focus(int user_question_focus) {
                this.user_question_focus = user_question_focus;
            }

            public void setUser_thanks(int user_thanks) {
                this.user_thanks = user_thanks;
            }

            public int getQuestion_id() {
                return question_id;
            }

            public String getQuestion_content() {
                return question_content;
            }

            public String getQuestion_detail() {
                return question_detail;
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

            public int getView_count() {
                return view_count;
            }

            public int getFocus_count() {
                return focus_count;
            }

            public int getComment_count() {
                return comment_count;
            }

            public int getAgree_count() {
                return agree_count;
            }

            public int getAgainst_count() {
                return against_count;
            }

            public int getThanks_count() {
                return thanks_count;
            }

            public UserInfoEntity getUser_info() {
                return user_info;
            }

            public int getUser_answered() {
                return user_answered;
            }

            public int getUser_follow_check() {
                return user_follow_check;
            }

            public int getUser_question_focus() {
                return user_question_focus;
            }

            public int getUser_thanks() {
                return user_thanks;
            }

            public static class UserInfoEntity {
                /**
                 * uid : 4
                 * user_name : Carol
                 * signature : 审计民工
                 * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/04_avatar_mid.jpg
                 */

                private int uid;
                private String user_name;
                private String signature;
                private String avatar_file;

                @BindingAdapter({"bind:userInfoItemAvatar"})
                public static void loadUserInfoItemAvatar(ImageView view, String url) {
                    Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
                }

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
        }

        public static class QuestionTopicsEntity {
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

        public static class AnswersEntity implements IAdapterType {
            /**
             * answer_id : 280
             * question_id : 162
             * answer_content : 阿里宣布“杭州北京”双主场战略后，准备在北京为中心的北方地区大干一场。此举的目的普遍被解读为针对京东而来，但阿里官方给出的说法是其业务的三分一都已在北京，包括阿里音乐、阿里云、UC优视、高德地图、阿里
             * add_time : 1443681524
             * against_count : 0
             * agree_count : 3
             * uid : 19
             * comment_count : 0
             * uninterested_count : 0
             * thanks_count : 1
             * category_id : 1
             * has_attach : 0
             * ip : 1034167026
             * force_fold : 0
             * anonymous : 0
             * publish_source : null
             * user_info : {"uid":19,"user_name":"放肆","signature":"好困。。。","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/19_avatar_mid.jpg"}
             * user_rated_thanks : null
             * user_rated_uninterested : null
             * agree_status : 0
             */

            private int answer_id;
            private int question_id;
            private String answer_content;
            private int add_time;
            private int against_count;
            private int agree_count;
            private int uid;
            private int comment_count;
            private int uninterested_count;
            private int thanks_count;
            private int category_id;
            private int has_attach;
            private long ip;
            private int force_fold;
            private int anonymous;
            private Object publish_source;
            private UserInfoEntity user_info;
            private Object user_rated_thanks;
            private Object user_rated_uninterested;
            private String agree_status;

            @BindingAdapter({"bind:questionAnswerItemAvatar"})
            public static void loadQuestionAnswerItemAvatar(ImageView view, String url) {
                Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
            }

            public void setAnswer_id(int answer_id) {
                this.answer_id = answer_id;
            }

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
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

            public void setUid(int uid) {
                this.uid = uid;
            }

            public void setComment_count(int comment_count) {
                this.comment_count = comment_count;
            }

            public void setUninterested_count(int uninterested_count) {
                this.uninterested_count = uninterested_count;
            }

            public void setThanks_count(int thanks_count) {
                this.thanks_count = thanks_count;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public void setHas_attach(int has_attach) {
                this.has_attach = has_attach;
            }

            public void setIp(long ip) {
                this.ip = ip;
            }

            public void setForce_fold(int force_fold) {
                this.force_fold = force_fold;
            }

            public void setAnonymous(int anonymous) {
                this.anonymous = anonymous;
            }

            public void setPublish_source(Object publish_source) {
                this.publish_source = publish_source;
            }

            public void setUser_info(UserInfoEntity user_info) {
                this.user_info = user_info;
            }

            public void setUser_rated_thanks(Object user_rated_thanks) {
                this.user_rated_thanks = user_rated_thanks;
            }

            public void setUser_rated_uninterested(Object user_rated_uninterested) {
                this.user_rated_uninterested = user_rated_uninterested;
            }

            public void setAgree_status(String agree_status) {
                this.agree_status = agree_status;
            }

            public int getAnswer_id() {
                return answer_id;
            }

            public int getQuestion_id() {
                return question_id;
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

            public int getUid() {
                return uid;
            }

            public int getComment_count() {
                return comment_count;
            }

            public int getUninterested_count() {
                return uninterested_count;
            }

            public int getThanks_count() {
                return thanks_count;
            }

            public int getCategory_id() {
                return category_id;
            }

            public int getHas_attach() {
                return has_attach;
            }

            public long getIp() {
                return ip;
            }

            public int getForce_fold() {
                return force_fold;
            }

            public int getAnonymous() {
                return anonymous;
            }

            public Object getPublish_source() {
                return publish_source;
            }

            public UserInfoEntity getUser_info() {
                return user_info;
            }

            public Object getUser_rated_thanks() {
                return user_rated_thanks;
            }

            public Object getUser_rated_uninterested() {
                return user_rated_uninterested;
            }

            public String getAgree_status() {
                return agree_status;
            }

            @Override
            public int getType() {
                return IAdapterType.CONTENT;
            }

            public static class UserInfoEntity {
                /**
                 * uid : 19
                 * user_name : 放肆
                 * signature : 好困。。。
                 * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/19_avatar_mid.jpg
                 */

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
        }
    }
}
