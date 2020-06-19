package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.KakaoUserInfo;
import com.ssafy.happyhouse.dto.UserInfo;

public interface KakaoUserService {
	int makeKUser(KakaoUserInfo userinfo);
	
	KakaoUserInfo getKUser();
	
	int deleteKUser();
	
	UserInfo findid(String id);
}
