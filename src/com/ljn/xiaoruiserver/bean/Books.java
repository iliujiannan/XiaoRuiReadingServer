package com.ljn.xiaoruiserver.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by 23381 on 2018/8/16.
 */

@Table("xrr_books")
public class Books {
    @Id
    private Integer bookId;
    @Column("book_name")
    private String bookName;
    @Column("book_author")
    private String bookAuthor;
    @Column("book_img")
    private String bookImg;
    @Column("book_description")
    private String bookDescription;
    @Column("book_price")
    private Integer bookPrice;
    @Column("book_loading_amount")
    private Integer bookLoadingAmount;
    @Column("book_chapter_amount")
    private Integer bookChapterAmount;
    @Column("book_location")
    private String bookLocation;
    @Column("book_label")
    private String bookLabel;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public Integer getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Integer bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getBookLoadingAmount() {
        return bookLoadingAmount;
    }

    public void setBookLoadingAmount(Integer bookLoadingAmount) {
        this.bookLoadingAmount = bookLoadingAmount;
    }

    public Integer getBookChapterAmount() {
        return bookChapterAmount;
    }

    public void setBookChapterAmount(Integer bookChapterAmount) {
        this.bookChapterAmount = bookChapterAmount;
    }

    public String getBookLocation() {
        return bookLocation;
    }

    public void setBookLocation(String bookLocation) {
        this.bookLocation = bookLocation;
    }

    public String getBookLabel() {
        return bookLabel;
    }

    public void setBookLabel(String bookLabel) {
        this.bookLabel = bookLabel;
    }
}
