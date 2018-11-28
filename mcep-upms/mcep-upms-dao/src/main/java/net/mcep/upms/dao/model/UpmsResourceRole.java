package net.mcep.upms.dao.model;

import java.io.Serializable;

public class UpmsResourceRole implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer upmsResourceId;

    /**
     * 资源编号
     *
     * @mbg.generated
     */
    private Integer resourceId;

    /**
     * 角色编号
     *
     * @mbg.generated
     */
    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public Integer getUpmsResourceId() {
        return upmsResourceId;
    }

    public void setUpmsResourceId(Integer upmsResourceId) {
        this.upmsResourceId = upmsResourceId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", upmsResourceId=").append(upmsResourceId);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", roleId=").append(roleId);
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
        UpmsResourceRole other = (UpmsResourceRole) that;
        return (this.getUpmsResourceId() == null ? other.getUpmsResourceId() == null : this.getUpmsResourceId().equals(other.getUpmsResourceId()))
            && (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUpmsResourceId() == null) ? 0 : getUpmsResourceId().hashCode());
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        return result;
    }
}