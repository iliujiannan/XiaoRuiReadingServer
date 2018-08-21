package com.ljn.xiaoruiserver.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by 23381 on 2018/8/16.
 */
@Table("xrr_bookshelf")
public class BookShelf {
    @Id
    private Integer shelfId;
    @Column("user_id")
    private Integer userId;
    @Column("book_id")
    private Integer bookId;
    @Column("buying_state")
    private Integer buyingState;
    @Column("book_url")
    private String bookUrl;
    @Column("read_time")
    private String readTime;

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public Integer getShelfId() {
        return shelfId;
    }

    public void setShelfId(Integer shelfId) {
        this.shelfId = shelfId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBuyingState() {
        return buyingState;
    }

    public void setBuyingState(Integer buyingState) {
        this.buyingState = buyingState;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }
}
