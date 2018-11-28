package net.mcep.upms.dao.model;

import java.io.Serializable;

public class UpmsJobsOrganization implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer jobsOrganizationId;

    /**
     * 组织编号
     *
     * @mbg.generated
     */
    private Integer organizationId;

    /**
     * 岗位编号
     *
     * @mbg.generated
     */
    private Integer jobsId;

    private static final long serialVersionUID = 1L;

    public Integer getJobsOrganizationId() {
        return jobsOrganizationId;
    }

    public void setJobsOrganizationId(Integer jobsOrganizationId) {
        this.jobsOrganizationId = jobsOrganizationId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
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
        sb.append(", jobsOrganizationId=").append(jobsOrganizationId);
        sb.append(", organizationId=").append(organizationId);
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
        UpmsJobsOrganization other = (UpmsJobsOrganization) that;
        return (this.getJobsOrganizationId() == null ? other.getJobsOrganizationId() == null : this.getJobsOrganizationId().equals(other.getJobsOrganizationId()))
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()))
            && (this.getJobsId() == null ? other.getJobsId() == null : this.getJobsId().equals(other.getJobsId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getJobsOrganizationId() == null) ? 0 : getJobsOrganizationId().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        result = prime * result + ((getJobsId() == null) ? 0 : getJobsId().hashCode());
        return result;
    }
}