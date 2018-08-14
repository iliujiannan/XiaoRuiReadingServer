package com.ljn.xiaoruiserver.controller;


import com.ljn.xiaoruiserver.bean.Game;
import com.ljn.xiaoruiserver.bean.User;
import com.ljn.xiaoruiserver.util.DateUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import java.util.Date;

/**
 * Created by 12390 on 2017/9/22.
 */
@IocBean
public class GameAction {
    private Log log = Logs.get();

    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("create_game")
    @GET
    @POST
    public Object createGame(@Param("userId") Integer userId, @Param("secretKey") String secretKey){
        NutMap re = new NutMap();
        User u = dao.fetch(User.class, Cnd.where("userId", "=", userId).and("secret_key", "=", secretKey));
        if(u!=null){
            Game game = dao.fetch(Game.class, Cnd.where("is_waiting", "=", 1).and("create_player_id", "=", userId));
            if(game!=null){
                dao.delete(game);
            }
            Game newGame = new Game();
            newGame.setCreatePlayerId(userId);
            newGame.setIsWaiting(1);
            newGame.setStrartTime(DateUtil.dateToString(new Date()));
            dao.insert(newGame);
            re.put("status", 1);
            re.put("msg", "创建成功");
        }else{
            re.put("status", 0);
            re.put("msg", "请登录");
        }
        return re;
    }


    @Ok("json")
    @Fail("http:500")
    @At("get_one_game")
    @GET
    @POST
    public Object getOneGame(@Param("userId") Integer userId, @Param("secretKey") String secretKey){
        NutMap re = new NutMap();
        User u = dao.fetch(User.class, Cnd.where("userId", "=", userId).and("secret_key", "=", secretKey));
        if(u!=null){
            Game game = dao.fetch(Game.class, Cnd.where("is_waiting", "=", 1).and("create_player_id", "!=", userId));
            if(game!=null){
                game.setIsWaiting(0);
                game.setConnPlayerId(userId);
                dao.update(game);
                User u1 = dao.fetch(User.class, Cnd.where("userId", "=", game.getCreatePlayerId()));
                re.put("game", game);
                re.put("user", u1);
                re.put("status", 1);
                re.put("msg", "OK");
            }else{
                re.put("status", -1);
                re.put("msg", "未找到对局,请创建对局");
            }

        }else{
            re.put("status", 0);
            re.put("msg", "请登录");
        }
        return re;
    }

    @Ok("json")
    @Fail("http:500")
    @At("destroy_game")
    @GET
    @POST
    public Object destroyGame(@Param("userId") Integer userId, @Param("secretKey") String secretKey){
        NutMap re = new NutMap();
        User u = dao.fetch(User.class, Cnd.where("userId", "=", userId).and("secret_key", "=", secretKey));
        if(u!=null){
            Game game = dao.fetch(Game.class, Cnd.where("is_waiting", "=", 1).and("create_player_id", "=", userId));
            if(game!=null){
                dao.delete(game);
                re.put("status", 1);
                re.put("msg", "OK");
            }
        }else{
            re.put("status", 0);
            re.put("msg", "请登录");
        }
        return re;
    }
}
