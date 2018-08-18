package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.Purse;
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
public class ChargeMoneyAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("charge_money")
    @GET
    @POST
    public Object chargeMoney(@Param("userId") String userId,@Param("secretKey") String secretKey,@Param("chargeMoneyAmount") String chargeMoneyAmount){
        NutMap re=new NutMap();
        if(userId!=null&&secretKey!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
            if(secretKey.equals(u.getSecretKey())){
                re.put("status",1);
                re.put("msg","OK");
                Purse p=dao.fetch(Purse.class,Cnd.where("user_id","=",userId));
                p.setPurseMoney(p.getPurseMoney()+Integer.valueOf(chargeMoneyAmount));
                dao.update(p);
                re.put("chargeMoney",p.getPurseMoney());
            }
            else {
                re.put("status",0);
                re.put("msg","您无法充值！");
            }
        }
        else {
            re.put("status",0);
            re.put("msg","您还未登陆！");
        }
        return re;
    }
}
