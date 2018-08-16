package com.ljn.xiaoruiserver.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by 23381 on 2018/8/16.
 */

@Table("xrr_articles")
public class Articles {
    @Id
    private Integer articleId;
    @Column("article_title")
    private String articleTitle;
    @Column("article_author_id")
    private Integer articleAuthorId;
    @Column("article_img")
    private String articleImg;
    @Column("article_url")
    private String articleUrl;
    @Column("article_reading_amount")
    private String articleReadingAmount;
    @Column("article_description")
    private String articleDescription;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Integer getArticleAuthorId() {
        return articleAuthorId;
    }

    public void setArticleAuthorId(Integer articleAuthorId) {
        this.articleAuthorId = articleAuthorId;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleReadingAmount() {
        return articleReadingAmount;
    }

    public void setArticleReadingAmount(String articleReadingAmount) {
        this.articleReadingAmount = articleReadingAmount;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }
}
