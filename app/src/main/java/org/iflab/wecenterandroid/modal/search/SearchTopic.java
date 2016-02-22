package org.iflab.wecenterandroid.modal.search;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.util.RoundedTransformation;

/**
 * Created by Lyn on 16/2/10.
 */
public class SearchTopic implements Search{

    /**
     * type : topics
     * search_id : 60
     * name : 股市
     * detail : {"topic_pic":"http://wecenter.dev.hihwei.com/static/common/topic-mid-img.png","topic_id":60,"focus_count":2,"discuss_count":34,"topic_description":""}
     */

    private String type;
    private int search_id;
    private String name;
    /**
     * topic_pic : http://wecenter.dev.hihwei.com/static/common/topic-mid-img.png
     * topic_id : 60
     * focus_count : 2
     * discuss_count : 34
     * topic_description :
     */

    private DetailEntity detail;

    public void setType(String type) {
        this.type = type;
    }

    public void setSearch_id(int search_id) {
        this.search_id = search_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetail(DetailEntity detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public int getSearch_id() {
        return search_id;
    }

    public String getName() {
        return name;
    }

    public DetailEntity getDetail() {
        return detail;
    }

    @BindingAdapter({"bind:topicPic"})
    public static void topicPic(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
    }

    public static class DetailEntity {
        private String topic_pic;
        private int topic_id;
        private int focus_count;
        private int discuss_count;
        private String topic_description;

        public void setTopic_pic(String topic_pic) {
            this.topic_pic = topic_pic;
        }

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }

        public void setFocus_count(int focus_count) {
            this.focus_count = focus_count;
        }

        public void setDiscuss_count(int discuss_count) {
            this.discuss_count = discuss_count;
        }

        public void setTopic_description(String topic_description) {
            this.topic_description = topic_description;
        }

        public String getTopic_pic() {
            return topic_pic;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public int getFocus_count() {
            return focus_count;
        }

        public int getDiscuss_count() {
            return discuss_count;
        }

        public String getTopic_description() {
            return topic_description;
        }
    }
}
