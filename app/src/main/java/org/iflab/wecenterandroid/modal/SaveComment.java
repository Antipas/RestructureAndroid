package org.iflab.wecenterandroid.modal;

/**
 * Created by Lyn on 16/1/2.
 */
public class SaveComment {


    /**
     * rsm : {"comment_id":"40"}
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
         * comment_id : 40
         */

        private String comment_id;

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getComment_id() {
            return comment_id;
        }
    }
}
