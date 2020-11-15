package com.gy.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    @Id
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.username
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.face_image
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    @Column(name = "face_image")
    private String faceImage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.face_image_big
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    @Column(name = "face_image_big")
    private String faceImageBig;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.nickname
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    private String nickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.qrcode
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    private String qrcode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.cid
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    private String cid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.username
     *
     * @return the value of user.username
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.username
     *
     * @param username the value for user.username
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.face_image
     *
     * @return the value of user.face_image
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.face_image
     *
     * @param faceImage the value for user.face_image
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage == null ? null : faceImage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.face_image_big
     *
     * @return the value of user.face_image_big
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getFaceImageBig() {
        return faceImageBig;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.face_image_big
     *
     * @param faceImageBig the value for user.face_image_big
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setFaceImageBig(String faceImageBig) {
        this.faceImageBig = faceImageBig == null ? null : faceImageBig.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.nickname
     *
     * @return the value of user.nickname
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.nickname
     *
     * @param nickname the value for user.nickname
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.qrcode
     *
     * @return the value of user.qrcode
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getQrcode() {
        return qrcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.qrcode
     *
     * @param qrcode the value for user.qrcode
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.cid
     *
     * @return the value of user.cid
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.cid
     *
     * @param cid the value for user.cid
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }
}