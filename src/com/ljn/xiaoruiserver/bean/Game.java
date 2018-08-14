package com.ljn.xiaoruiserver.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by 12390 on 2017/9/22.
 */
@Table("game")
public class Game {
    @Id
    private Integer gameId;
    @Column("create_player_id")
    private Integer createPlayerId;
    @Column("conn_player_id")
    private Integer connPlayerId;
    @Column("start_time")
    private String strartTime;
    @Column("is_waiting")
    private Integer isWaiting;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getCreatePlayerId() {
        return createPlayerId;
    }

    public void setCreatePlayerId(Integer createPlayerId) {
        this.createPlayerId = createPlayerId;
    }

    public Integer getConnPlayerId() {
        return connPlayerId;
    }

    public void setConnPlayerId(Integer connPlayerId) {
        this.connPlayerId = connPlayerId;
    }

    public String getStrartTime() {
        return strartTime;
    }

    public void setStrartTime(String strartTime) {
        this.strartTime = strartTime;
    }

    public Integer getIsWaiting() {
        return isWaiting;
    }

    public void setIsWaiting(Integer isWaiting) {
        this.isWaiting = isWaiting;
    }
}
