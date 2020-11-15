package com.gy.service;

import com.gy.pojo.User;
import com.gy.pojo.vo.FriendRequestVO;
import com.gy.pojo.vo.MyFriendsVO;
import java.util.List;


public interface UserService {
/*
    查询用户名是否存在，来判断是 登陆：注册
 */
    public boolean queryUsernameIsExist(String userName);

/*
    查询用户名和密码是否正确，进行登陆
 */
    public User queryUserForLogin(String username, String pwd);

/*
    用户注册
 */
    public User saveUser(User user);

/*
    修改用户信息
 */
    public User updateUserInfo(User user);


    /**
     * @Description: 搜索朋友的前置条件
     */
    public Integer preconditionSearchFriends(String myUserId, String friendUsername);

    /**
     * @Description: 根据用户名查询用户对象
     */
    public User queryUserInfoByUsername(String username);

    /**
     * @Description: 添加好友请求记录，保存到数据库
     */
    public void sendFriendRequest(String myUserId, String friendUsername);

    /**
     * @Description: 查询好友请求
     */
    public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);

    /**
     * @Description: 删除好友请求记录
     */
    public void deleteFriendRequest(String sendUserId, String acceptUserId);

    /**
     * @Description: 通过好友请求
     * 				1. 保存好友
     * 				2. 逆向保存好友
     * 				3. 删除好友请求记录
     */
    public void passFriendRequest(String sendUserId, String acceptUserId);

    /**
     * @Description: 查询好友列表
     */
    public List<MyFriendsVO> queryMyFriends(String userId);

    /**
     * @Description: 保存聊天消息到数据库
     */
    public String saveMsg(com.gy.netty.ChatMsg chatMsg);

    /**
     * @Description: 批量签收消息
     */
    public void updateMsgSigned(List<String> msgIdList);

    /**
     * @Description: 获取未签收消息列表
     */
    public List<com.gy.pojo.ChatMsg> getUnReadMsgList(String acceptUserId);
}
