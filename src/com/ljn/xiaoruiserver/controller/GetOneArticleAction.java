package com.ljn.xiaoruiserver.controller;

import com.ljn.xiaoruiserver.bean.Articles;
import com.ljn.xiaoruiserver.bean.Users;
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
public class GetOneArticleAction {
    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("get_one_article")
    @GET
    @POST
    public Object getOneArticle(@Param("articleId") String articleId){
        NutMap re=new NutMap();
        Articles a=dao.fetch(Articles.class, Cnd.where("articleId","=",Integer.valueOf(articleId)));
        Users u=dao.fetch(Users.class,Cnd.where("userId","=",a.getArticleAuthorId()));
        if(u.getUserId()!=null){
            re.put("status",1);
            re.put("msg","OK");
//            re.put("userPhoto",u.getUserPhone());
//            re.put("userNickname",u.getUserNickName());
//            re.put("articleTitle",a.getArticleTitle());
//            re.put("article",a.getArticleUrl());
            re.put("article",a);
            a.setArticleReadingAmount(a.getArticleReadingAmount()+1);
            dao.update(a);
        }
        else {
            re.put("status",0);
            re.put("msg","No");
        }
        return re;
    }
}
