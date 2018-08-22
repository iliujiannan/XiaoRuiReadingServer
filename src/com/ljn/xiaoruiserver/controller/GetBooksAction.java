package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.Books;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import java.util.List;

/**
 * Created by 23381 on 2018/8/18.
 */
@IocBean
public class GetBooksAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("get_books")
    @GET
    @POST
    public Object getBooks(@Param("bookLabel") String bookLabel){
        NutMap re=new NutMap();
        List<Books> booksList;
        if(bookLabel.equals("0")){
            booksList=dao.query(Books.class, Cnd.orderBy().asc("bookId"));
        }else{
            booksList = dao.query(Books.class, Cnd.where("book_label", "=", bookLabel).asc("bookId"));
        }
        if(booksList!=null) {
            if(booksList.size()>0) {
                re.put("status", 1);
                re.put("msg", "OK");
                re.put("books", booksList);
                return re;
            }
        }
        re.put("status", 0);
        re.put("msg", "没有数据");
        return re;
    }
}
