package org.iflab.wecenterandroid.modal;

/**
 * Created by Lyn on 16/1/19.
 */
public class Attach {


    /**
     * rsm : {"attach_access_key":"c186588de2158ef43d33187ef3073406","attach_id":"39","thumb":"http://wecenter.dev.hihwei.com/uploads/answer/20151112/90x90_affce7cf1b824e23ebe767a45f3b00a0.jpeg"}
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
         * attach_access_key : c186588de2158ef43d33187ef3073406
         * attach_id : 39
         * thumb : http://wecenter.dev.hihwei.com/uploads/answer/20151112/90x90_affce7cf1b824e23ebe767a45f3b00a0.jpeg
         */

        private String attach_access_key;
        private String attach_id;
        private String thumb;

        public void setAttach_access_key(String attach_access_key) {
            this.attach_access_key = attach_access_key;
        }

        public void setAttach_id(String attach_id) {
            this.attach_id = attach_id;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getAttach_access_key() {
            return attach_access_key;
        }

        public String getAttach_id() {
            return attach_id;
        }

        public String getThumb() {
            return thumb;
        }
    }
}
