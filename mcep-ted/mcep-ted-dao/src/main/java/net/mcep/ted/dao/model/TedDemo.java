package net.mcep.ted.dao.model;

import java.io.Serializable;

public class TedDemo implements Serializable {
    private Integer proprietorId;

    /**
     * 业主编码
     *
     * @mbg.generated
     */
    private String proprietorCode;

    /**
     * 业主名称
     *
     * @mbg.generated
     */
    private String proprietorName;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String memo;

    private String state;

    /**
     * 联络人id
     *
     * @mbg.generated
     */
    private Integer contactId;

    /**
     * 企业信用代码
     *
     * @mbg.generated
     */
    private String creditCode;

    /**
     * 0-国有独资、1-国有控股、2-中外合资、3-中外合作、4-外商独资、5-民营
     *
     * @mbg.generated
     */
    private Integer proprietorNature;

    private String representative;

    private Float registeredCapital;

    private static final long serialVersionUID = 1L;

    public Integer getProprietorId() {
        return proprietorId;
    }

    public void setProprietorId(Integer proprietorId) {
        this.proprietorId = proprietorId;
    }

    public String getProprietorCode() {
        return proprietorCode;
    }

    public void setProprietorCode(String proprietorCode) {
        this.proprietorCode = proprietorCode;
    }

    public String getProprietorName() {
        return proprietorName;
    }

    public void setProprietorName(String proprietorName) {
        this.proprietorName = proprietorName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public Integer getProprietorNature() {
        return proprietorNature;
    }

    public void setProprietorNature(Integer proprietorNature) {
        this.proprietorNature = proprietorNature;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public Float getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Float registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", proprietorId=").append(proprietorId);
        sb.append(", proprietorCode=").append(proprietorCode);
        sb.append(", proprietorName=").append(proprietorName);
        sb.append(", memo=").append(memo);
        sb.append(", state=").append(state);
        sb.append(", contactId=").append(contactId);
        sb.append(", creditCode=").append(creditCode);
        sb.append(", proprietorNature=").append(proprietorNature);
        sb.append(", representative=").append(representative);
        sb.append(", registeredCapital=").append(registeredCapital);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TedDemo other = (TedDemo) that;
        return (this.getProprietorId() == null ? other.getProprietorId() == null : this.getProprietorId().equals(other.getProprietorId()))
            && (this.getProprietorCode() == null ? other.getProprietorCode() == null : this.getProprietorCode().equals(other.getProprietorCode()))
            && (this.getProprietorName() == null ? other.getProprietorName() == null : this.getProprietorName().equals(other.getProprietorName()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getContactId() == null ? other.getContactId() == null : this.getContactId().equals(other.getContactId()))
            && (this.getCreditCode() == null ? other.getCreditCode() == null : this.getCreditCode().equals(other.getCreditCode()))
            && (this.getProprietorNature() == null ? other.getProprietorNature() == null : this.getProprietorNature().equals(other.getProprietorNature()))
            && (this.getRepresentative() == null ? other.getRepresentative() == null : this.getRepresentative().equals(other.getRepresentative()))
            && (this.getRegisteredCapital() == null ? other.getRegisteredCapital() == null : this.getRegisteredCapital().equals(other.getRegisteredCapital()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProprietorId() == null) ? 0 : getProprietorId().hashCode());
        result = prime * result + ((getProprietorCode() == null) ? 0 : getProprietorCode().hashCode());
        result = prime * result + ((getProprietorName() == null) ? 0 : getProprietorName().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getContactId() == null) ? 0 : getContactId().hashCode());
        result = prime * result + ((getCreditCode() == null) ? 0 : getCreditCode().hashCode());
        result = prime * result + ((getProprietorNature() == null) ? 0 : getProprietorNature().hashCode());
        result = prime * result + ((getRepresentative() == null) ? 0 : getRepresentative().hashCode());
        result = prime * result + ((getRegisteredCapital() == null) ? 0 : getRegisteredCapital().hashCode());
        return result;
    }
}