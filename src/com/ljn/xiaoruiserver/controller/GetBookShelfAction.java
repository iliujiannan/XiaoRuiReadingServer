package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.Users;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

/**
 * Created by 23381 on 2018/8/18.
 */
@IocBean
public class GetBookShelfAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("get_bookshelf")
    @GET
    @POST
    public Object getBookshelf(@Param("userId") String userId,@Param("secretKey") String secretKey){
        NutMap re=new NutMap();
        if(userId!=null&&secretKey!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
            if(u.getSecretKey().equals(secretKey)){

            }
            else {
                re.put("status",0);
                re.put("msg","wrong");
            }
        }
        else {
            re.put("status",0);
            re.put("msg","您还未登录，请登录！");
        }
        return re;
    }
}
