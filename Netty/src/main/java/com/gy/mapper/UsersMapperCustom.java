package com.gy.mapper;

import com.gy.pojo.User;
import com.gy.pojo.vo.FriendRequestVO;
import com.gy.pojo.vo.MyFriendsVO;
import com.gy.utils.MyMapper;

import java.util.List;



public interface UsersMapperCustom extends MyMapper<User> {
	
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);
	
	public List<MyFriendsVO> queryMyFriends(String userId);
	
	public void batchUpdateMsgSigned(List<String> msgIdList);
	
}