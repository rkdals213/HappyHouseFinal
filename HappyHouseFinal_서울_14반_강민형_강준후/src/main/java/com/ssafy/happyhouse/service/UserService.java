package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.UserInfo;

public interface UserService {	
	int join(UserInfo userinfo);
	UserInfo login(UserInfo userinfo);
	UserInfo getdata(UserInfo userinfo);
	int update(UserInfo userinfo);
	int drop(UserInfo userinfo);
}
