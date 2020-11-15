package com.gy.controller;

import com.gy.enums.OperatorFriendRequestTypeEnum;
import com.gy.enums.SearchFriendsStatusEnum;
import com.gy.pojo.ChatMsg;
import com.gy.pojo.User;
import com.gy.pojo.bo.UsersBO;
import com.gy.pojo.vo.MyFriendsVO;
import com.gy.pojo.vo.UsersVO;
import com.gy.service.UserService;
import com.gy.utils.ChatJSONResult;
import com.gy.utils.FastDFSClient;
import com.gy.utils.FileUtils;
import com.gy.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("u")
public class UserRegisterOrLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private FastDFSClient fastDFSClient;


/*
	登陆注册
 */
    @PostMapping("/registOrLogin")
    public ChatJSONResult registOrLogin(@RequestBody User user) throws Exception {

        //得到前端传来的数据，进行非空判断
        if (       StringUtils.isBlank(user.getUsername())
		|| StringUtils.isBlank(user.getPassword())){
	    	// 用户名或密码不能为空，为空返回提示信息
            	return ChatJSONResult.errorMsg("用户名或密码不能为空");
	}


        //判断用户名是否存在，存在则登陆，不存在则注册
	boolean isExist = userService.queryUsernameIsExist(user.getUsername());

	User userResult = null;
        if (isExist){
            //登陆
	     userResult = userService.queryUserForLogin(
	     		user.getUsername(),
		     	MD5Utils.getMD5Str(user.getPassword()));

	     //登陆失败
	     if (userResult == null){
		 System.out.println("用户或密码不正确");
	         return ChatJSONResult.errorMsg("用户或密码不正确");//用户名或密码不正确
	     }
	}else {
            //注册
	    System.out.println("注册");
	    user.setNickname(user.getUsername());
	    user.setFaceImage("");
	    user.setFaceImageBig("");
	    user.setPassword(MD5Utils.getMD5Str(user.getPassword()));//MD5加密

	    userResult = userService.saveUser(user);
	}
        //传给前端的数据
	UsersVO usersVO = new UsersVO();
	BeanUtils.copyProperties(userResult,usersVO);

	return ChatJSONResult.ok(usersVO);
    }

/*
	修改头像
 */
    @PostMapping("/uploadFaceBase64")
    public ChatJSONResult uploadFaceBase64(@RequestBody UsersBO usersBO) throws Exception {
        //获取前端传来的base64字符串，然后转换为文件对象再上传
	String base64Data = usersBO.getFaceData();
	String userFacePath = "/Users/mac/Desktop/face/"+usersBO.getUserId()+"userface64.png";
	FileUtils.base64ToFile(userFacePath,base64Data);

	//上传文件到FDFS
	MultipartFile multipartFile = FileUtils.fileToMultipart(userFacePath);
	String url = fastDFSClient.uploadBase64(multipartFile);
	System.out.println(url);

	//获取缩略图的url
	String temp = "_80x80.";
	String arr[] = url.split("\\.");
	String smallUrl = arr[0] + temp + arr[1];

	//更新用户头像
	User user = new User();
	user.setId(usersBO.getUserId());
	user.setFaceImage(smallUrl);
	user.setFaceImageBig(url);
	System.out.println("3");
	User result = userService.updateUserInfo(user);

	return ChatJSONResult.ok(result);
    }


/*
	修改昵称
 */
    @PostMapping("/setNickname")
    public ChatJSONResult setNickname(@RequestBody UsersBO userBO) throws Exception {

	User user = new User();
	user.setId(userBO.getUserId());
	user.setNickname(userBO.getNickname());

	User result = userService.updateUserInfo(user);

	return ChatJSONResult.ok(result);
    }


/*
      搜索好友接口, 根据账号做匹配查询而不是模糊查询
 */
    @PostMapping("/search")
    public ChatJSONResult searchUser(String myUserId, String friendUsername)
	    throws Exception {

	// 0. 判断 myUserId friendUsername 不能为空
	if (StringUtils.isBlank(myUserId)
		|| StringUtils.isBlank(friendUsername)) {
	    return ChatJSONResult.errorMsg("");
	}

	// 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
	// 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
	// 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
	Integer status = userService.preconditionSearchFriends(myUserId, friendUsername);
	if (status == SearchFriendsStatusEnum.SUCCESS.status) {
	    User user = userService.queryUserInfoByUsername(friendUsername);
	    UsersVO userVO = new UsersVO();
	    BeanUtils.copyProperties(user, userVO);
	    return ChatJSONResult.ok(userVO);
	} else {
	    String errorMsg = SearchFriendsStatusEnum.getMsgByKey(status);
	    return ChatJSONResult.errorMsg(errorMsg);
	}
    }


/*
     发送添加好友的请求
 */
    @PostMapping("/addFriendRequest")
    public ChatJSONResult addFriendRequest(String myUserId, String friendUsername)
	    throws Exception {

	// 0. 判断 myUserId friendUsername 不能为空
	if (StringUtils.isBlank(myUserId)
		|| StringUtils.isBlank(friendUsername)) {
	    return ChatJSONResult.errorMsg("");
	}

	// 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
	// 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
	// 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
	Integer status = userService.preconditionSearchFriends(myUserId, friendUsername);
	if (status == SearchFriendsStatusEnum.SUCCESS.status) {
	    userService.sendFriendRequest(myUserId, friendUsername);
	} else {
	    String errorMsg = SearchFriendsStatusEnum.getMsgByKey(status);
	    return ChatJSONResult.errorMsg(errorMsg);
	}

	return ChatJSONResult.ok();
    }



/*
     发送添加好友的请求
 */
    @PostMapping("/queryFriendRequests")
    public ChatJSONResult queryFriendRequests(String userId) {

	// 0. 判断不能为空
	if (StringUtils.isBlank(userId)) {
	    return ChatJSONResult.errorMsg("");
	}

	// 1. 查询用户接受到的朋友申请
	return ChatJSONResult.ok(userService.queryFriendRequestList(userId));
    }



/*
      接受方 通过或者忽略朋友请求
 */
    @PostMapping("/operFriendRequest")
    public ChatJSONResult operFriendRequest(String acceptUserId, String sendUserId,
					     Integer operType) {

	// 0. acceptUserId sendUserId operType 判断不能为空
	if (StringUtils.isBlank(acceptUserId)
		|| StringUtils.isBlank(sendUserId)
		|| operType == null) {
	    return ChatJSONResult.errorMsg("");
	}

	// 1. 如果operType 没有对应的枚举值，则直接抛出空错误信息
	if (StringUtils.isBlank(OperatorFriendRequestTypeEnum.getMsgByType(operType))) {
	    return ChatJSONResult.errorMsg("");
	}

	if (operType == OperatorFriendRequestTypeEnum.IGNORE.type) {
	    // 2. 判断如果忽略好友请求，则直接删除好友请求的数据库表记录
	    userService.deleteFriendRequest(sendUserId, acceptUserId);
	} else if (operType == OperatorFriendRequestTypeEnum.PASS.type) {
	    // 3. 判断如果是通过好友请求，则互相增加好友记录到数据库对应的表
	    //	  然后删除好友请求的数据库表记录
	    userService.passFriendRequest(sendUserId, acceptUserId);
	}

	// 4. 数据库查询好友列表
	List<MyFriendsVO> myFirends = userService.queryMyFriends(acceptUserId);

	return ChatJSONResult.ok(myFirends);
    }




/*
	 查询我的好友列表
 */
    @PostMapping("/myFriends")
    public ChatJSONResult myFriends(String userId) {
	// 0. userId 判断不能为空
	if (StringUtils.isBlank(userId)) {
	    return ChatJSONResult.errorMsg("");
	}

	// 1. 数据库查询好友列表
	List<MyFriendsVO> myFirends = userService.queryMyFriends(userId);

	return ChatJSONResult.ok(myFirends);
    }



    /**
     * @Description: 用户手机端获取未签收的消息列表
     */
    @PostMapping("/getUnReadMsgList")
    public ChatJSONResult getUnReadMsgList(String acceptUserId) {
	// 0. userId 判断不能为空
	if (StringUtils.isBlank(acceptUserId)) {
	    return ChatJSONResult.errorMsg("");
	}

	// 查询列表
	List<ChatMsg> unreadMsgList = userService.getUnReadMsgList(acceptUserId);

	return ChatJSONResult.ok(unreadMsgList);
    }
}
