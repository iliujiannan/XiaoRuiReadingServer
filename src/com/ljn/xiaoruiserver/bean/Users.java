package com.ljn.xiaoruiserver.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by 23381 on 2018/8/16.
 */

@Table("xrr_users")
public class Users {
    @Id
    private Integer userId;
    @Column("user_phone")
    private String userPhone;
    @Column("user_photo")
    private String userPhoto;
    @Column("user_nickname")
    private String userNickName;
    @Column("psw")
    private String psw;
    @Column("secret_key")
    private String secretKey;
    @Column("user_type")
    private Integer userType;
    @Column("user_read_daily")
    private Integer userReadDailly;
    @Column("user_read_totally")
    private Integer userReadTotally;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserReadDailly() {
        return userReadDailly;
    }

    public void setUserReadDailly(Integer userReadDailly) {
        this.userReadDailly = userReadDailly;
    }

    public Integer getUserReadTotally() {
        return userReadTotally;
    }

    public void setUserReadTotally(Integer userReadTotally) {
        this.userReadTotally = userReadTotally;
    }
}
