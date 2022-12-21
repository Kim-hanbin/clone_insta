package kr.prd.web.user.entity;

import java.util.Date;

public class UserVO {

    @Override
    public String toString() {
        return "UserVO{" +
                "seq=" + seq +
                ", user_id='" + user_id + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_passwd='" + user_passwd + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_role_id='" + user_role_id + '\'' +
                ", create_time=" + create_time +
                '}';
    }

    private int seq;
    private String user_id;
    private String user_email;
    private String user_passwd;
    private String user_name;
    private String user_role_id;
    private Date create_time;


    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_passwd() {
        return user_passwd;
    }

    public void setUser_passwd(String user_passwd) {
        this.user_passwd = user_passwd;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(String user_role_id) {
        this.user_role_id = user_role_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }


}
