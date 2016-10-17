package com.rexxar.dao.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NovelOverviewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NovelOverviewExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBooknameIsNull() {
            addCriterion("bookname is null");
            return (Criteria) this;
        }

        public Criteria andBooknameIsNotNull() {
            addCriterion("bookname is not null");
            return (Criteria) this;
        }

        public Criteria andBooknameEqualTo(String value) {
            addCriterion("bookname =", value, "bookname");
            return (Criteria) this;
        }

        public Criteria andBooknameNotEqualTo(String value) {
            addCriterion("bookname <>", value, "bookname");
            return (Criteria) this;
        }

        public Criteria andBooknameGreaterThan(String value) {
            addCriterion("bookname >", value, "bookname");
            return (Criteria) this;
        }

        public Criteria andBooknameGreaterThanOrEqualTo(String value) {
            addCriterion("bookname >=", value, "bookname");
            return (Criteria) this;
        }

        public Criteria andBooknameLessThan(String value) {
            addCriterion("bookname <", value, "bookname");
            return (Criteria) this;
        }

        public Criteria andBooknameLessThanOrEqualTo(String value) {
            addCriterion("bookname <=", value, "bookname");
            return (Criteria) this;
        }

        public Criteria andBooknameLike(String value) {
            addCriterion("bookname like", value, "bookname");
            return (Criteria) this;
        }
        
        public Criteria andBooknameBlurryLike(String value) {
        	String blurryValue = "%" + value + "%";
            addCriterion("bookname like", blurryValue, "bookname");
            return (Criteria) this;
        }

        public Criteria andBooknameNotLike(String value) {
            addCriterion("bookname not like", value, "bookname");
            return (Criteria) this;
        }

        public Criteria andBooknameIn(List<String> values) {
            addCriterion("bookname in", values, "bookname");
            return (Criteria) this;
        }

        public Criteria andBooknameNotIn(List<String> values) {
            addCriterion("bookname not in", values, "bookname");
            return (Criteria) this;
        }

        public Criteria andBooknameBetween(String value1, String value2) {
            addCriterion("bookname between", value1, value2, "bookname");
            return (Criteria) this;
        }

        public Criteria andBooknameNotBetween(String value1, String value2) {
            addCriterion("bookname not between", value1, value2, "bookname");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("img_url not between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeIsNull() {
            addCriterion("novel_describe is null");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeIsNotNull() {
            addCriterion("novel_describe is not null");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeEqualTo(String value) {
            addCriterion("novel_describe =", value, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeNotEqualTo(String value) {
            addCriterion("novel_describe <>", value, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeGreaterThan(String value) {
            addCriterion("novel_describe >", value, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("novel_describe >=", value, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeLessThan(String value) {
            addCriterion("novel_describe <", value, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeLessThanOrEqualTo(String value) {
            addCriterion("novel_describe <=", value, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeLike(String value) {
            addCriterion("novel_describe like", value, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeNotLike(String value) {
            addCriterion("novel_describe not like", value, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeIn(List<String> values) {
            addCriterion("novel_describe in", values, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeNotIn(List<String> values) {
            addCriterion("novel_describe not in", values, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeBetween(String value1, String value2) {
            addCriterion("novel_describe between", value1, value2, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andNovelDescribeNotBetween(String value1, String value2) {
            addCriterion("novel_describe not between", value1, value2, "novelDescribe");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterIsNull() {
            addCriterion("crawled_chapter is null");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterIsNotNull() {
            addCriterion("crawled_chapter is not null");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterEqualTo(Short value) {
            addCriterion("crawled_chapter =", value, "crawledChapter");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNotEqualTo(Short value) {
            addCriterion("crawled_chapter <>", value, "crawledChapter");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterGreaterThan(Short value) {
            addCriterion("crawled_chapter >", value, "crawledChapter");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterGreaterThanOrEqualTo(Short value) {
            addCriterion("crawled_chapter >=", value, "crawledChapter");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterLessThan(Short value) {
            addCriterion("crawled_chapter <", value, "crawledChapter");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterLessThanOrEqualTo(Short value) {
            addCriterion("crawled_chapter <=", value, "crawledChapter");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterIn(List<Short> values) {
            addCriterion("crawled_chapter in", values, "crawledChapter");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNotIn(List<Short> values) {
            addCriterion("crawled_chapter not in", values, "crawledChapter");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterBetween(Short value1, Short value2) {
            addCriterion("crawled_chapter between", value1, value2, "crawledChapter");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNotBetween(Short value1, Short value2) {
            addCriterion("crawled_chapter not between", value1, value2, "crawledChapter");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameIsNull() {
            addCriterion("crawled_chapter_name is null");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameIsNotNull() {
            addCriterion("crawled_chapter_name is not null");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameEqualTo(String value) {
            addCriterion("crawled_chapter_name =", value, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameNotEqualTo(String value) {
            addCriterion("crawled_chapter_name <>", value, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameGreaterThan(String value) {
            addCriterion("crawled_chapter_name >", value, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameGreaterThanOrEqualTo(String value) {
            addCriterion("crawled_chapter_name >=", value, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameLessThan(String value) {
            addCriterion("crawled_chapter_name <", value, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameLessThanOrEqualTo(String value) {
            addCriterion("crawled_chapter_name <=", value, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameLike(String value) {
            addCriterion("crawled_chapter_name like", value, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameNotLike(String value) {
            addCriterion("crawled_chapter_name not like", value, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameIn(List<String> values) {
            addCriterion("crawled_chapter_name in", values, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameNotIn(List<String> values) {
            addCriterion("crawled_chapter_name not in", values, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameBetween(String value1, String value2) {
            addCriterion("crawled_chapter_name between", value1, value2, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andCrawledChapterNameNotBetween(String value1, String value2) {
            addCriterion("crawled_chapter_name not between", value1, value2, "crawledChapterName");
            return (Criteria) this;
        }

        public Criteria andNovelStatusIsNull() {
            addCriterion("novel_status is null");
            return (Criteria) this;
        }

        public Criteria andNovelStatusIsNotNull() {
            addCriterion("novel_status is not null");
            return (Criteria) this;
        }

        public Criteria andNovelStatusEqualTo(String value) {
            addCriterion("novel_status =", value, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelStatusNotEqualTo(String value) {
            addCriterion("novel_status <>", value, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelStatusGreaterThan(String value) {
            addCriterion("novel_status >", value, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelStatusGreaterThanOrEqualTo(String value) {
            addCriterion("novel_status >=", value, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelStatusLessThan(String value) {
            addCriterion("novel_status <", value, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelStatusLessThanOrEqualTo(String value) {
            addCriterion("novel_status <=", value, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelStatusLike(String value) {
            addCriterion("novel_status like", value, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelStatusNotLike(String value) {
            addCriterion("novel_status not like", value, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelStatusIn(List<String> values) {
            addCriterion("novel_status in", values, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelStatusNotIn(List<String> values) {
            addCriterion("novel_status not in", values, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelStatusBetween(String value1, String value2) {
            addCriterion("novel_status between", value1, value2, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelStatusNotBetween(String value1, String value2) {
            addCriterion("novel_status not between", value1, value2, "novelStatus");
            return (Criteria) this;
        }

        public Criteria andNovelScoreIsNull() {
            addCriterion("novel_score is null");
            return (Criteria) this;
        }

        public Criteria andNovelScoreIsNotNull() {
            addCriterion("novel_score is not null");
            return (Criteria) this;
        }

        public Criteria andNovelScoreEqualTo(BigDecimal value) {
            addCriterion("novel_score =", value, "novelScore");
            return (Criteria) this;
        }

        public Criteria andNovelScoreNotEqualTo(BigDecimal value) {
            addCriterion("novel_score <>", value, "novelScore");
            return (Criteria) this;
        }

        public Criteria andNovelScoreGreaterThan(BigDecimal value) {
            addCriterion("novel_score >", value, "novelScore");
            return (Criteria) this;
        }

        public Criteria andNovelScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("novel_score >=", value, "novelScore");
            return (Criteria) this;
        }

        public Criteria andNovelScoreLessThan(BigDecimal value) {
            addCriterion("novel_score <", value, "novelScore");
            return (Criteria) this;
        }

        public Criteria andNovelScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("novel_score <=", value, "novelScore");
            return (Criteria) this;
        }

        public Criteria andNovelScoreIn(List<BigDecimal> values) {
            addCriterion("novel_score in", values, "novelScore");
            return (Criteria) this;
        }

        public Criteria andNovelScoreNotIn(List<BigDecimal> values) {
            addCriterion("novel_score not in", values, "novelScore");
            return (Criteria) this;
        }

        public Criteria andNovelScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("novel_score between", value1, value2, "novelScore");
            return (Criteria) this;
        }

        public Criteria andNovelScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("novel_score not between", value1, value2, "novelScore");
            return (Criteria) this;
        }

        public Criteria andPublishSourceIsNull() {
            addCriterion("publish_source is null");
            return (Criteria) this;
        }

        public Criteria andPublishSourceIsNotNull() {
            addCriterion("publish_source is not null");
            return (Criteria) this;
        }

        public Criteria andPublishSourceEqualTo(String value) {
            addCriterion("publish_source =", value, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPublishSourceNotEqualTo(String value) {
            addCriterion("publish_source <>", value, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPublishSourceGreaterThan(String value) {
            addCriterion("publish_source >", value, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPublishSourceGreaterThanOrEqualTo(String value) {
            addCriterion("publish_source >=", value, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPublishSourceLessThan(String value) {
            addCriterion("publish_source <", value, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPublishSourceLessThanOrEqualTo(String value) {
            addCriterion("publish_source <=", value, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPublishSourceLike(String value) {
            addCriterion("publish_source like", value, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPublishSourceNotLike(String value) {
            addCriterion("publish_source not like", value, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPublishSourceIn(List<String> values) {
            addCriterion("publish_source in", values, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPublishSourceNotIn(List<String> values) {
            addCriterion("publish_source not in", values, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPublishSourceBetween(String value1, String value2) {
            addCriterion("publish_source between", value1, value2, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPublishSourceNotBetween(String value1, String value2) {
            addCriterion("publish_source not between", value1, value2, "publishSource");
            return (Criteria) this;
        }

        public Criteria andPreferGenderIsNull() {
            addCriterion("prefer_gender is null");
            return (Criteria) this;
        }

        public Criteria andPreferGenderIsNotNull() {
            addCriterion("prefer_gender is not null");
            return (Criteria) this;
        }

        public Criteria andPreferGenderEqualTo(Boolean value) {
            addCriterion("prefer_gender =", value, "preferGender");
            return (Criteria) this;
        }

        public Criteria andPreferGenderNotEqualTo(Boolean value) {
            addCriterion("prefer_gender <>", value, "preferGender");
            return (Criteria) this;
        }

        public Criteria andPreferGenderGreaterThan(Boolean value) {
            addCriterion("prefer_gender >", value, "preferGender");
            return (Criteria) this;
        }

        public Criteria andPreferGenderGreaterThanOrEqualTo(Boolean value) {
            addCriterion("prefer_gender >=", value, "preferGender");
            return (Criteria) this;
        }

        public Criteria andPreferGenderLessThan(Boolean value) {
            addCriterion("prefer_gender <", value, "preferGender");
            return (Criteria) this;
        }

        public Criteria andPreferGenderLessThanOrEqualTo(Boolean value) {
            addCriterion("prefer_gender <=", value, "preferGender");
            return (Criteria) this;
        }

        public Criteria andPreferGenderIn(List<Boolean> values) {
            addCriterion("prefer_gender in", values, "preferGender");
            return (Criteria) this;
        }

        public Criteria andPreferGenderNotIn(List<Boolean> values) {
            addCriterion("prefer_gender not in", values, "preferGender");
            return (Criteria) this;
        }

        public Criteria andPreferGenderBetween(Boolean value1, Boolean value2) {
            addCriterion("prefer_gender between", value1, value2, "preferGender");
            return (Criteria) this;
        }

        public Criteria andPreferGenderNotBetween(Boolean value1, Boolean value2) {
            addCriterion("prefer_gender not between", value1, value2, "preferGender");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}