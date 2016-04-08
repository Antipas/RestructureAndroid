package org.iflab.wecenterandroid.modal;

/**
 * Created by lyn on 16-4-8.
 */
public class FocusPeople {

    /**
     * type : remove
     */

    private RsmBean rsm;
    /**
     * rsm : {"type":"remove"}
     * errno : 1
     * err : null
     */

    private int errno;
    private Object err;

    public RsmBean getRsm() {
        return rsm;
    }

    public void setRsm(RsmBean rsm) {
        this.rsm = rsm;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public Object getErr() {
        return err;
    }

    public void setErr(Object err) {
        this.err = err;
    }

    public static class RsmBean {
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
