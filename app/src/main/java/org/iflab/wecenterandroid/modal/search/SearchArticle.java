package org.iflab.wecenterandroid.modal.search;

/**
 * Created by Lyn on 16/2/10.
 */
public class SearchArticle implements Search{

    /**
     * type : articles
     * search_id : 21
     * name : 想想当年经济大萧条，这次股市暴跌也是个警示
     * detail : {"comments":0,"views":63}
     */

    private String type;
    private int search_id;
    private String name;
    /**
     * comments : 0
     * views : 63
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

    public static class DetailEntity {
        private int comments;
        private int views;

        public void setComments(int comments) {
            this.comments = comments;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getComments() {
            return comments;
        }

        public int getViews() {
            return views;
        }
    }
}
