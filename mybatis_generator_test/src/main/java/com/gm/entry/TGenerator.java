package com.gm.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: lin
 * @Date: 2019/04/22
 * @Table: t_generator
 * @Description: 测试mybatis-generator的自动生成类，xml，接口的表
 * @Warn 该类不能删除 
 */
public class TGenerator implements Serializable {
    /**
     * @Column: id
     * @Description: 主键id
     */
    private Integer id;

    /**
     * @Column: message
     * @Description: 描述信息
     */
    private String message;

    /**
     * @Column: create_date
     * @Description: 该对象的修改时间
     */
    private Date createDate;

    /**
     * @Column: update_date
     * @Description: 该对象的修改时间
     */
    private Date updateDate;

    /**
     * @Table: t_generator
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Table: t_generator
	 * @Column: id
	 * @Description: 主键id
	 * @Return:  java.lang.Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * @Table: t_generator
	 * @Column: id
	 * @Param id java.lang.Integer
	 * @ Description: 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @Table: t_generator
	 * @Column: message
	 * @Description: 描述信息
	 * @Return:  java.lang.String
     */
    public String getMessage() {
        return message;
    }

    /**
     * @Table: t_generator
	 * @Column: message
	 * @Param message java.lang.String
	 * @ Description: 描述信息
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * @Table: t_generator
	 * @Column: create_date
	 * @Description: 该对象的修改时间
	 * @Return:  java.util.Date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @Table: t_generator
	 * @Column: create_date
	 * @Param createDate java.util.Date
	 * @ Description: 该对象的修改时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @Table: t_generator
	 * @Column: update_date
	 * @Description: 该对象的修改时间
	 * @Return:  java.util.Date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @Table: t_generator
	 * @Column: update_date
	 * @Param updateDate java.util.Date
	 * @ Description: 该对象的修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @Date: 2019/04/22
     * @Description: 
     * @Return: java.lang.String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", message=").append(message);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}