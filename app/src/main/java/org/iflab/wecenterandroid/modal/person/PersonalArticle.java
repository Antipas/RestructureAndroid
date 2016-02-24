package org.iflab.wecenterandroid.modal.person;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.modal.prefs.UserPrefs;
import org.iflab.wecenterandroid.util.RoundedTransformation;

import java.util.List;

/**
 * Created by Lyn on 16/1/31.
 */

public class PersonalArticle{


    /**
     * rsm : {"total_rows":2,"rows":[{"history_id":4578,"associate_action":501,"add_time":1453981788,"article_info":{"id":57,"title":"啦啦啦","message":"&lt;p dir=&quot;ltr&quot;&gt;14555&lt;/p&gt;\n[attach]77[/attach]&lt;p dir=&quot;ltr&quot;&gt;&amp;#21734;&amp;#37324;&amp;#21679;&amp;#20154;&amp;#38506;&lt;/p&gt;\n[attach]78[/attach]","comments":0,"views":5,"add_time":1453981788}},{"history_id":4577,"associate_action":501,"add_time":1453903733,"article_info":{"id":56,"title":"啦啦啦","message":"[attach]76[/attach]","comments":0,"views":10,"add_time":1453903732}}]}
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
         * total_rows : 2
         * rows : [{"history_id":4578,"associate_action":501,"add_time":1453981788,"article_info":{"id":57,"title":"啦啦啦","message":"&lt;p dir=&quot;ltr&quot;&gt;14555&lt;/p&gt;\n[attach]77[/attach]&lt;p dir=&quot;ltr&quot;&gt;&amp;#21734;&amp;#37324;&amp;#21679;&amp;#20154;&amp;#38506;&lt;/p&gt;\n[attach]78[/attach]","comments":0,"views":5,"add_time":1453981788}},{"history_id":4577,"associate_action":501,"add_time":1453903733,"article_info":{"id":56,"title":"啦啦啦","message":"[attach]76[/attach]","comments":0,"views":10,"add_time":1453903732}}]
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
             * history_id : 4578
             * associate_action : 501
             * add_time : 1453981788
             * article_info : {"id":57,"title":"啦啦啦","message":"&lt;p dir=&quot;ltr&quot;&gt;14555&lt;/p&gt;\n[attach]77[/attach]&lt;p dir=&quot;ltr&quot;&gt;&amp;#21734;&amp;#37324;&amp;#21679;&amp;#20154;&amp;#38506;&lt;/p&gt;\n[attach]78[/attach]","comments":0,"views":5,"add_time":1453981788}
             */

            private int history_id;
            private int associate_action;
            private int add_time;
            private ArticleInfoEntity article_info;

            @BindingAdapter({"bind:articleItemAvatar"})
            public static void loadImage(ImageView view, String url) {
                Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
            }

            @BindingAdapter({"bind:articleItemName"})
            public static void setUserName(TextView view, String text) {
                view.setText(text);
            }

            @Override
            public int getType() {
                return PersonInfo.ARTICLE;
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

            public void setArticle_info(ArticleInfoEntity article_info) {
                this.article_info = article_info;
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

            public ArticleInfoEntity getArticle_info() {
                return article_info;
            }

            public static class ArticleInfoEntity {
                /**
                 * id : 57
                 * title : 啦啦啦
                 * message : &lt;p dir=&quot;ltr&quot;&gt;14555&lt;/p&gt;
                 [attach]77[/attach]&lt;p dir=&quot;ltr&quot;&gt;&amp;#21734;&amp;#37324;&amp;#21679;&amp;#20154;&amp;#38506;&lt;/p&gt;
                 [attach]78[/attach]
                 * comments : 0
                 * views : 5
                 * add_time : 1453981788
                 */

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
        }
    }

}
