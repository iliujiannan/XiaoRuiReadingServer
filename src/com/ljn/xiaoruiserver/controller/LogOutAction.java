package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.Users;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

/**
 * Created by 23381 on 2018/8/17.
 */
@IocBean
public class LogOutAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("log_out")
    @GET
    @POST
    public Object logOut(@Param("userId") Integer userId,@Param("secretKey") String secretKey){
        NutMap re=new NutMap();
        if(secretKey!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",userId));
            u.setSecretKey("");
            dao.update(u);
            re.put("status",1);
            re.put("msg","退出登录！");
        }
        else {
            re.put("status",0);
            re.put("msg","您还未登陆！");
        }
        return re;
    }
}
