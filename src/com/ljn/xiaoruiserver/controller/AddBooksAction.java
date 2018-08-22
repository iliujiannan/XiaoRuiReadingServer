package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.BookShelf;
import com.ljn.xiaoruiserver.bean.Books;
import com.ljn.xiaoruiserver.bean.Purse;
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
 * Created by 23381 on 2018/8/20.
 */
@IocBean
public class AddBooksAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("add_books")
    @GET
    @POST
    public Object addBooks(@Param("userId") String userId, @Param("secretKey") String secretKey, @Param("bookId") String bookId, @Param("flag") String flag) {
        NutMap re = new NutMap();
        Users u = dao.fetch(Users.class, Cnd.where("userId", "=", Integer.valueOf(userId)).and("secret_key", "=", secretKey));
        if (u != null) {
            BookShelf bs1 = dao.fetch(BookShelf.class, Cnd.where("user_id", "=", Integer.valueOf(userId)).and("book_id", "=", Integer.valueOf(bookId)));
            if (bs1 != null) {
                re.put("status", 0);
                re.put("msg", "已经添加过！");
            } else {
                if(flag.equals("0")){
                    Purse p=dao.fetch(Purse.class,Cnd.where("user_id","=",userId));
                    Books b=dao.fetch(Books.class,Cnd.where("bookId","=",bookId));
                    if(p.getPurseMoney()>=b.getBookPrice()){
                        flag="1";
                        p.setPurseMoney(p.getPurseMoney()-b.getBookPrice());
                        dao.update(p);
                    }else {
                        re.put("status", 0);
                        re.put("msg", "余额不足！");
                        return re;
                    }
                }
                re.put("status", 1);
                re.put("msg", "OK");
                BookShelf bs = new BookShelf();
                bs.setBookId(Integer.valueOf(bookId));
                bs.setUserId(Integer.valueOf(userId));
                bs.setBuyingState(Integer.valueOf(flag));
                bs.setReadTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
                dao.insert(bs);
            }
        } else {
            re.put("status", 0);
            re.put("msg", "您还未登陆！");
        }
        return re;
    }
}
