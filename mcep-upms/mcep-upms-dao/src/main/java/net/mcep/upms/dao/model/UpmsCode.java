package net.mcep.upms.dao.model;

import java.io.Serializable;

public class UpmsCode implements Serializable {
    private Long id;

    /**
     * 分类编码
     *
     * @mbg.generated
     */
    private String catNo;

    /**
     * 取值编码
     *
     * @mbg.generated
     */
    private String cdNo;

    /**
     * 取值名称
     *
     * @mbg.generated
     */
    private String cdName;

    /**
     * 状态
     *
     * @mbg.generated
     */
    private String ison;

    /**
     * 上级数据id
     *
     * @mbg.generated
     */
    private Long parentId;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Integer sort;

    /**
     * 资源id
     *
     * @mbg.generated
     */
    private Integer resourceId;

    /**
     * 资源名称
     *
     * @mbg.generated
     */
    private String resourceName;

    /**
     * 旧数据
     *
     * @mbg.generated
     */
    private String old;

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatNo() {
        return catNo;
    }

    public void setCatNo(String catNo) {
        this.catNo = catNo;
    }

    public String getCdNo() {
        return cdNo;
    }

    public void setCdNo(String cdNo) {
        this.cdNo = cdNo;
    }

    public String getCdName() {
        return cdName;
    }

    public void setCdName(String cdName) {
        this.cdName = cdName;
    }

    public String getIson() {
        return ison;
    }

    public void setIson(String ison) {
        this.ison = ison;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", catNo=").append(catNo);
        sb.append(", cdNo=").append(cdNo);
        sb.append(", cdName=").append(cdName);
        sb.append(", ison=").append(ison);
        sb.append(", parentId=").append(parentId);
        sb.append(", sort=").append(sort);
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
        UpmsCode other = (UpmsCode) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCatNo() == null ? other.getCatNo() == null : this.getCatNo().equals(other.getCatNo()))
            && (this.getCdNo() == null ? other.getCdNo() == null : this.getCdNo().equals(other.getCdNo()))
            && (this.getCdName() == null ? other.getCdName() == null : this.getCdName().equals(other.getCdName()))
            && (this.getIson() == null ? other.getIson() == null : this.getIson().equals(other.getIson()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCatNo() == null) ? 0 : getCatNo().hashCode());
        result = prime * result + ((getCdNo() == null) ? 0 : getCdNo().hashCode());
        result = prime * result + ((getCdName() == null) ? 0 : getCdName().hashCode());
        result = prime * result + ((getIson() == null) ? 0 : getIson().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        return result;
    }
}