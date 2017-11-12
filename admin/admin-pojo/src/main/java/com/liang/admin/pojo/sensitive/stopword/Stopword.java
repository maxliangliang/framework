package com.liang.admin.pojo.sensitive.stopword;

import com.liang.admin.pojo.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Stopword extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3244361977671714993L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_stopword.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_stopword.stopword
     *
     * @mbg.generated
     */
    private String stopword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_stopword.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_stopword.owner
     *
     * @mbg.generated
     */
    private String owner;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_stopword.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_stopword.is_del
     *
     * @mbg.generated
     */
    private String isDel;


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_stopword.id
     *
     * @return the value of sensitive_stopword.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_stopword.id
     *
     * @param id the value for sensitive_stopword.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_stopword.stopword
     *
     * @return the value of sensitive_stopword.stopword
     *
     * @mbg.generated
     */
    public String getStopword() {
        return stopword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_stopword.stopword
     *
     * @param stopword the value for sensitive_stopword.stopword
     *
     * @mbg.generated
     */
    public void setStopword(String stopword) {
        this.stopword = stopword == null ? null : stopword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_stopword.description
     *
     * @return the value of sensitive_stopword.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_stopword.description
     *
     * @param description the value for sensitive_stopword.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_stopword.owner
     *
     * @return the value of sensitive_stopword.owner
     *
     * @mbg.generated
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_stopword.owner
     *
     * @param owner the value for sensitive_stopword.owner
     *
     * @mbg.generated
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_stopword.create_time
     *
     * @return the value of sensitive_stopword.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_stopword.create_time
     *
     * @param createTime the value for sensitive_stopword.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_stopword.is_del
     *
     * @return the value of sensitive_stopword.is_del
     *
     * @mbg.generated
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_stopword.is_del
     *
     * @param isDel the value for sensitive_stopword.is_del
     *
     * @mbg.generated
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensitive_stopword
     *
     * @mbg.generated
     */
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
        Stopword other = (Stopword) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStopword() == null ? other.getStopword() == null : this.getStopword().equals(other.getStopword()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getOwner() == null ? other.getOwner() == null : this.getOwner().equals(other.getOwner()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDel() == null ? other.getIsDel() == null : this.getIsDel().equals(other.getIsDel()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensitive_stopword
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStopword() == null) ? 0 : getStopword().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getOwner() == null) ? 0 : getOwner().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensitive_stopword
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stopword=").append(stopword);
        sb.append(", description=").append(description);
        sb.append(", owner=").append(owner);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDel=").append(isDel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}