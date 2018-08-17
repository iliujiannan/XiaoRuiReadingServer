package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.Users;
import com.ljn.xiaoruiserver.util.SecretKey;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import java.util.Objects;

/**
 * Created by 23381 on 2018/8/16.
 */
@IocBean
public class PersonalInformationAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("personal_information")
    @GET
    @POST
    public Object getPersonalInformation(@Param("userId") String userId,@Param("secretKey") String secretKey){
        NutMap re = new NutMap();
        if(userId!=null&&secretKey!=null){
            Users u=dao.fetch(Users.class,Cnd.where("userId","=",Integer.valueOf(userId)).and("secret_key","=",secretKey));
            if(u!=null){
                dao.update(u);
                re.put("status",1);
                re.put("msg","Ok");
                u.setPsw("");
                re.put("userData", u);
//                re.put("userId",userId);
//                re.put("userPhone",u.getUserPhone());
//                re.put("userPhoto",u.getUserPhoto());
//                re.put("userNickName",u.getUserNickName());
//                re.put("secretKey",secretKey);
//                re.put("userType",u.getUserType());
//                re.put("userReadDailly",u.getUserReadDailly());
//                re.put("userReadTotally",u.getUserReadTotally());
            }
            else {
                re.put("status",0);
                re.put("msg","该用户信息不存在");
            }
        }
        else {
            re.put("ststus",0);
            re.put("msg","该用户信息不存在");
        }
        return re;
    }
}
