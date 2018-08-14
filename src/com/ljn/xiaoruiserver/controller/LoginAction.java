package com.ljn.xiaoruiserver.controller;


import com.ljn.xiaoruiserver.bean.User;
import com.ljn.xiaoruiserver.filter.RequestFilter;
import com.ljn.xiaoruiserver.util.SecretKey;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 12390 on 2017/9/22.
 */
@IocBean
public class LoginAction {
    private Log log = Logs.get();

    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("login")
    @GET
    @POST
    public Object doLogin(@Param("userName")String userName, @Param("psw")String password, @Param("host") String host){
        HttpServletRequest request = RequestFilter.requests.get();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        System.out.println(ip);
        try{
            NutMap re = new NutMap();
            if(userName!=null&&password!=null) {
                User u = dao.fetch(User.class, Cnd.where("user_name", "=", userName).and("psw", "=", password));
                if (u!=null) {
                    //HttpServletRequest request = RequestFilter.requests.get();
                    System.out.println(request.getRemoteAddr());
                    System.out.println(request.getRemoteHost());
                    String secretKey = SecretKey.getSecretKey();
                    u.setSecretKey(secretKey);
                    u.setHost(host);
                    dao.update(u);
                    re.put("status", 1);
                    re.put("msg", "OK");
                    re.put("secretKey", secretKey);
                    re.put("userId", u.getUserId());
                    re.put("userName", u.getUserName());
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
            log.info(e);
            NutMap re = new NutMap();
            re.put("status", 0);
            re.put("msg", "error in login");
            return re;
        }

    }

}
