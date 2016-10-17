package com.rexxar.dao.model;

import java.util.Date;

public class NovelDirectory {
    private Long id;

    private String bookname;

    private Short crawledChapterNumber;

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

    public Short getCrawledChapterNumber() {
        return crawledChapterNumber;
    }

    public void setCrawledChapterNumber(Short crawledChapterNumber) {
        this.crawledChapterNumber = crawledChapterNumber;
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