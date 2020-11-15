package com.gy.service.serviceImpl;

import com.gy.enums.MsgActionEnum;
import com.gy.enums.MsgSignFlagEnum;
import com.gy.enums.SearchFriendsStatusEnum;
import com.gy.idworker.Sid;
import com.gy.mapper.*;
import com.gy.netty.DataContent;
import com.gy.netty.UserChannelRel;
import com.gy.pojo.FriendRequest;
import com.gy.pojo.MyFriends;
import com.gy.pojo.User;
import com.gy.pojo.vo.FriendRequestVO;
import com.gy.pojo.vo.MyFriendsVO;
import com.gy.service.UserService;
import com.gy.utils.FastDFSClient;
import com.gy.utils.FileUtils;
import com.gy.utils.JsonUtils;
import com.gy.utils.QRCodeUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Sid sid;

    @Autowired
    private QRCodeUtils qrCodeUtils;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Autowired
    private MyFriendsMapper friendsMapper;

    @Autowired
    private FriendRequestMapper friendRequestMapper;

    @Autowired
    private ChatMsgMapper chatMsgMapper;

    @Autowired
    private UsersMapperCustom usersMapperCustom;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String userName) {
	User user = new User();
	user.setUsername(userName);

	User result = userMapper.selectOne(user);

	return result != null;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserForLogin(String username, String pwd) {
	Example userExample = new Example(User.class);
	Example.Criteria criteria = userExample.createCriteria();

	//criteria条件查询
	criteria.andEqualTo("username",username);//（数据库字段名，前端传来的）
	criteria.andEqualTo("password",pwd);

	User user = userMapper.selectOneByExample(userExample);
	return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User saveUser(User user) {

     	String  userid =  sid.nextShort();

	//唯一二维码
     	String qrCodePath = "/Users/mac/Desktop/face/" +userid + "qrCode.png";
	qrCodeUtils.createQRCode(qrCodePath,"chat qrCode:"+user.getUsername());

	MultipartFile qrCodeFile = FileUtils.fileToMultipart(qrCodePath);

	String qrCodeUrl = "";
	try {
	    qrCodeUrl = fastDFSClient.uploadQRCode(qrCodeFile);
	}catch (IOException e){
	    e.printStackTrace();
	}
	user.setQrcode(qrCodeUrl);

        //唯一id
	user.setId(userid);
	userMapper.insert(user);

	return user;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User updateUserInfo(User user) {
	userMapper.updateByPrimaryKeySelective(user);
	return queryUserById(user.getId());
    }



    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer preconditionSearchFriends(String myUserId, String friendUsername) {

	User user = queryUserInfoByUsername(friendUsername);

	// 1. 搜索的用户如果不存在，返回[无此用户]
	if (user == null) {
	    return SearchFriendsStatusEnum.USER_NOT_EXIST.status;
	}

	// 2. 搜索账号是你自己，返回[不能添加自己]
	if (user.getId().equals(myUserId)) {
	    return SearchFriendsStatusEnum.NOT_YOURSELF.status;
	}

	// 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
	Example mfe = new Example(MyFriends.class);
	Example.Criteria mfc = mfe.createCriteria();
	mfc.andEqualTo("myUserId", myUserId);
	mfc.andEqualTo("myFriendUserId", user.getId());
	MyFriends myFriendsRel = friendsMapper.selectOneByExample(mfe);
	if (myFriendsRel != null) {
	    return SearchFriendsStatusEnum.ALREADY_FRIENDS.status;
	}

	return SearchFriendsStatusEnum.SUCCESS.status;
    }




    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserInfoByUsername(String username) {
	Example ue = new Example(User.class);
	Example.Criteria uc = ue.createCriteria();
	uc.andEqualTo("username", username);
	return userMapper.selectOneByExample(ue);
    }



    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void sendFriendRequest(String myUserId, String friendUsername) {

	// 根据用户名把朋友信息查询出来
	User friend = queryUserInfoByUsername(friendUsername);

	// 1. 查询发送好友请求记录表
	Example fre = new Example(FriendRequest.class);
	Example.Criteria frc = fre.createCriteria();
	frc.andEqualTo("sendUserId", myUserId);
	frc.andEqualTo("acceptUserId", friend.getId());
	FriendRequest friendRequest = friendRequestMapper.selectOneByExample(fre);
	if (friendRequest == null) {
	    // 2. 如果不是你的好友，并且好友记录没有添加，则新增好友请求记录
	    String requestId = sid.nextShort();

	    FriendRequest request = new FriendRequest();
	    request.setId(requestId);
	    request.setSendUserId(myUserId);
	    request.setAcceptUserId(friend.getId());
	    request.setRequestDateTime(new Date());
	    friendRequestMapper.insert(request);
	}
    }




    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<FriendRequestVO> queryFriendRequestList(String acceptUserId) {
	return usersMapperCustom.queryFriendRequestList(acceptUserId);
    }




    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteFriendRequest(String sendUserId, String acceptUserId) {
	Example fre = new Example(FriendRequest.class);
	Example.Criteria frc = fre.createCriteria();
	frc.andEqualTo("sendUserId", sendUserId);
	frc.andEqualTo("acceptUserId", acceptUserId);
	friendRequestMapper.deleteByExample(fre);
    }



    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void passFriendRequest(String sendUserId, String acceptUserId) {
	saveFriends(sendUserId, acceptUserId);
	saveFriends(acceptUserId, sendUserId);
	//删除通过的好友请求记录
	deleteFriendRequest(sendUserId, acceptUserId);

	Channel sendChannel = UserChannelRel.get(sendUserId);
	if (sendChannel != null) {
	    // 使用websocket主动推送消息到请求发起者，更新他的通讯录列表为最新
	    DataContent dataContent = new DataContent();
	    dataContent.setAction(MsgActionEnum.PULL_FRIEND.type);

	    sendChannel.writeAndFlush(
		    new TextWebSocketFrame(
			    JsonUtils.objectToJson(dataContent)));
	}
    }



    @Transactional(propagation = Propagation.REQUIRED)
    void saveFriends(String sendUserId, String acceptUserId) {
	MyFriends myFriends = new MyFriends();
	String recordId = sid.nextShort();
	myFriends.setId(recordId);
	myFriends.setMyFriendUserId(acceptUserId);
	myFriends.setMyUserId(sendUserId);
	friendsMapper.insert(myFriends);
    }



    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<MyFriendsVO> queryMyFriends(String userId) {
	List<MyFriendsVO> myFirends = usersMapperCustom.queryMyFriends(userId);
	return myFirends;
    }



    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String saveMsg(com.gy.netty.ChatMsg chatMsg) {

	com.gy.pojo.ChatMsg msgDB = new com.gy.pojo.ChatMsg();
	String msgId = sid.nextShort();
	msgDB.setId(msgId);
	msgDB.setAcceptUserId(chatMsg.getReceiverId());
	msgDB.setSendUserId(chatMsg.getSenderId());
	msgDB.setCreateTime(new Date());
	msgDB.setSignFlag(MsgSignFlagEnum.unsign.type);
	msgDB.setMsg(chatMsg.getMsg());

	chatMsgMapper.insert(msgDB);

	return msgId;
    }



    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateMsgSigned(List<String> msgIdList) {

	usersMapperCustom.batchUpdateMsgSigned(msgIdList);
    }




    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<com.gy.pojo.ChatMsg> getUnReadMsgList(String acceptUserId) {

	Example chatExample = new Example(com.gy.pojo.ChatMsg.class);
	Example.Criteria chatCriteria = chatExample.createCriteria();
	chatCriteria.andEqualTo("signFlag", 0);
	chatCriteria.andEqualTo("acceptUserId", acceptUserId);

	List<com.gy.pojo.ChatMsg> result = chatMsgMapper.selectByExample(chatExample);

	return result;
    }



    @Transactional(propagation = Propagation.SUPPORTS)
    User queryUserById(String id){

        return userMapper.selectByPrimaryKey(id);
    }
}
