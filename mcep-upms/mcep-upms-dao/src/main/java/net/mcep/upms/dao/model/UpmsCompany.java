package net.mcep.upms.dao.model;

import java.io.Serializable;

public class UpmsCompany implements Serializable {
    /**
     * 公司id
     *
     * @mbg.generated
     */
    private Integer companyId;

    /**
     * 公司名
     *
     * @mbg.generated
     */
    private String companyName;

    /**
     * 代码
     *
     * @mbg.generated
     */
    private String companyCode;

    /**
     * 地址
     *
     * @mbg.generated
     */
    private String address;

    /**
     * 公司短名
     *
     * @mbg.generated
     */
    private String companynameShort;

    /**
     * 省份
     *
     * @mbg.generated
     */
    private String province;

    /**
     * 父公司id
     *
     * @mbg.generated
     */
    private Integer parentId;

    /**
     * 0-国有独资、1-国有控股、2-中外合资、3-中外合作、4-外商独资、5-民营
     *
     * @mbg.generated
     */
    private Integer companyNature;

    /**
     * 法定代表人
     *
     * @mbg.generated
     */
    private String representative;

    /**
     * 注册资本（万元）
     *
     * @mbg.generated
     */
    private Float registeredCapital;

    /**
     * 1内部，2外部
     *
     * @mbg.generated
     */
    private Integer innerOrOuter;

    /**
     * 业主ID
     *
     * @mbg.generated
     */
    private Integer companyProprietorId;

    /**
     * 介绍
     *
     * @mbg.generated
     */
    private String introduction;

    private static final long serialVersionUID = 1L;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanynameShort() {
        return companynameShort;
    }

    public void setCompanynameShort(String companynameShort) {
        this.companynameShort = companynameShort;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(Integer companyNature) {
        this.companyNature = companyNature;
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

    public Integer getInnerOrOuter() {
        return innerOrOuter;
    }

    public void setInnerOrOuter(Integer innerOrOuter) {
        this.innerOrOuter = innerOrOuter;
    }

    public Integer getCompanyProprietorId() {
        return companyProprietorId;
    }

    public void setCompanyProprietorId(Integer companyProprietorId) {
        this.companyProprietorId = companyProprietorId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyCode=").append(companyCode);
        sb.append(", address=").append(address);
        sb.append(", companynameShort=").append(companynameShort);
        sb.append(", province=").append(province);
        sb.append(", parentId=").append(parentId);
        sb.append(", companyNature=").append(companyNature);
        sb.append(", representative=").append(representative);
        sb.append(", registeredCapital=").append(registeredCapital);
        sb.append(", innerOrOuter=").append(innerOrOuter);
        sb.append(", companyProprietorId=").append(companyProprietorId);
        sb.append(", introduction=").append(introduction);
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
        UpmsCompany other = (UpmsCompany) that;
        return (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
                && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
                && (this.getCompanyCode() == null ? other.getCompanyCode() == null : this.getCompanyCode().equals(other.getCompanyCode()))
                && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
                && (this.getCompanynameShort() == null ? other.getCompanynameShort() == null : this.getCompanynameShort().equals(other.getCompanynameShort()))
                && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
                && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
                && (this.getCompanyNature() == null ? other.getCompanyNature() == null : this.getCompanyNature().equals(other.getCompanyNature()))
                && (this.getRepresentative() == null ? other.getRepresentative() == null : this.getRepresentative().equals(other.getRepresentative()))
                && (this.getRegisteredCapital() == null ? other.getRegisteredCapital() == null : this.getRegisteredCapital().equals(other.getRegisteredCapital()))
                && (this.getInnerOrOuter() == null ? other.getInnerOrOuter() == null : this.getInnerOrOuter().equals(other.getInnerOrOuter()))
                && (this.getCompanyProprietorId() == null ? other.getCompanyProprietorId() == null : this.getCompanyProprietorId().equals(other.getCompanyProprietorId()))
                && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getCompanyCode() == null) ? 0 : getCompanyCode().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getCompanynameShort() == null) ? 0 : getCompanynameShort().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getCompanyNature() == null) ? 0 : getCompanyNature().hashCode());
        result = prime * result + ((getRepresentative() == null) ? 0 : getRepresentative().hashCode());
        result = prime * result + ((getRegisteredCapital() == null) ? 0 : getRegisteredCapital().hashCode());
        result = prime * result + ((getInnerOrOuter() == null) ? 0 : getInnerOrOuter().hashCode());
        result = prime * result + ((getCompanyProprietorId() == null) ? 0 : getCompanyProprietorId().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
        return result;
    }
}