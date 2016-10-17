package com.rexxar.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class NovelOverview {
    private Long id;

    private String bookname;

    private String author;

    private String imgUrl;

    private String novelDescribe;

    private String category;

    private Short crawledChapter;

    private String crawledChapterName;

    private String novelStatus;

    private BigDecimal novelScore;

    private String publishSource;

    private Boolean preferGender;

    private Date updateTime;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getNovelDescribe() {
        return novelDescribe;
    }

    public void setNovelDescribe(String novelDescribe) {
        this.novelDescribe = novelDescribe == null ? null : novelDescribe.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Short getCrawledChapter() {
        return crawledChapter;
    }

    public void setCrawledChapter(Short crawledChapter) {
        this.crawledChapter = crawledChapter;
    }

    public String getCrawledChapterName() {
        return crawledChapterName;
    }

    public void setCrawledChapterName(String crawledChapterName) {
        this.crawledChapterName = crawledChapterName == null ? null : crawledChapterName.trim();
    }

    public String getNovelStatus() {
        return novelStatus;
    }

    public void setNovelStatus(String novelStatus) {
        this.novelStatus = novelStatus == null ? null : novelStatus.trim();
    }

    public BigDecimal getNovelScore() {
        return novelScore;
    }

    public void setNovelScore(BigDecimal novelScore) {
        this.novelScore = novelScore;
    }

    public String getPublishSource() {
        return publishSource;
    }

    public void setPublishSource(String publishSource) {
        this.publishSource = publishSource == null ? null : publishSource.trim();
    }

    public Boolean getPreferGender() {
        return preferGender;
    }

    public void setPreferGender(Boolean preferGender) {
        this.preferGender = preferGender;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}