package com.rexxar.dao.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrawlerTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CrawlerTaskExample() {
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

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceUrlIsNull() {
            addCriterion("source_url is null");
            return (Criteria) this;
        }

        public Criteria andSourceUrlIsNotNull() {
            addCriterion("source_url is not null");
            return (Criteria) this;
        }

        public Criteria andSourceUrlEqualTo(String value) {
            addCriterion("source_url =", value, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andSourceUrlNotEqualTo(String value) {
            addCriterion("source_url <>", value, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andSourceUrlGreaterThan(String value) {
            addCriterion("source_url >", value, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andSourceUrlGreaterThanOrEqualTo(String value) {
            addCriterion("source_url >=", value, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andSourceUrlLessThan(String value) {
            addCriterion("source_url <", value, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andSourceUrlLessThanOrEqualTo(String value) {
            addCriterion("source_url <=", value, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andSourceUrlLike(String value) {
            addCriterion("source_url like", value, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andSourceUrlNotLike(String value) {
            addCriterion("source_url not like", value, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andSourceUrlIn(List<String> values) {
            addCriterion("source_url in", values, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andSourceUrlNotIn(List<String> values) {
            addCriterion("source_url not in", values, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andSourceUrlBetween(String value1, String value2) {
            addCriterion("source_url between", value1, value2, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andSourceUrlNotBetween(String value1, String value2) {
            addCriterion("source_url not between", value1, value2, "sourceUrl");
            return (Criteria) this;
        }

        public Criteria andBackSourceIsNull() {
            addCriterion("back_source is null");
            return (Criteria) this;
        }

        public Criteria andBackSourceIsNotNull() {
            addCriterion("back_source is not null");
            return (Criteria) this;
        }

        public Criteria andBackSourceEqualTo(String value) {
            addCriterion("back_source =", value, "backSource");
            return (Criteria) this;
        }

        public Criteria andBackSourceNotEqualTo(String value) {
            addCriterion("back_source <>", value, "backSource");
            return (Criteria) this;
        }

        public Criteria andBackSourceGreaterThan(String value) {
            addCriterion("back_source >", value, "backSource");
            return (Criteria) this;
        }

        public Criteria andBackSourceGreaterThanOrEqualTo(String value) {
            addCriterion("back_source >=", value, "backSource");
            return (Criteria) this;
        }

        public Criteria andBackSourceLessThan(String value) {
            addCriterion("back_source <", value, "backSource");
            return (Criteria) this;
        }

        public Criteria andBackSourceLessThanOrEqualTo(String value) {
            addCriterion("back_source <=", value, "backSource");
            return (Criteria) this;
        }

        public Criteria andBackSourceLike(String value) {
            addCriterion("back_source like", value, "backSource");
            return (Criteria) this;
        }

        public Criteria andBackSourceNotLike(String value) {
            addCriterion("back_source not like", value, "backSource");
            return (Criteria) this;
        }

        public Criteria andBackSourceIn(List<String> values) {
            addCriterion("back_source in", values, "backSource");
            return (Criteria) this;
        }

        public Criteria andBackSourceNotIn(List<String> values) {
            addCriterion("back_source not in", values, "backSource");
            return (Criteria) this;
        }

        public Criteria andBackSourceBetween(String value1, String value2) {
            addCriterion("back_source between", value1, value2, "backSource");
            return (Criteria) this;
        }

        public Criteria andBackSourceNotBetween(String value1, String value2) {
            addCriterion("back_source not between", value1, value2, "backSource");
            return (Criteria) this;
        }

        public Criteria andLatestChapterIsNull() {
            addCriterion("latest_chapter is null");
            return (Criteria) this;
        }

        public Criteria andLatestChapterIsNotNull() {
            addCriterion("latest_chapter is not null");
            return (Criteria) this;
        }

        public Criteria andLatestChapterEqualTo(Short value) {
            addCriterion("latest_chapter =", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNotEqualTo(Short value) {
            addCriterion("latest_chapter <>", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterGreaterThan(Short value) {
            addCriterion("latest_chapter >", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterGreaterThanOrEqualTo(Short value) {
            addCriterion("latest_chapter >=", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterLessThan(Short value) {
            addCriterion("latest_chapter <", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterLessThanOrEqualTo(Short value) {
            addCriterion("latest_chapter <=", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterIn(List<Short> values) {
            addCriterion("latest_chapter in", values, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNotIn(List<Short> values) {
            addCriterion("latest_chapter not in", values, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterBetween(Short value1, Short value2) {
            addCriterion("latest_chapter between", value1, value2, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNotBetween(Short value1, Short value2) {
            addCriterion("latest_chapter not between", value1, value2, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameIsNull() {
            addCriterion("latest_chapter_name is null");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameIsNotNull() {
            addCriterion("latest_chapter_name is not null");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameEqualTo(String value) {
            addCriterion("latest_chapter_name =", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameNotEqualTo(String value) {
            addCriterion("latest_chapter_name <>", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameGreaterThan(String value) {
            addCriterion("latest_chapter_name >", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameGreaterThanOrEqualTo(String value) {
            addCriterion("latest_chapter_name >=", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameLessThan(String value) {
            addCriterion("latest_chapter_name <", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameLessThanOrEqualTo(String value) {
            addCriterion("latest_chapter_name <=", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameLike(String value) {
            addCriterion("latest_chapter_name like", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameNotLike(String value) {
            addCriterion("latest_chapter_name not like", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameIn(List<String> values) {
            addCriterion("latest_chapter_name in", values, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameNotIn(List<String> values) {
            addCriterion("latest_chapter_name not in", values, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameBetween(String value1, String value2) {
            addCriterion("latest_chapter_name between", value1, value2, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameNotBetween(String value1, String value2) {
            addCriterion("latest_chapter_name not between", value1, value2, "latestChapterName");
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

        public Criteria andCrawledPercentIsNull() {
            addCriterion("crawled_percent is null");
            return (Criteria) this;
        }

        public Criteria andCrawledPercentIsNotNull() {
            addCriterion("crawled_percent is not null");
            return (Criteria) this;
        }

        public Criteria andCrawledPercentEqualTo(BigDecimal value) {
            addCriterion("crawled_percent =", value, "crawledPercent");
            return (Criteria) this;
        }

        public Criteria andCrawledPercentNotEqualTo(BigDecimal value) {
            addCriterion("crawled_percent <>", value, "crawledPercent");
            return (Criteria) this;
        }

        public Criteria andCrawledPercentGreaterThan(BigDecimal value) {
            addCriterion("crawled_percent >", value, "crawledPercent");
            return (Criteria) this;
        }

        public Criteria andCrawledPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("crawled_percent >=", value, "crawledPercent");
            return (Criteria) this;
        }

        public Criteria andCrawledPercentLessThan(BigDecimal value) {
            addCriterion("crawled_percent <", value, "crawledPercent");
            return (Criteria) this;
        }

        public Criteria andCrawledPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("crawled_percent <=", value, "crawledPercent");
            return (Criteria) this;
        }

        public Criteria andCrawledPercentIn(List<BigDecimal> values) {
            addCriterion("crawled_percent in", values, "crawledPercent");
            return (Criteria) this;
        }

        public Criteria andCrawledPercentNotIn(List<BigDecimal> values) {
            addCriterion("crawled_percent not in", values, "crawledPercent");
            return (Criteria) this;
        }

        public Criteria andCrawledPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("crawled_percent between", value1, value2, "crawledPercent");
            return (Criteria) this;
        }

        public Criteria andCrawledPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("crawled_percent not between", value1, value2, "crawledPercent");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusIsNull() {
            addCriterion("crawled_status is null");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusIsNotNull() {
            addCriterion("crawled_status is not null");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusEqualTo(Boolean value) {
            addCriterion("crawled_status =", value, "crawledStatus");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusNotEqualTo(Boolean value) {
            addCriterion("crawled_status <>", value, "crawledStatus");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusGreaterThan(Boolean value) {
            addCriterion("crawled_status >", value, "crawledStatus");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("crawled_status >=", value, "crawledStatus");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusLessThan(Boolean value) {
            addCriterion("crawled_status <", value, "crawledStatus");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("crawled_status <=", value, "crawledStatus");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusIn(List<Boolean> values) {
            addCriterion("crawled_status in", values, "crawledStatus");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusNotIn(List<Boolean> values) {
            addCriterion("crawled_status not in", values, "crawledStatus");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("crawled_status between", value1, value2, "crawledStatus");
            return (Criteria) this;
        }

        public Criteria andCrawledStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("crawled_status not between", value1, value2, "crawledStatus");
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