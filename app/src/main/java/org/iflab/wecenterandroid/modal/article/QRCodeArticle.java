package org.iflab.wecenterandroid.modal.article;

/**
 * Created by Lyn on 16/4/9.
 */
public class QRCodeArticle {

    /**
     * article_id : 127
     */

    private RsmEntity rsm;
    /**
     * rsm : {"article_id":"127"}
     * errno : 1
     * err : null
     */

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
        private int article_id;

        public void setArticle_id(int article_id) {
            this.article_id = article_id;
        }

        public int getArticle_id() {
            return article_id;
        }
    }
}
