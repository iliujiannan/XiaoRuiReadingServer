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
public class ModifyPswAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("modify_psw")
    @GET
    @POST
    public Object modifyPsw(@Param("userId") String userId,@Param("psw") String passWord,@Param("repsw") String rePassWord){
        NutMap re=new NutMap();
        if(passWord!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
            if(passWord.equals(u.getPsw())){
                re.put("status",0);
                re.put("msg","修改");
            }
            else {
                re.put("status",0);
                re.put("msg","原密码不正确！");
            }
        }
        else {
            re.put("status",0);
            re.put("msg","原密码不能为空！");
        }
        return re;
    }
}
