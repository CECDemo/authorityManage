package com.wbliu.cecdemo.userManager.pojo;

import java.util.ArrayList;
import java.util.List;

public class RolesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RolesExample() {
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

        public Criteria andRolesnameIsNull() {
            addCriterion("rolesname is null");
            return (Criteria) this;
        }

        public Criteria andRolesnameIsNotNull() {
            addCriterion("rolesname is not null");
            return (Criteria) this;
        }

        public Criteria andRolesnameEqualTo(String value) {
            addCriterion("rolesname =", value, "rolesname");
            return (Criteria) this;
        }

        public Criteria andRolesnameNotEqualTo(String value) {
            addCriterion("rolesname <>", value, "rolesname");
            return (Criteria) this;
        }

        public Criteria andRolesnameGreaterThan(String value) {
            addCriterion("rolesname >", value, "rolesname");
            return (Criteria) this;
        }

        public Criteria andRolesnameGreaterThanOrEqualTo(String value) {
            addCriterion("rolesname >=", value, "rolesname");
            return (Criteria) this;
        }

        public Criteria andRolesnameLessThan(String value) {
            addCriterion("rolesname <", value, "rolesname");
            return (Criteria) this;
        }

        public Criteria andRolesnameLessThanOrEqualTo(String value) {
            addCriterion("rolesname <=", value, "rolesname");
            return (Criteria) this;
        }

        public Criteria andRolesnameLike(String value) {
            addCriterion("rolesname like", value, "rolesname");
            return (Criteria) this;
        }

        public Criteria andRolesnameNotLike(String value) {
            addCriterion("rolesname not like", value, "rolesname");
            return (Criteria) this;
        }

        public Criteria andRolesnameIn(List<String> values) {
            addCriterion("rolesname in", values, "rolesname");
            return (Criteria) this;
        }

        public Criteria andRolesnameNotIn(List<String> values) {
            addCriterion("rolesname not in", values, "rolesname");
            return (Criteria) this;
        }

        public Criteria andRolesnameBetween(String value1, String value2) {
            addCriterion("rolesname between", value1, value2, "rolesname");
            return (Criteria) this;
        }

        public Criteria andRolesnameNotBetween(String value1, String value2) {
            addCriterion("rolesname not between", value1, value2, "rolesname");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andRolelevelIsNull() {
            addCriterion("rolelevel is null");
            return (Criteria) this;
        }

        public Criteria andRolelevelIsNotNull() {
            addCriterion("rolelevel is not null");
            return (Criteria) this;
        }

        public Criteria andRolelevelEqualTo(Integer value) {
            addCriterion("rolelevel =", value, "rolelevel");
            return (Criteria) this;
        }

        public Criteria andRolelevelNotEqualTo(Integer value) {
            addCriterion("rolelevel <>", value, "rolelevel");
            return (Criteria) this;
        }

        public Criteria andRolelevelGreaterThan(Integer value) {
            addCriterion("rolelevel >", value, "rolelevel");
            return (Criteria) this;
        }

        public Criteria andRolelevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("rolelevel >=", value, "rolelevel");
            return (Criteria) this;
        }

        public Criteria andRolelevelLessThan(Integer value) {
            addCriterion("rolelevel <", value, "rolelevel");
            return (Criteria) this;
        }

        public Criteria andRolelevelLessThanOrEqualTo(Integer value) {
            addCriterion("rolelevel <=", value, "rolelevel");
            return (Criteria) this;
        }

        public Criteria andRolelevelIn(List<Integer> values) {
            addCriterion("rolelevel in", values, "rolelevel");
            return (Criteria) this;
        }

        public Criteria andRolelevelNotIn(List<Integer> values) {
            addCriterion("rolelevel not in", values, "rolelevel");
            return (Criteria) this;
        }

        public Criteria andRolelevelBetween(Integer value1, Integer value2) {
            addCriterion("rolelevel between", value1, value2, "rolelevel");
            return (Criteria) this;
        }

        public Criteria andRolelevelNotBetween(Integer value1, Integer value2) {
            addCriterion("rolelevel not between", value1, value2, "rolelevel");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelIsNull() {
            addCriterion("downloadlevel is null");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelIsNotNull() {
            addCriterion("downloadlevel is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelEqualTo(Integer value) {
            addCriterion("downloadlevel =", value, "downloadlevel");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelNotEqualTo(Integer value) {
            addCriterion("downloadlevel <>", value, "downloadlevel");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelGreaterThan(Integer value) {
            addCriterion("downloadlevel >", value, "downloadlevel");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("downloadlevel >=", value, "downloadlevel");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelLessThan(Integer value) {
            addCriterion("downloadlevel <", value, "downloadlevel");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelLessThanOrEqualTo(Integer value) {
            addCriterion("downloadlevel <=", value, "downloadlevel");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelIn(List<Integer> values) {
            addCriterion("downloadlevel in", values, "downloadlevel");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelNotIn(List<Integer> values) {
            addCriterion("downloadlevel not in", values, "downloadlevel");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelBetween(Integer value1, Integer value2) {
            addCriterion("downloadlevel between", value1, value2, "downloadlevel");
            return (Criteria) this;
        }

        public Criteria andDownloadlevelNotBetween(Integer value1, Integer value2) {
            addCriterion("downloadlevel not between", value1, value2, "downloadlevel");
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