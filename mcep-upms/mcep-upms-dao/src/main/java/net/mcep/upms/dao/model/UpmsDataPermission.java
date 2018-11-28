package net.mcep.upms.dao.model;

import java.io.Serializable;

public class UpmsDataPermission implements Serializable {
    /**
     * 数据权限id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 权限id
     *
     * @mbg.generated
     */
    private Integer permissionId;

    /**
     * 资源id
     *
     * @mbg.generated
     */
    private Integer resourceId;

    /**
     * 授权类型
     *
     * @mbg.generated
     */
    private String authorizationType;

    /**
     * 权限名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 资源名称
     *
     * @mbg.generated
     */
    private String resourceName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getAuthorizationType() {
        return authorizationType;
    }

    public void setAuthorizationType(String authorizationType) {
        this.authorizationType = authorizationType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", authorizationType=").append(authorizationType);
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
        UpmsDataPermission other = (UpmsDataPermission) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPermissionId() == null ? other.getPermissionId() == null : this.getPermissionId().equals(other.getPermissionId()))
            && (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
            && (this.getAuthorizationType() == null ? other.getAuthorizationType() == null : this.getAuthorizationType().equals(other.getAuthorizationType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPermissionId() == null) ? 0 : getPermissionId().hashCode());
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getAuthorizationType() == null) ? 0 : getAuthorizationType().hashCode());
        return result;
    }
}