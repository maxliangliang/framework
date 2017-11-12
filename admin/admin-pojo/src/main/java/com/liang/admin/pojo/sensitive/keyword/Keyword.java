package com.liang.admin.pojo.sensitive.keyword;

import com.liang.admin.pojo.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Keyword extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8517625408958808098L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_keyword.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_keyword.text
     *
     * @mbg.generated
     */
    private String text;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_keyword.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_keyword.owner
     *
     * @mbg.generated
     */
    private String owner;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_keyword.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sensitive_keyword.is_del
     *
     * @mbg.generated
     */
    private String isDel;


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_keyword.id
     *
     * @return the value of sensitive_keyword.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_keyword.id
     *
     * @param id the value for sensitive_keyword.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_keyword.text
     *
     * @return the value of sensitive_keyword.text
     *
     * @mbg.generated
     */
    public String getText() {
        return text;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_keyword.text
     *
     * @param text the value for sensitive_keyword.text
     *
     * @mbg.generated
     */
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_keyword.description
     *
     * @return the value of sensitive_keyword.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_keyword.description
     *
     * @param description the value for sensitive_keyword.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_keyword.owner
     *
     * @return the value of sensitive_keyword.owner
     *
     * @mbg.generated
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_keyword.owner
     *
     * @param owner the value for sensitive_keyword.owner
     *
     * @mbg.generated
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_keyword.create_time
     *
     * @return the value of sensitive_keyword.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_keyword.create_time
     *
     * @param createTime the value for sensitive_keyword.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sensitive_keyword.is_del
     *
     * @return the value of sensitive_keyword.is_del
     *
     * @mbg.generated
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sensitive_keyword.is_del
     *
     * @param isDel the value for sensitive_keyword.is_del
     *
     * @mbg.generated
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensitive_keyword
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
        Keyword other = (Keyword) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getText() == null ? other.getText() == null : this.getText().equals(other.getText()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getOwner() == null ? other.getOwner() == null : this.getOwner().equals(other.getOwner()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDel() == null ? other.getIsDel() == null : this.getIsDel().equals(other.getIsDel()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensitive_keyword
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getText() == null) ? 0 : getText().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getOwner() == null) ? 0 : getOwner().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensitive_keyword
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
        sb.append(", text=").append(text);
        sb.append(", description=").append(description);
        sb.append(", owner=").append(owner);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDel=").append(isDel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}