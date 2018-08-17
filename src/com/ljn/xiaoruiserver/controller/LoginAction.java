package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.Users;
import com.ljn.xiaoruiserver.util.SecretKey;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

/**
 * Created by 23381 on 2018/8/16.
 */
@IocBean
public class LoginAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("login")
    @GET
    @POST
    public Object doLogin(@Param("userPhone")String userPhone, @Param("psw")String password){
        try{
            NutMap re = new NutMap();
            if(userPhone!=null&&password!=null) {
                Users u = dao.fetch(Users.class, Cnd.where("user_phone", "=", userPhone).and("psw", "=", password));
                if (u!=null) {
                    String secretKey = SecretKey.getSecretKey();
                    u.setSecretKey(secretKey);
                    dao.update(u);
                    re.put("status", 1);
                    re.put("msg", "OK");
                    re.put("secretKey", secretKey);
                    re.put("userId", u.getUserId());
//                    re.put("userPhone", u.getUserPhone());
                } else {
                    re.put("status", 0);
                    re.put("msg", "账号或密码错误");
                }
            }else{
                re.put("status", 0);
                re.put("msg", "账号或密码错误");
            }
            return re;
        }catch (Exception e){
            NutMap re = new NutMap();
            re.put("status", 0);
            re.put("msg", "error in login");
            return re;
        }
    }
}
