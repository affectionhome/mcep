package net.mcep.upms.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UpmsCodeExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public UpmsCodeExample() {
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

    protected abstract static class GeneratedCriteria implements Serializable {
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

        public Criteria andCatNoIsNull() {
            addCriterion("cat_no is null");
            return (Criteria) this;
        }

        public Criteria andCatNoIsNotNull() {
            addCriterion("cat_no is not null");
            return (Criteria) this;
        }

        public Criteria andCatNoEqualTo(String value) {
            addCriterion("cat_no =", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoNotEqualTo(String value) {
            addCriterion("cat_no <>", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoGreaterThan(String value) {
            addCriterion("cat_no >", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoGreaterThanOrEqualTo(String value) {
            addCriterion("cat_no >=", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoLessThan(String value) {
            addCriterion("cat_no <", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoLessThanOrEqualTo(String value) {
            addCriterion("cat_no <=", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoLike(String value) {
            addCriterion("cat_no like", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoNotLike(String value) {
            addCriterion("cat_no not like", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoIn(List<String> values) {
            addCriterion("cat_no in", values, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoNotIn(List<String> values) {
            addCriterion("cat_no not in", values, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoBetween(String value1, String value2) {
            addCriterion("cat_no between", value1, value2, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoNotBetween(String value1, String value2) {
            addCriterion("cat_no not between", value1, value2, "catNo");
            return (Criteria) this;
        }

        public Criteria andCdNoIsNull() {
            addCriterion("cd_no is null");
            return (Criteria) this;
        }

        public Criteria andCdNoIsNotNull() {
            addCriterion("cd_no is not null");
            return (Criteria) this;
        }

        public Criteria andCdNoEqualTo(String value) {
            addCriterion("cd_no =", value, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoEqualTo1(String value) {
            addCriterion("upms_code.cd_no =", value, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoNotEqualTo(String value) {
            addCriterion("cd_no <>", value, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoGreaterThan(String value) {
            addCriterion("cd_no >", value, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoGreaterThanOrEqualTo(String value) {
            addCriterion("cd_no >=", value, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoLessThan(String value) {
            addCriterion("cd_no <", value, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoLessThanOrEqualTo(String value) {
            addCriterion("cd_no <=", value, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoLike(String value) {
            addCriterion("cd_no like", value, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoNotLike(String value) {
            addCriterion("cd_no not like", value, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoIn(List<String> values) {
            addCriterion("cd_no in", values, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoNotIn(List<String> values) {
            addCriterion("cd_no not in", values, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoBetween(String value1, String value2) {
            addCriterion("cd_no between", value1, value2, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNoNotBetween(String value1, String value2) {
            addCriterion("cd_no not between", value1, value2, "cdNo");
            return (Criteria) this;
        }

        public Criteria andCdNameIsNull() {
            addCriterion("cd_name is null");
            return (Criteria) this;
        }

        public Criteria andCdNameIsNotNull() {
            addCriterion("cd_name is not null");
            return (Criteria) this;
        }

        public Criteria andCdNameEqualTo(String value) {
            addCriterion("cd_name =", value, "cdName");
            return (Criteria) this;
        }

        public Criteria andCdNameNotEqualTo(String value) {
            addCriterion("cd_name <>", value, "cdName");
            return (Criteria) this;
        }

        public Criteria andCdNameGreaterThan(String value) {
            addCriterion("cd_name >", value, "cdName");
            return (Criteria) this;
        }

        public Criteria andCdNameGreaterThanOrEqualTo(String value) {
            addCriterion("cd_name >=", value, "cdName");
            return (Criteria) this;
        }

        public Criteria andCdNameLessThan(String value) {
            addCriterion("cd_name <", value, "cdName");
            return (Criteria) this;
        }

        public Criteria andCdNameLessThanOrEqualTo(String value) {
            addCriterion("cd_name <=", value, "cdName");
            return (Criteria) this;
        }

        public Criteria andCdNameLike(String value) {
            addCriterion("cd_name like", value, "cdName");
            return (Criteria) this;
        }

        public Criteria andCdNameNotLike(String value) {
            addCriterion("cd_name not like", value, "cdName");
            return (Criteria) this;
        }

        public Criteria andCdNameIn(List<String> values) {
            addCriterion("cd_name in", values, "cdName");
            return (Criteria) this;
        }

        public Criteria andCdNameNotIn(List<String> values) {
            addCriterion("cd_name not in", values, "cdName");
            return (Criteria) this;
        }

        public Criteria andCdNameBetween(String value1, String value2) {
            addCriterion("cd_name between", value1, value2, "cdName");
            return (Criteria) this;
        }

        public Criteria andCdNameNotBetween(String value1, String value2) {
            addCriterion("cd_name not between", value1, value2, "cdName");
            return (Criteria) this;
        }

        public Criteria andIsonIsNull() {
            addCriterion("ison is null");
            return (Criteria) this;
        }

        public Criteria andIsonIsNotNull() {
            addCriterion("ison is not null");
            return (Criteria) this;
        }

        public Criteria andIsonEqualTo(String value) {
            addCriterion("ison =", value, "ison");
            return (Criteria) this;
        }

        public Criteria andIsonNotEqualTo(String value) {
            addCriterion("ison <>", value, "ison");
            return (Criteria) this;
        }

        public Criteria andIsonGreaterThan(String value) {
            addCriterion("ison >", value, "ison");
            return (Criteria) this;
        }

        public Criteria andIsonGreaterThanOrEqualTo(String value) {
            addCriterion("ison >=", value, "ison");
            return (Criteria) this;
        }

        public Criteria andIsonLessThan(String value) {
            addCriterion("ison <", value, "ison");
            return (Criteria) this;
        }

        public Criteria andIsonLessThanOrEqualTo(String value) {
            addCriterion("ison <=", value, "ison");
            return (Criteria) this;
        }

        public Criteria andIsonLike(String value) {
            addCriterion("ison like", value, "ison");
            return (Criteria) this;
        }

        public Criteria andIsonNotLike(String value) {
            addCriterion("ison not like", value, "ison");
            return (Criteria) this;
        }

        public Criteria andIsonIn(List<String> values) {
            addCriterion("ison in", values, "ison");
            return (Criteria) this;
        }

        public Criteria andIsonNotIn(List<String> values) {
            addCriterion("ison not in", values, "ison");
            return (Criteria) this;
        }

        public Criteria andIsonBetween(String value1, String value2) {
            addCriterion("ison between", value1, value2, "ison");
            return (Criteria) this;
        }

        public Criteria andIsonNotBetween(String value1, String value2) {
            addCriterion("ison not between", value1, value2, "ison");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
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