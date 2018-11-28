package net.mcep.upms.dao.model;

import java.io.Serializable;

public class UpmsOrganization implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer organizationId;

    /**
     * 所属上级
     *
     * @mbg.generated
     */
    private Integer pid;

    /**
     * 组织名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 组织描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Long ctime;
//////////////////////////////////////////////////////
    /**
     * 组织机构编码
     *
     * @mbg.generated
     */
    private String organizationCoding;

    /**
     * 属性（是否虚拟组织）
     *
     * @mbg.generated
     */
    private Integer property;

    /**
     * 定边（有多少人）
     *
     * @mbg.generated
     */
    private Integer enact;

    /**
     * 分管领导
     *
     * @mbg.generated
     */
    private String manageLead;

    /**
     * 是否内部组织
     *
     * @mbg.generated
     */
    private Integer interior;

    /**
     * 简称
     *
     * @mbg.generated
     */
    private String abbreviation;

    /**
     * 上级组织名称
     *
     * @mbg.generated
     */
    private String pname;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getOrganizationCoding() {
        return organizationCoding;
    }

    public void setOrganizationCoding(String organizationCoding) {
        this.organizationCoding = organizationCoding;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Integer getEnact() {
        return enact;
    }

    public void setEnact(Integer enact) {
        this.enact = enact;
    }

    public String getManageLead() {
        return manageLead;
    }

    public void setManageLead(String manageLead) {
        this.manageLead = manageLead;
    }

    public Integer getInterior() {
        return interior;
    }

    public void setInterior(Integer interior) {
        this.interior = interior;
    }

    private static final long serialVersionUID = 1L;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", organizationId=").append(organizationId);
        sb.append(", pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", ctime=").append(ctime);
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
        UpmsOrganization other = (UpmsOrganization) that;
        return (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        return result;
    }
}