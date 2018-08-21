package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.Books;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

/**
 * Created by 23381 on 2018/8/20.
 */
@IocBean
public class getOneBookAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("get_one_book")
    @GET
    @POST
    public Object getOneBook(@Param("bookId") String bookId){
        NutMap re=new NutMap();
        Books b=dao.fetch(Books.class, Cnd.where("bookId","=",Integer.valueOf(bookId)));
        re.put("status",1);
        re.put("msg","OK");
        re.put("book",b);
        return re;
    }
}
