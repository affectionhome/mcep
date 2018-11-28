package net.mcep.upms.dao.model;

import java.io.Serializable;

public class UpmsResourceJobs implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer resourceJobsId;

    /**
     * 资源编号
     *
     * @mbg.generated
     */
    private Integer resourceId;

    /**
     * 岗位编号
     *
     * @mbg.generated
     */
    private Integer jobsId;

    /**
     * 用户编号
     *
     * @mbg.generated
     */
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private static final long serialVersionUID = 1L;

    public Integer getResourceJobsId() {
        return resourceJobsId;
    }

    public void setResourceJobsId(Integer resourceJobsId) {
        this.resourceJobsId = resourceJobsId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getJobsId() {
        return jobsId;
    }

    public void setJobsId(Integer jobsId) {
        this.jobsId = jobsId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resourceJobsId=").append(resourceJobsId);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", jobsId=").append(jobsId);
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
        UpmsResourceJobs other = (UpmsResourceJobs) that;
        return (this.getResourceJobsId() == null ? other.getResourceJobsId() == null : this.getResourceJobsId().equals(other.getResourceJobsId()))
            && (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
            && (this.getJobsId() == null ? other.getJobsId() == null : this.getJobsId().equals(other.getJobsId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getResourceJobsId() == null) ? 0 : getResourceJobsId().hashCode());
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getJobsId() == null) ? 0 : getJobsId().hashCode());
        return result;
    }
}