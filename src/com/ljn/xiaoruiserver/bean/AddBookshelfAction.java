package com.ljn.xiaoruiserver.bean;

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
public class AddBookshelfAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("add_bookshelf")
    @GET
    @POST
    public Object addBookshelf(@Param("userId") String userId,@Param("secretKey") String secretKey,@Param("bookId") String bookId){
        NutMap re=new NutMap();
        if(userId!=null&&secretKey!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
            if(u.getSecretKey().equals(secretKey)){
                re.put("status",1);
                re.put("msg","OK");
                BookShelf bs=new BookShelf();
                bs.setUserId(Integer.valueOf(userId));
                bs.setBookId(Integer.valueOf(bookId));
                bs.setBuyingState(0);
                dao.insert(bs);
            }
            else {
                re.put("status",0);
                re.put("msg","无法加入书架！");
            }
        }
        else {
            re.put("status",0);
            re.put("msg","您还未登陆，请登录！");
        }
        return re;
    }
}
