package org.iflab.wecenterandroid.modal.search;

/**
 * Created by Lyn on 16/2/10.
 */
public class SearchUser implements Search {

    /**
     * type : users
     * search_id : 60
     * name : lyn
     * detail : {"avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/60_avatar_mid.jpg","signature":"不啦不啦","reputation":3,"agree_count":1,"thanks_count":0,"fans_count":0}
     */

    private String type;
    private int search_id;
    private String name;
    /**
     * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/60_avatar_mid.jpg
     * signature : 不啦不啦
     * reputation : 3
     * agree_count : 1
     * thanks_count : 0
     * fans_count : 0
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
        private String avatar_file;
        private String signature;
        private int reputation;
        private int agree_count;
        private int thanks_count;
        private int fans_count;

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public void setReputation(int reputation) {
            this.reputation = reputation;
        }

        public void setAgree_count(int agree_count) {
            this.agree_count = agree_count;
        }

        public void setThanks_count(int thanks_count) {
            this.thanks_count = thanks_count;
        }

        public void setFans_count(int fans_count) {
            this.fans_count = fans_count;
        }

        public String getAvatar_file() {
            return avatar_file;
        }

        public String getSignature() {
            return signature;
        }

        public int getReputation() {
            return reputation;
        }

        public int getAgree_count() {
            return agree_count;
        }

        public int getThanks_count() {
            return thanks_count;
        }

        public int getFans_count() {
            return fans_count;
        }
    }
}
