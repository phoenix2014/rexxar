package com.rexxar.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class CrawlerTask {
    private Long id;

    private String bookname;

    private String source;

    private String sourceUrl;

    private String backSource;

    private Short latestChapter;

    private String latestChapterName;

    private Short crawledChapter;

    private String crawledChapterName;

    private BigDecimal crawledPercent;

    private Boolean crawledStatus;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl == null ? null : sourceUrl.trim();
    }

    public String getBackSource() {
        return backSource;
    }

    public void setBackSource(String backSource) {
        this.backSource = backSource == null ? null : backSource.trim();
    }

    public Short getLatestChapter() {
        return latestChapter;
    }

    public void setLatestChapter(Short latestChapter) {
        this.latestChapter = latestChapter;
    }

    public String getLatestChapterName() {
        return latestChapterName;
    }

    public void setLatestChapterName(String latestChapterName) {
        this.latestChapterName = latestChapterName == null ? null : latestChapterName.trim();
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

    public BigDecimal getCrawledPercent() {
        return crawledPercent;
    }

    public void setCrawledPercent(BigDecimal crawledPercent) {
        this.crawledPercent = crawledPercent;
    }

    public Boolean getCrawledStatus() {
        return crawledStatus;
    }

    public void setCrawledStatus(Boolean crawledStatus) {
        this.crawledStatus = crawledStatus;
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