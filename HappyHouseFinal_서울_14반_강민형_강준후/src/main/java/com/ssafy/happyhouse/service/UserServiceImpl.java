package com.ssafy.happyhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.UserRepo;
import com.ssafy.happyhouse.dto.UserInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo uRepo;
	
	@Override
	public int join(UserInfo userinfo) {
		return uRepo.join(userinfo);
	}

	@Override
	public UserInfo login(UserInfo userinfo) {
		return uRepo.login(userinfo);
	}

	@Override
	public UserInfo getdata(UserInfo userinfo) {
		return uRepo.getdata(userinfo);
	}

	@Override
	public int update(UserInfo userinfo) {
		return uRepo.update(userinfo);
	}

	@Override
	public int drop(UserInfo userinfo) {
		return uRepo.drop(userinfo);
	}
	
	
}
