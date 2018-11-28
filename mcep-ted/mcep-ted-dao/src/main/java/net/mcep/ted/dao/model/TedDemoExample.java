package net.mcep.ted.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TedDemoExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public TedDemoExample() {
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

        public Criteria andProprietorIdIsNull() {
            addCriterion("proprietor_id is null");
            return (Criteria) this;
        }

        public Criteria andProprietorIdIsNotNull() {
            addCriterion("proprietor_id is not null");
            return (Criteria) this;
        }

        public Criteria andProprietorIdEqualTo(Integer value) {
            addCriterion("proprietor_id =", value, "proprietorId");
            return (Criteria) this;
        }

        public Criteria andProprietorIdNotEqualTo(Integer value) {
            addCriterion("proprietor_id <>", value, "proprietorId");
            return (Criteria) this;
        }

        public Criteria andProprietorIdGreaterThan(Integer value) {
            addCriterion("proprietor_id >", value, "proprietorId");
            return (Criteria) this;
        }

        public Criteria andProprietorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("proprietor_id >=", value, "proprietorId");
            return (Criteria) this;
        }

        public Criteria andProprietorIdLessThan(Integer value) {
            addCriterion("proprietor_id <", value, "proprietorId");
            return (Criteria) this;
        }

        public Criteria andProprietorIdLessThanOrEqualTo(Integer value) {
            addCriterion("proprietor_id <=", value, "proprietorId");
            return (Criteria) this;
        }

        public Criteria andProprietorIdIn(List<Integer> values) {
            addCriterion("proprietor_id in", values, "proprietorId");
            return (Criteria) this;
        }

        public Criteria andProprietorIdNotIn(List<Integer> values) {
            addCriterion("proprietor_id not in", values, "proprietorId");
            return (Criteria) this;
        }

        public Criteria andProprietorIdBetween(Integer value1, Integer value2) {
            addCriterion("proprietor_id between", value1, value2, "proprietorId");
            return (Criteria) this;
        }

        public Criteria andProprietorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("proprietor_id not between", value1, value2, "proprietorId");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeIsNull() {
            addCriterion("proprietor_code is null");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeIsNotNull() {
            addCriterion("proprietor_code is not null");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeEqualTo(String value) {
            addCriterion("proprietor_code =", value, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeNotEqualTo(String value) {
            addCriterion("proprietor_code <>", value, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeGreaterThan(String value) {
            addCriterion("proprietor_code >", value, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("proprietor_code >=", value, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeLessThan(String value) {
            addCriterion("proprietor_code <", value, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeLessThanOrEqualTo(String value) {
            addCriterion("proprietor_code <=", value, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeLike(String value) {
            addCriterion("proprietor_code like", value, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeNotLike(String value) {
            addCriterion("proprietor_code not like", value, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeIn(List<String> values) {
            addCriterion("proprietor_code in", values, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeNotIn(List<String> values) {
            addCriterion("proprietor_code not in", values, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeBetween(String value1, String value2) {
            addCriterion("proprietor_code between", value1, value2, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorCodeNotBetween(String value1, String value2) {
            addCriterion("proprietor_code not between", value1, value2, "proprietorCode");
            return (Criteria) this;
        }

        public Criteria andProprietorNameIsNull() {
            addCriterion("proprietor_name is null");
            return (Criteria) this;
        }

        public Criteria andProprietorNameIsNotNull() {
            addCriterion("proprietor_name is not null");
            return (Criteria) this;
        }

        public Criteria andProprietorNameEqualTo(String value) {
            addCriterion("proprietor_name =", value, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andProprietorNameNotEqualTo(String value) {
            addCriterion("proprietor_name <>", value, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andProprietorNameGreaterThan(String value) {
            addCriterion("proprietor_name >", value, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andProprietorNameGreaterThanOrEqualTo(String value) {
            addCriterion("proprietor_name >=", value, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andProprietorNameLessThan(String value) {
            addCriterion("proprietor_name <", value, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andProprietorNameLessThanOrEqualTo(String value) {
            addCriterion("proprietor_name <=", value, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andProprietorNameLike(String value) {
            addCriterion("proprietor_name like", value, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andProprietorNameNotLike(String value) {
            addCriterion("proprietor_name not like", value, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andProprietorNameIn(List<String> values) {
            addCriterion("proprietor_name in", values, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andProprietorNameNotIn(List<String> values) {
            addCriterion("proprietor_name not in", values, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andProprietorNameBetween(String value1, String value2) {
            addCriterion("proprietor_name between", value1, value2, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andProprietorNameNotBetween(String value1, String value2) {
            addCriterion("proprietor_name not between", value1, value2, "proprietorName");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andContactIdIsNull() {
            addCriterion("contact_id is null");
            return (Criteria) this;
        }

        public Criteria andContactIdIsNotNull() {
            addCriterion("contact_id is not null");
            return (Criteria) this;
        }

        public Criteria andContactIdEqualTo(Integer value) {
            addCriterion("contact_id =", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdNotEqualTo(Integer value) {
            addCriterion("contact_id <>", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdGreaterThan(Integer value) {
            addCriterion("contact_id >", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("contact_id >=", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdLessThan(Integer value) {
            addCriterion("contact_id <", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdLessThanOrEqualTo(Integer value) {
            addCriterion("contact_id <=", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdIn(List<Integer> values) {
            addCriterion("contact_id in", values, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdNotIn(List<Integer> values) {
            addCriterion("contact_id not in", values, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdBetween(Integer value1, Integer value2) {
            addCriterion("contact_id between", value1, value2, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdNotBetween(Integer value1, Integer value2) {
            addCriterion("contact_id not between", value1, value2, "contactId");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIsNull() {
            addCriterion("credit_code is null");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIsNotNull() {
            addCriterion("credit_code is not null");
            return (Criteria) this;
        }

        public Criteria andCreditCodeEqualTo(String value) {
            addCriterion("credit_code =", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotEqualTo(String value) {
            addCriterion("credit_code <>", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeGreaterThan(String value) {
            addCriterion("credit_code >", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeGreaterThanOrEqualTo(String value) {
            addCriterion("credit_code >=", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLessThan(String value) {
            addCriterion("credit_code <", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLessThanOrEqualTo(String value) {
            addCriterion("credit_code <=", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLike(String value) {
            addCriterion("credit_code like", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotLike(String value) {
            addCriterion("credit_code not like", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIn(List<String> values) {
            addCriterion("credit_code in", values, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotIn(List<String> values) {
            addCriterion("credit_code not in", values, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeBetween(String value1, String value2) {
            addCriterion("credit_code between", value1, value2, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotBetween(String value1, String value2) {
            addCriterion("credit_code not between", value1, value2, "creditCode");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureIsNull() {
            addCriterion("proprietor_nature is null");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureIsNotNull() {
            addCriterion("proprietor_nature is not null");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureEqualTo(Integer value) {
            addCriterion("proprietor_nature =", value, "proprietorNature");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureNotEqualTo(Integer value) {
            addCriterion("proprietor_nature <>", value, "proprietorNature");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureGreaterThan(Integer value) {
            addCriterion("proprietor_nature >", value, "proprietorNature");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureGreaterThanOrEqualTo(Integer value) {
            addCriterion("proprietor_nature >=", value, "proprietorNature");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureLessThan(Integer value) {
            addCriterion("proprietor_nature <", value, "proprietorNature");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureLessThanOrEqualTo(Integer value) {
            addCriterion("proprietor_nature <=", value, "proprietorNature");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureIn(List<Integer> values) {
            addCriterion("proprietor_nature in", values, "proprietorNature");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureNotIn(List<Integer> values) {
            addCriterion("proprietor_nature not in", values, "proprietorNature");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureBetween(Integer value1, Integer value2) {
            addCriterion("proprietor_nature between", value1, value2, "proprietorNature");
            return (Criteria) this;
        }

        public Criteria andProprietorNatureNotBetween(Integer value1, Integer value2) {
            addCriterion("proprietor_nature not between", value1, value2, "proprietorNature");
            return (Criteria) this;
        }

        public Criteria andRepresentativeIsNull() {
            addCriterion("representative is null");
            return (Criteria) this;
        }

        public Criteria andRepresentativeIsNotNull() {
            addCriterion("representative is not null");
            return (Criteria) this;
        }

        public Criteria andRepresentativeEqualTo(String value) {
            addCriterion("representative =", value, "representative");
            return (Criteria) this;
        }

        public Criteria andRepresentativeNotEqualTo(String value) {
            addCriterion("representative <>", value, "representative");
            return (Criteria) this;
        }

        public Criteria andRepresentativeGreaterThan(String value) {
            addCriterion("representative >", value, "representative");
            return (Criteria) this;
        }

        public Criteria andRepresentativeGreaterThanOrEqualTo(String value) {
            addCriterion("representative >=", value, "representative");
            return (Criteria) this;
        }

        public Criteria andRepresentativeLessThan(String value) {
            addCriterion("representative <", value, "representative");
            return (Criteria) this;
        }

        public Criteria andRepresentativeLessThanOrEqualTo(String value) {
            addCriterion("representative <=", value, "representative");
            return (Criteria) this;
        }

        public Criteria andRepresentativeLike(String value) {
            addCriterion("representative like", value, "representative");
            return (Criteria) this;
        }

        public Criteria andRepresentativeNotLike(String value) {
            addCriterion("representative not like", value, "representative");
            return (Criteria) this;
        }

        public Criteria andRepresentativeIn(List<String> values) {
            addCriterion("representative in", values, "representative");
            return (Criteria) this;
        }

        public Criteria andRepresentativeNotIn(List<String> values) {
            addCriterion("representative not in", values, "representative");
            return (Criteria) this;
        }

        public Criteria andRepresentativeBetween(String value1, String value2) {
            addCriterion("representative between", value1, value2, "representative");
            return (Criteria) this;
        }

        public Criteria andRepresentativeNotBetween(String value1, String value2) {
            addCriterion("representative not between", value1, value2, "representative");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIsNull() {
            addCriterion("registered_capital is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIsNotNull() {
            addCriterion("registered_capital is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalEqualTo(Float value) {
            addCriterion("registered_capital =", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotEqualTo(Float value) {
            addCriterion("registered_capital <>", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalGreaterThan(Float value) {
            addCriterion("registered_capital >", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalGreaterThanOrEqualTo(Float value) {
            addCriterion("registered_capital >=", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLessThan(Float value) {
            addCriterion("registered_capital <", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLessThanOrEqualTo(Float value) {
            addCriterion("registered_capital <=", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIn(List<Float> values) {
            addCriterion("registered_capital in", values, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotIn(List<Float> values) {
            addCriterion("registered_capital not in", values, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalBetween(Float value1, Float value2) {
            addCriterion("registered_capital between", value1, value2, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotBetween(Float value1, Float value2) {
            addCriterion("registered_capital not between", value1, value2, "registeredCapital");
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