package net.mcep.upms.dao.model;

import java.io.Serializable;
import java.util.List;

public class UpmsCodeClass implements Serializable {
    private Integer id;

    /**
     * 分类编码
     *
     * @mbg.generated
     */
    private String catNo;

    /**
     * 分类名称
     *
     * @mbg.generated
     */
    private String catName;

    private List<UpmsCode> upmsCodeList;

    public List<UpmsCode> getUpmsCodeList() {
        return upmsCodeList;
    }

    public void setUpmsCodeList(List<UpmsCode> upmsCodeList) {
        this.upmsCodeList = upmsCodeList;
    }



    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatNo() {
        return catNo;
    }

    public void setCatNo(String catNo) {
        this.catNo = catNo;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", catNo=").append(catNo);
        sb.append(", catName=").append(catName);
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
        UpmsCodeClass other = (UpmsCodeClass) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCatNo() == null ? other.getCatNo() == null : this.getCatNo().equals(other.getCatNo()))
            && (this.getCatName() == null ? other.getCatName() == null : this.getCatName().equals(other.getCatName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCatNo() == null) ? 0 : getCatNo().hashCode());
        result = prime * result + ((getCatName() == null) ? 0 : getCatName().hashCode());
        return result;
    }
}