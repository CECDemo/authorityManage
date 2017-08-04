package com.wbliu.cecdemo.userManager.pojo;

import java.util.ArrayList;
import java.util.List;

public class UsermenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UsermenuExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionIsNull() {
            addCriterion("menudescription is null");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionIsNotNull() {
            addCriterion("menudescription is not null");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionEqualTo(String value) {
            addCriterion("menudescription =", value, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionNotEqualTo(String value) {
            addCriterion("menudescription <>", value, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionGreaterThan(String value) {
            addCriterion("menudescription >", value, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("menudescription >=", value, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionLessThan(String value) {
            addCriterion("menudescription <", value, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionLessThanOrEqualTo(String value) {
            addCriterion("menudescription <=", value, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionLike(String value) {
            addCriterion("menudescription like", value, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionNotLike(String value) {
            addCriterion("menudescription not like", value, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionIn(List<String> values) {
            addCriterion("menudescription in", values, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionNotIn(List<String> values) {
            addCriterion("menudescription not in", values, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionBetween(String value1, String value2) {
            addCriterion("menudescription between", value1, value2, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenudescriptionNotBetween(String value1, String value2) {
            addCriterion("menudescription not between", value1, value2, "menudescription");
            return (Criteria) this;
        }

        public Criteria andMenuurlIsNull() {
            addCriterion("menuurl is null");
            return (Criteria) this;
        }

        public Criteria andMenuurlIsNotNull() {
            addCriterion("menuurl is not null");
            return (Criteria) this;
        }

        public Criteria andMenuurlEqualTo(String value) {
            addCriterion("menuurl =", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlNotEqualTo(String value) {
            addCriterion("menuurl <>", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlGreaterThan(String value) {
            addCriterion("menuurl >", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlGreaterThanOrEqualTo(String value) {
            addCriterion("menuurl >=", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlLessThan(String value) {
            addCriterion("menuurl <", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlLessThanOrEqualTo(String value) {
            addCriterion("menuurl <=", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlLike(String value) {
            addCriterion("menuurl like", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlNotLike(String value) {
            addCriterion("menuurl not like", value, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlIn(List<String> values) {
            addCriterion("menuurl in", values, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlNotIn(List<String> values) {
            addCriterion("menuurl not in", values, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlBetween(String value1, String value2) {
            addCriterion("menuurl between", value1, value2, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuurlNotBetween(String value1, String value2) {
            addCriterion("menuurl not between", value1, value2, "menuurl");
            return (Criteria) this;
        }

        public Criteria andMenuroleIsNull() {
            addCriterion("menurole is null");
            return (Criteria) this;
        }

        public Criteria andMenuroleIsNotNull() {
            addCriterion("menurole is not null");
            return (Criteria) this;
        }

        public Criteria andMenuroleEqualTo(String value) {
            addCriterion("menurole =", value, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenuroleNotEqualTo(String value) {
            addCriterion("menurole <>", value, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenuroleGreaterThan(String value) {
            addCriterion("menurole >", value, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenuroleGreaterThanOrEqualTo(String value) {
            addCriterion("menurole >=", value, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenuroleLessThan(String value) {
            addCriterion("menurole <", value, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenuroleLessThanOrEqualTo(String value) {
            addCriterion("menurole <=", value, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenuroleLike(String value) {
            addCriterion("menurole like", value, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenuroleNotLike(String value) {
            addCriterion("menurole not like", value, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenuroleIn(List<String> values) {
            addCriterion("menurole in", values, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenuroleNotIn(List<String> values) {
            addCriterion("menurole not in", values, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenuroleBetween(String value1, String value2) {
            addCriterion("menurole between", value1, value2, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenuroleNotBetween(String value1, String value2) {
            addCriterion("menurole not between", value1, value2, "menurole");
            return (Criteria) this;
        }

        public Criteria andMenunameIsNull() {
            addCriterion("menuname is null");
            return (Criteria) this;
        }

        public Criteria andMenunameIsNotNull() {
            addCriterion("menuname is not null");
            return (Criteria) this;
        }

        public Criteria andMenunameEqualTo(String value) {
            addCriterion("menuname =", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameNotEqualTo(String value) {
            addCriterion("menuname <>", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameGreaterThan(String value) {
            addCriterion("menuname >", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameGreaterThanOrEqualTo(String value) {
            addCriterion("menuname >=", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameLessThan(String value) {
            addCriterion("menuname <", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameLessThanOrEqualTo(String value) {
            addCriterion("menuname <=", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameLike(String value) {
            addCriterion("menuname like", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameNotLike(String value) {
            addCriterion("menuname not like", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameIn(List<String> values) {
            addCriterion("menuname in", values, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameNotIn(List<String> values) {
            addCriterion("menuname not in", values, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameBetween(String value1, String value2) {
            addCriterion("menuname between", value1, value2, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameNotBetween(String value1, String value2) {
            addCriterion("menuname not between", value1, value2, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenuorderIsNull() {
            addCriterion("menuorder is null");
            return (Criteria) this;
        }

        public Criteria andMenuorderIsNotNull() {
            addCriterion("menuorder is not null");
            return (Criteria) this;
        }

        public Criteria andMenuorderEqualTo(Integer value) {
            addCriterion("menuorder =", value, "menuorder");
            return (Criteria) this;
        }

        public Criteria andMenuorderNotEqualTo(Integer value) {
            addCriterion("menuorder <>", value, "menuorder");
            return (Criteria) this;
        }

        public Criteria andMenuorderGreaterThan(Integer value) {
            addCriterion("menuorder >", value, "menuorder");
            return (Criteria) this;
        }

        public Criteria andMenuorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("menuorder >=", value, "menuorder");
            return (Criteria) this;
        }

        public Criteria andMenuorderLessThan(Integer value) {
            addCriterion("menuorder <", value, "menuorder");
            return (Criteria) this;
        }

        public Criteria andMenuorderLessThanOrEqualTo(Integer value) {
            addCriterion("menuorder <=", value, "menuorder");
            return (Criteria) this;
        }

        public Criteria andMenuorderIn(List<Integer> values) {
            addCriterion("menuorder in", values, "menuorder");
            return (Criteria) this;
        }

        public Criteria andMenuorderNotIn(List<Integer> values) {
            addCriterion("menuorder not in", values, "menuorder");
            return (Criteria) this;
        }

        public Criteria andMenuorderBetween(Integer value1, Integer value2) {
            addCriterion("menuorder between", value1, value2, "menuorder");
            return (Criteria) this;
        }

        public Criteria andMenuorderNotBetween(Integer value1, Integer value2) {
            addCriterion("menuorder not between", value1, value2, "menuorder");
            return (Criteria) this;
        }

        public Criteria andIsAdminIsNull() {
            addCriterion("is_admin is null");
            return (Criteria) this;
        }

        public Criteria andIsAdminIsNotNull() {
            addCriterion("is_admin is not null");
            return (Criteria) this;
        }

        public Criteria andIsAdminEqualTo(Boolean value) {
            addCriterion("is_admin =", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminNotEqualTo(Boolean value) {
            addCriterion("is_admin <>", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminGreaterThan(Boolean value) {
            addCriterion("is_admin >", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_admin >=", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminLessThan(Boolean value) {
            addCriterion("is_admin <", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminLessThanOrEqualTo(Boolean value) {
            addCriterion("is_admin <=", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminIn(List<Boolean> values) {
            addCriterion("is_admin in", values, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminNotIn(List<Boolean> values) {
            addCriterion("is_admin not in", values, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminBetween(Boolean value1, Boolean value2) {
            addCriterion("is_admin between", value1, value2, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_admin not between", value1, value2, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkIsNull() {
            addCriterion("platform_mark is null");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkIsNotNull() {
            addCriterion("platform_mark is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkEqualTo(String value) {
            addCriterion("platform_mark =", value, "platformMark");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkNotEqualTo(String value) {
            addCriterion("platform_mark <>", value, "platformMark");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkGreaterThan(String value) {
            addCriterion("platform_mark >", value, "platformMark");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkGreaterThanOrEqualTo(String value) {
            addCriterion("platform_mark >=", value, "platformMark");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkLessThan(String value) {
            addCriterion("platform_mark <", value, "platformMark");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkLessThanOrEqualTo(String value) {
            addCriterion("platform_mark <=", value, "platformMark");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkLike(String value) {
            addCriterion("platform_mark like", value, "platformMark");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkNotLike(String value) {
            addCriterion("platform_mark not like", value, "platformMark");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkIn(List<String> values) {
            addCriterion("platform_mark in", values, "platformMark");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkNotIn(List<String> values) {
            addCriterion("platform_mark not in", values, "platformMark");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkBetween(String value1, String value2) {
            addCriterion("platform_mark between", value1, value2, "platformMark");
            return (Criteria) this;
        }

        public Criteria andPlatformMarkNotBetween(String value1, String value2) {
            addCriterion("platform_mark not between", value1, value2, "platformMark");
            return (Criteria) this;
        }

        public Criteria andSystemflagIsNull() {
            addCriterion("systemFlag is null");
            return (Criteria) this;
        }

        public Criteria andSystemflagIsNotNull() {
            addCriterion("systemFlag is not null");
            return (Criteria) this;
        }

        public Criteria andSystemflagEqualTo(String value) {
            addCriterion("systemFlag =", value, "systemflag");
            return (Criteria) this;
        }

        public Criteria andSystemflagNotEqualTo(String value) {
            addCriterion("systemFlag <>", value, "systemflag");
            return (Criteria) this;
        }

        public Criteria andSystemflagGreaterThan(String value) {
            addCriterion("systemFlag >", value, "systemflag");
            return (Criteria) this;
        }

        public Criteria andSystemflagGreaterThanOrEqualTo(String value) {
            addCriterion("systemFlag >=", value, "systemflag");
            return (Criteria) this;
        }

        public Criteria andSystemflagLessThan(String value) {
            addCriterion("systemFlag <", value, "systemflag");
            return (Criteria) this;
        }

        public Criteria andSystemflagLessThanOrEqualTo(String value) {
            addCriterion("systemFlag <=", value, "systemflag");
            return (Criteria) this;
        }

        public Criteria andSystemflagLike(String value) {
            addCriterion("systemFlag like", value, "systemflag");
            return (Criteria) this;
        }

        public Criteria andSystemflagNotLike(String value) {
            addCriterion("systemFlag not like", value, "systemflag");
            return (Criteria) this;
        }

        public Criteria andSystemflagIn(List<String> values) {
            addCriterion("systemFlag in", values, "systemflag");
            return (Criteria) this;
        }

        public Criteria andSystemflagNotIn(List<String> values) {
            addCriterion("systemFlag not in", values, "systemflag");
            return (Criteria) this;
        }

        public Criteria andSystemflagBetween(String value1, String value2) {
            addCriterion("systemFlag between", value1, value2, "systemflag");
            return (Criteria) this;
        }

        public Criteria andSystemflagNotBetween(String value1, String value2) {
            addCriterion("systemFlag not between", value1, value2, "systemflag");
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