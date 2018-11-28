package net.mcep.upms.dao.model;

import java.io.Serializable;

public class UpmsRoleOrganization implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer roleOrganizationId;

    /**
     * 角色编号
     *
     * @mbg.generated
     */
    private Integer roleId;

    /**
     * 组织编号
     *
     * @mbg.generated
     */
    private Integer organizationId;

    private static final long serialVersionUID = 1L;

    public Integer getRoleOrganizationId() {
        return roleOrganizationId;
    }

    public void setRoleOrganizationId(Integer roleOrganizationId) {
        this.roleOrganizationId = roleOrganizationId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleOrganizationId=").append(roleOrganizationId);
        sb.append(", roleId=").append(roleId);
        sb.append(", organizationId=").append(organizationId);
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
        UpmsRoleOrganization other = (UpmsRoleOrganization) that;
        return (this.getRoleOrganizationId() == null ? other.getRoleOrganizationId() == null : this.getRoleOrganizationId().equals(other.getRoleOrganizationId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleOrganizationId() == null) ? 0 : getRoleOrganizationId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        return result;
    }
}