package com.gy.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "chat_msg")
public class ChatMsg {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chat_msg.id
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    @Id
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chat_msg.send_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    @Column(name = "send_user_id")
    private String sendUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chat_msg.accept_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    @Column(name = "accept_user_id")
    private String acceptUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chat_msg.msg
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    private String msg;


    @Column(name ="sign_flag")
    private Integer signFlag;

    public Integer getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(Integer signFlag) {
        this.signFlag = signFlag;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chat_msg.create_time
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chat_msg.id
     *
     * @return the value of chat_msg.id
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chat_msg.id
     *
     * @param id the value for chat_msg.id
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chat_msg.send_user_id
     *
     * @return the value of chat_msg.send_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    public String getSendUserId() {
        return sendUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chat_msg.send_user_id
     *
     * @param sendUserId the value for chat_msg.send_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId == null ? null : sendUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chat_msg.accept_user_id
     *
     * @return the value of chat_msg.accept_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    public String getAcceptUserId() {
        return acceptUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chat_msg.accept_user_id
     *
     * @param acceptUserId the value for chat_msg.accept_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId == null ? null : acceptUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chat_msg.msg
     *
     * @return the value of chat_msg.msg
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    public String getMsg() {
        return msg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chat_msg.msg
     *
     * @param msg the value for chat_msg.msg
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chat_msg.create_time
     *
     * @return the value of chat_msg.create_time
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chat_msg.create_time
     *
     * @param createTime the value for chat_msg.create_time
     *
     * @mbg.generated Fri Nov 13 23:36:10 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}