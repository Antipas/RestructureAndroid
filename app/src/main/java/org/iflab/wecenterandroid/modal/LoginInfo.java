package org.iflab.wecenterandroid.modal;

/**
 * Created by Lyn on 15/11/15.
 */
public class LoginInfo {


    /**
     * rsm : {"uid":1,"user_name":"Hwei","avatar_file":"http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/01_avatar_max.jpg","valid_email":1,"is_first_login":0}
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
         * uid : 1
         * user_name : Hwei
         * avatar_file : http://wecenter.dev.hihwei.com/uploads/avatar/000/00/00/01_avatar_max.jpg
         * valid_email : 1
         * is_first_login : 0
         */

        private int uid;
        private String user_name;
        private String avatar_file;
        private int valid_email;
        private int is_first_login;

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }

        public void setValid_email(int valid_email) {
            this.valid_email = valid_email;
        }

        public void setIs_first_login(int is_first_login) {
            this.is_first_login = is_first_login;
        }

        public int getUid() {
            return uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getAvatar_file() {
            return avatar_file;
        }

        public int getValid_email() {
            return valid_email;
        }

        public int getIs_first_login() {
            return is_first_login;
        }

        @Override
        public String toString() {
            return "RsmEntity{" +
                    "uid=" + uid +
                    ", user_name='" + user_name + '\'' +
                    ", avatar_file='" + avatar_file + '\'' +
                    ", valid_email=" + valid_email +
                    ", is_first_login=" + is_first_login +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "rsm=" + rsm.toString() +
                ", errno=" + errno +
                ", err=" + err +
                '}';
    }
}
