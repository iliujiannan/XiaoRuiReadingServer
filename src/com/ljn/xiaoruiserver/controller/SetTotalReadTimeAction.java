package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.BookShelf;
import com.ljn.xiaoruiserver.bean.Users;
import com.ljn.xiaoruiserver.util.DateUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 23381 on 2018/8/21.
 */
@IocBean
public class SetTotalReadTimeAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("set_total_readTime")
    @GET
    @POST
    public Object setTotalReadTime(@Param("userId") String userId,@Param("secretKey") String secretKey,@Param("bookId") String bookId,@Param("readTime") String readTime){
        NutMap re=new NutMap();
        Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)).and("secret_key","=",secretKey));
        if(u!=null){
            re.put("status",1);
            re.put("msg","OK");
            System.out.println("**************"+readTime);
            u.setUserReadDailly(Integer.valueOf(readTime));
            u.setUserReadTotally(u.getUserReadTotally()+Integer.valueOf(readTime));
            BookShelf bs=dao.fetch(BookShelf.class,Cnd.where("user_id","=",Integer.valueOf(userId)).and("book_id","=",Integer.valueOf(bookId)));
            bs.setReadTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
            dao.update(bs);
            dao.update(u);
        }
        else {
            re.put("status",0);
            re.put("msg","您还未登陆！");
        }
        return re;
    }
}
