package net.mcep.upms.dao.model;

import java.io.Serializable;

public class UpmsResourceUser implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer resourceUserId;

    /**
     * 资源编号
     *
     * @mbg.generated
     */
    private Integer resourceId;

    /**
     * 用户编号
     *
     * @mbg.generated
     */
    private Integer userId;

    private static final long serialVersionUID = 1L;

    public Integer getResourceUserId() {
        return resourceUserId;
    }

    public void setResourceUserId(Integer resourceUserId) {
        this.resourceUserId = resourceUserId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resourceUserId=").append(resourceUserId);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", userId=").append(userId);
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
        UpmsResourceUser other = (UpmsResourceUser) that;
        return (this.getResourceUserId() == null ? other.getResourceUserId() == null : this.getResourceUserId().equals(other.getResourceUserId()))
            && (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getResourceUserId() == null) ? 0 : getResourceUserId().hashCode());
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }
}