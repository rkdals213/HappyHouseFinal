package com.ssafy.happyhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.KakaoUserRepo;
import com.ssafy.happyhouse.dto.KakaoUserInfo;
import com.ssafy.happyhouse.dto.UserInfo;

@Service
public class KakaoUserServiceImpl implements KakaoUserService{
	@Autowired
	KakaoUserRepo kRepo;	
	
	static KakaoUserInfo userinfo;

	@Override
	public int makeKUser(KakaoUserInfo userinfo) {
		this.userinfo = userinfo;
		return 1;
	}

	@Override
	public KakaoUserInfo getKUser() {
		return userinfo;
	}

	@Override
	public int deleteKUser() {
		this.userinfo = null;
		return 1;
	}

	@Override
	public UserInfo findid(String id) {
		return kRepo.findid(id);
	}
}
