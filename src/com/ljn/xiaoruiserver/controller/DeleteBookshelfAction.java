package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.BookShelf;
import com.ljn.xiaoruiserver.bean.Users;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

/**
 * Created by 23381 on 2018/8/21.
 */
@IocBean
public class DeleteBookshelfAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("delete_bookshelf")
    @GET
    @POST
    public Object deleteBookshelf(@Param("userId") String userId,@Param("secretKey") String secretKey,@Param("bookId") String bookId){
        NutMap re=new NutMap();
        Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
        if(u!=null){
            BookShelf bs=dao.fetch(BookShelf.class,Cnd.where("user_id","=",Integer.valueOf(userId)).and("book_id","=",Integer.valueOf(bookId)));
            if(bs!=null){
                re.put("status",1);
                re.put("msg","OK");
                dao.delete(bs);
            }
            else {
                re.put("status",0);
                re.put("msg","书架上没有这本书！");
            }
        }
        else {
            re.put("status",0);
            re.put("msg","请登录！");
        }
        return re;
    }
}
