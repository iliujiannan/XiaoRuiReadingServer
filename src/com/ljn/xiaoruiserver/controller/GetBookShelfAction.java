package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.BookShelf;
import com.ljn.xiaoruiserver.bean.Books;
import com.ljn.xiaoruiserver.bean.Users;
import com.ljn.xiaoruiserver.util.DateUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        NutMap re = new NutMap();
        Users u = dao.fetch(Users.class, Cnd.where("userId", "=", Integer.valueOf(userId)).and("secret_key", "=", secretKey));
        if (u!=null) {
            List<BookShelf> shelfList=dao.query(BookShelf.class,Cnd.where("user_id","=",Integer.valueOf(userId)).desc("read_time"));
            if(shelfList!=null){
                if(shelfList.size()>0) {
                    re.put("status", 1);
                    re.put("msg", "OK");
                    List<Books> booksList = new ArrayList<>();
                    for (BookShelf b: shelfList) {
                        Books book = dao.fetch(Books.class, Cnd.where("bookid", "=", b.getBookId()));
                        booksList.add(book);
                    }
                    re.put("books", booksList);
                }else{
                    re.put("status",0);
                    re.put("msg","书架上没有书籍！");
                }
            }
            else {
                re.put("status",0);
                re.put("msg","书架上没有书籍！");
            }
        }
        else {
            re.put("status", 0);
            re.put("msg", "您还未登陆！");
        }
        return re;
    }
}
