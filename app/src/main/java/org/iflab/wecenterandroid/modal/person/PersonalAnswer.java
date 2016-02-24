package org.iflab.wecenterandroid.modal.person;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.util.RoundedTransformation;

import java.util.List;

/**
 * Created by Lyn on 16/1/31.
 */

public class PersonalAnswer{


    /**
     * rsm : {"total_rows":4,"rows":[{"history_id":4259,"associate_action":201,"add_time":1448411258,"answer_info":{"answer_id":299,"answer_content":"shiba ","add_time":1448411258,"against_count":0,"agree_count":0},"question_info":{"question_id":164,"question_content":"听说横店集团旗下有600多家子公司，都涉及哪些产业呢？","add_time":1444022522,"update_time":1452602780,"answer_count":5,"agree_count":6}}]}
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

    public static class RsmEntity {
        /**
         * total_rows : 4
         * rows : [{"history_id":4259,"associate_action":201,"add_time":1448411258,"answer_info":{"answer_id":299,"answer_content":"shiba ","add_time":1448411258,"against_count":0,"agree_count":0},"question_info":{"question_id":164,"question_content":"听说横店集团旗下有600多家子公司，都涉及哪些产业呢？","add_time":1444022522,"update_time":1452602780,"answer_count":5,"agree_count":6}}]
         */

        private int total_rows;
        private List<RowsEntity> rows;

        public void setTotal_rows(int total_rows) {
            this.total_rows = total_rows;
        }

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public int getTotal_rows() {
            return total_rows;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public static class RowsEntity implements PersonInfo{
            /**
             * history_id : 4259
             * associate_action : 201
             * add_time : 1448411258
             * answer_info : {"answer_id":299,"answer_content":"shiba ","add_time":1448411258,"against_count":0,"agree_count":0}
             * question_info : {"question_id":164,"question_content":"听说横店集团旗下有600多家子公司，都涉及哪些产业呢？","add_time":1444022522,"update_time":1452602780,"answer_count":5,"agree_count":6}
             */

            private int history_id;
            private int associate_action;
            private int add_time;
            private AnswerInfoEntity answer_info;
            private QuestionInfoEntity question_info;

            @BindingAdapter({"bind:answerItemAvatar"})
            public static void loadImage(ImageView view, String url) {
                Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
            }

            @BindingAdapter({"bind:answerItemName"})
            public static void setUserName(TextView view, String text) {
                view.setText(text);
            }

            public void setHistory_id(int history_id) {
                this.history_id = history_id;
            }

            public void setAssociate_action(int associate_action) {
                this.associate_action = associate_action;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
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

            public AnswerInfoEntity getAnswer_info() {
                return answer_info;
            }

            public QuestionInfoEntity getQuestion_info() {
                return question_info;
            }

            @Override
            public int getType() {
                return PersonInfo.ANSWER;
            }

            public static class AnswerInfoEntity {
                /**
                 * answer_id : 299
                 * answer_content : shiba
                 * add_time : 1448411258
                 * against_count : 0
                 * agree_count : 0
                 */

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
                /**
                 * question_id : 164
                 * question_content : 听说横店集团旗下有600多家子公司，都涉及哪些产业呢？
                 * add_time : 1444022522
                 * update_time : 1452602780
                 * answer_count : 5
                 * agree_count : 6
                 */

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
    }
}
