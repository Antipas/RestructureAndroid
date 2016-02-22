package org.iflab.wecenterandroid.modal.person;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.util.RoundedTransformation;

import java.util.List;

/**
 * Created by Lyn on 16/1/31.
 */
public class PersonFollow   {

    /**
     * rsm : {"total_rows":10,"rows":[{"uid":25,"user_name":"Daflsh","agree_count":0,"thanks_count":0,"reputation":0,"signature":null,"avatar_file":"http://wecenter.dev.hihwei.com/static/common/avatar-mid-img.png"}]}
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
         * total_rows : 10
         * rows : [{"uid":25,"user_name":"Daflsh","agree_count":0,"thanks_count":0,"reputation":0,"signature":null,"avatar_file":"http://wecenter.dev.hihwei.com/static/common/avatar-mid-img.png"}]
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

        public static class RowsEntity extends BaseObservable implements PersonInfo{
            /**
             * uid : 25
             * user_name : Daflsh
             * agree_count : 0
             * thanks_count : 0
             * reputation : 0
             * signature : null
             * avatar_file : http://wecenter.dev.hihwei.com/static/common/avatar-mid-img.png
             */

            private int uid;
            private String user_name;
            private int agree_count;
            private int thanks_count;
            private int reputation;
            private Object signature;
            private String avatar_file;

            @BindingAdapter({"bind:followFansAvatarUrl"})
            public static void loadImage(ImageView view, String url) {
                Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
            }

            @Override
            public int getType() {
                return PersonInfo.FOLLOW;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public void setAgree_count(int agree_count) {
                this.agree_count = agree_count;
            }

            public void setThanks_count(int thanks_count) {
                this.thanks_count = thanks_count;
            }

            public void setReputation(int reputation) {
                this.reputation = reputation;
            }

            public void setSignature(Object signature) {
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

            public int getAgree_count() {
                return agree_count;
            }

            public int getThanks_count() {
                return thanks_count;
            }

            public int getReputation() {
                return reputation;
            }

            public Object getSignature() {
                return signature;
            }

            public String getAvatar_file() {
                return avatar_file;
            }
        }
    }
}
