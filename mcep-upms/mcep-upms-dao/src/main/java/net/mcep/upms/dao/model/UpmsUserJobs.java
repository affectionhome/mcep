package net.mcep.upms.dao.model;

import java.io.Serializable;

public class UpmsUserJobs implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer userJobsId;

    /**
     * 用户编号
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 岗位编号
     *
     * @mbg.generated
     */
    private Integer jobsId;

    private static final long serialVersionUID = 1L;

    public Integer getUserJobsId() {
        return userJobsId;
    }

    public void setUserJobsId(Integer userJobsId) {
        this.userJobsId = userJobsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        sb.append(", userJobsId=").append(userJobsId);
        sb.append(", userId=").append(userId);
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
        UpmsUserJobs other = (UpmsUserJobs) that;
        return (this.getUserJobsId() == null ? other.getUserJobsId() == null : this.getUserJobsId().equals(other.getUserJobsId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getJobsId() == null ? other.getJobsId() == null : this.getJobsId().equals(other.getJobsId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserJobsId() == null) ? 0 : getUserJobsId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getJobsId() == null) ? 0 : getJobsId().hashCode());
        return result;
    }
}