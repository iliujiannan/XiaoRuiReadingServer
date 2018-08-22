package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.Purse;
import com.ljn.xiaoruiserver.bean.Users;
import com.ljn.xiaoruiserver.bean.Checkcode;
import com.ljn.xiaoruiserver.util.DateUtil;
import com.ljn.xiaoruiserver.util.Message;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import java.io.IOException;
import java.util.Date;

/**
 * Created by 23381 on 2018/8/16.
 */
@IocBean
public class RegisterAction {
    @Inject
    Dao dao;

    @Ok("json")
    @Fail("http:500")
    @At("register")
    @GET
    @POST
    public Object register(@Param("userPhone") String userPhone, @Param("psw") String password, @Param("repsw") String rePassword) {
        NutMap re = new NutMap();
        if (userPhone != null && password != null && rePassword != null) {
            Users u = dao.fetch(Users.class, Cnd.where("user_phone", "=", userPhone));
            if (u != null) {
                re.put("status", 0);
                re.put("msg", "您已注册过，请登录！");
            } else {
                if (password.equals(rePassword)) {
                    Users user = new Users();
                    user.setUserPhone(userPhone);
                    user.setPsw(password);
                    user.setUserNickName("user_" + userPhone);
                    user.setUserPhoto("img/sys_photo.jpg");
                    user.setUserType(0);
                    user.setUserReadDailly(0);
                    user.setUserReadTotally(0);
                    dao.insert(user);
                    Purse p=new Purse();
                    p.setUserId(user.getUserId());
                    p.setPurseMoney(0);
                    dao.insert(p);
                    re.put("status", 1);
                    re.put("msg", "注册成功");
                } else {
                    re.put("status", 0);
                    re.put("msg", "两次密码不一致");
                }
            }
        } else {
            re.put("status", 0);
            re.put("msg", "输入信息不合法");
        }
        return re;
    }

    @Ok("json")
    @Fail("http:500")
    @At("get_check_code")
    @GET
    @POST
    public Object getcheckcode(@Param("userPhone") String userPhone) {
        NutMap re = new NutMap();
        Message cc = new Message();
        Checkcode checkcode;
        String code;
        checkcode = dao.fetch(Checkcode.class, Cnd.where("phone", "=", userPhone));
        if (checkcode != null) {
            long dis = DateUtil.getDistanceTimeInMinute(checkcode.getLastTime(), DateUtil.dateToString(new Date()));
            System.out.println(dis);
            if (dis >= 1) {
                try {
                    code = cc.mSsendMessage(userPhone);
                    checkcode.setCode(code);
                    checkcode.setLastTime(DateUtil.dateToString(new Date()));
                    dao.update(checkcode);
                    re.put("status", 1);
                    re.put("msg", "获取验证码成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                re.put("status", 0);
                re.put("msg", "请不要短时间内重复获取");
            }
        } else {
//                code = cc.mSsendMessage(userPhone);
            code="6789";
            checkcode = new Checkcode();
            checkcode.setCode(code);
            checkcode.setPhone(userPhone);
            checkcode.setLastTime(DateUtil.dateToString(new Date()));

            dao.insert(checkcode);
            re.put("status", 1);
            re.put("msg", "获取验证码成功");
        }
        return re;
    }
}
