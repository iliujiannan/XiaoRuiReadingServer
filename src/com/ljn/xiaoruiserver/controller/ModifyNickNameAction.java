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
public class ModifyNickNameAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("modify_nickname")
    @GET
    @POST
    public Object modifyNickNmae(@Param("userId") String userId,@Param("secretKey") String secretKey,@Param("newNickname") String newNickname){
        NutMap re=new NutMap();
        if(userId!=null&&secretKey!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
            if(u.getSecretKey().equals(secretKey)){
                re.put("status",1);
                re.put("msg","OK");
                u.setUserNickName(newNickname);
                dao.update(u);
            }
            else{
                re.put("status",0);
                re.put("msg","您不能修改昵称");
            }
        }
        else {
            re.put("status",0);
            re.put("msg","您还未登陆！");
        }
        return re;
    }
}
