package com.gy.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "friend_request")
public class FriendRequest {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_request.id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    @Id
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_request.send_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    @Column(name = "send_user_id")
    private String sendUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_request.accept_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    @Column(name = "accept_user_id")
    private String acceptUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_request.request_date_time
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    @Column(name = "request_date_time")
    private Date requestDateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_request.id
     *
     * @return the value of friend_request.id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_request.id
     *
     * @param id the value for friend_request.id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_request.send_user_id
     *
     * @return the value of friend_request.send_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getSendUserId() {
        return sendUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_request.send_user_id
     *
     * @param sendUserId the value for friend_request.send_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId == null ? null : sendUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_request.accept_user_id
     *
     * @return the value of friend_request.accept_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getAcceptUserId() {
        return acceptUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_request.accept_user_id
     *
     * @param acceptUserId the value for friend_request.accept_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId == null ? null : acceptUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_request.request_date_time
     *
     * @return the value of friend_request.request_date_time
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public Date getRequestDateTime() {
        return requestDateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_request.request_date_time
     *
     * @param requestDateTime the value for friend_request.request_date_time
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }
}