package com.gy.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "my_friends")
public class MyFriends {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_friends.id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    @Id
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_friends.my_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    @Column(name = "my_user_id")
    private String myUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_friends.my_friend_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */

    @Column(name = "my_friend_user_id")
    private String myFriendUserId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_friends.id
     *
     * @return the value of my_friends.id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_friends.id
     *
     * @param id the value for my_friends.id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_friends.my_user_id
     *
     * @return the value of my_friends.my_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getMyUserId() {
        return myUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_friends.my_user_id
     *
     * @param myUserId the value for my_friends.my_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId == null ? null : myUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_friends.my_friend_user_id
     *
     * @return the value of my_friends.my_friend_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public String getMyFriendUserId() {
        return myFriendUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_friends.my_friend_user_id
     *
     * @param myFriendUserId the value for my_friends.my_friend_user_id
     *
     * @mbg.generated Fri Nov 13 23:36:11 CST 2020
     */
    public void setMyFriendUserId(String myFriendUserId) {
        this.myFriendUserId = myFriendUserId == null ? null : myFriendUserId.trim();
    }
}