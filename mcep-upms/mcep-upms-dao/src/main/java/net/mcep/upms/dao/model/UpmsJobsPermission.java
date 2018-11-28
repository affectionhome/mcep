package net.mcep.upms.dao.model;

import java.io.Serializable;

public class UpmsJobsPermission implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer jobsPermissionId;

    /**
     * 岗位编号
     *
     * @mbg.generated
     */
    private Integer jobsId;

    /**
     * 权限编号
     *
     * @mbg.generated
     */
    private Integer permissionId;

    private static final long serialVersionUID = 1L;

    public Integer getJobsPermissionId() {
        return jobsPermissionId;
    }

    public void setJobsPermissionId(Integer jobsPermissionId) {
        this.jobsPermissionId = jobsPermissionId;
    }

    public Integer getJobsId() {
        return jobsId;
    }

    public void setJobsId(Integer jobsId) {
        this.jobsId = jobsId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jobsPermissionId=").append(jobsPermissionId);
        sb.append(", jobsId=").append(jobsId);
        sb.append(", permissionId=").append(permissionId);
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
        UpmsJobsPermission other = (UpmsJobsPermission) that;
        return (this.getJobsPermissionId() == null ? other.getJobsPermissionId() == null : this.getJobsPermissionId().equals(other.getJobsPermissionId()))
            && (this.getJobsId() == null ? other.getJobsId() == null : this.getJobsId().equals(other.getJobsId()))
            && (this.getPermissionId() == null ? other.getPermissionId() == null : this.getPermissionId().equals(other.getPermissionId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getJobsPermissionId() == null) ? 0 : getJobsPermissionId().hashCode());
        result = prime * result + ((getJobsId() == null) ? 0 : getJobsId().hashCode());
        result = prime * result + ((getPermissionId() == null) ? 0 : getPermissionId().hashCode());
        return result;
    }
}