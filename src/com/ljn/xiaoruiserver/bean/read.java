package com.ljn.xiaoruiserver.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by 23381 on 2018/8/16.
 */

@Table("xrr_read")
public class read {
    @Id
    private Integer readId;
    @Column("user_id")
    private Integer userId;
    @Column("book_id")
    private Integer bookId;
    @Column("read_time")
    private String readTime;
    @Column("read_page")
    private Integer readPage;

    public Integer getReadId() {
        return readId;
    }

    public void setReadId(Integer readId) {
        this.readId = readId;
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

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public Integer getReadPage() {
        return readPage;
    }

    public void setReadPage(Integer readPage) {
        this.readPage = readPage;
    }
}
