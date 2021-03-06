package com.liang.admin.pojo.sensitive.countlog;

import com.liang.admin.pojo.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class CountLog extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -1411068615484408805L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_count_log.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_count_log.text_id
     *
     * @mbg.generated
     */
    private String textId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_count_log.count
     *
     * @mbg.generated
     */
    private Integer count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_count_log.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_count_log.id
     *
     * @return the value of sensitive_count_log.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_count_log.id
     *
     * @param id the value for sensitive_count_log.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_count_log.text_id
     *
     * @return the value of sensitive_count_log.text_id
     *
     * @mbg.generated
     */
    public String getTextId() {
        return textId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_count_log.text_id
     *
     * @param textId the value for sensitive_count_log.text_id
     *
     * @mbg.generated
     */
    public void setTextId(String textId) {
        this.textId = textId == null ? null : textId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_count_log.count
     *
     * @return the value of sensitive_count_log.count
     *
     * @mbg.generated
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_count_log.count
     *
     * @param count the value for sensitive_count_log.count
     *
     * @mbg.generated
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_count_log.create_time
     *
     * @return the value of sensitive_count_log.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_count_log.create_time
     *
     * @param createTime the value for sensitive_count_log.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensitive_count_log
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
        CountLog other = (CountLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTextId() == null ? other.getTextId() == null : this.getTextId().equals(other.getTextId()))
            && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensitive_count_log
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTextId() == null) ? 0 : getTextId().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensitive_count_log
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
        sb.append(", textId=").append(textId);
        sb.append(", count=").append(count);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}