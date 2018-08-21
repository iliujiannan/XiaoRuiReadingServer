package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.Articles;
import com.ljn.xiaoruiserver.bean.Users;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 23381 on 2018/8/20.
 */
@IocBean
public class GetArticlesAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("get_articles")
    @GET
    @POST
    public Object getArticles(){
        NutMap re=new NutMap();
        List<Articles> articlesList=dao.query(Articles.class, Cnd.orderBy());
        if(articlesList!=null){
            if(articlesList.size()>0){
                re.put("status",1);
                re.put("msg","OK");
                re.put("articles",articlesList);
                List<Users> usersList = new ArrayList<>();
                for (Articles a: articlesList) {
                    Users users = dao.fetch(Users.class, Cnd.where("userId", "=", a.getArticleAuthorId()));
                    System.out.println(a.getArticleAuthorId());
                    System.out.println(a.getArticleId());
                    users.setPsw("");
                    users.setUserPhone("");
                    users.setUserReadDailly(0);
                    users.setUserReadTotally(0);
                    users.setSecretKey("");
                    usersList.add(users);

                }
                re.put("users", usersList);
                return re;
            }
        }
        else {
            re.put("status",0);
            re.put("msg","No");
        }
        return re;
    }
}
