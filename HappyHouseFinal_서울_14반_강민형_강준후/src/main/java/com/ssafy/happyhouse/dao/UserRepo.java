package com.ssafy.happyhouse.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.UserInfo;
@Mapper
public interface UserRepo {
	int join(UserInfo userinfo);
	UserInfo login(UserInfo userinfo);
	UserInfo getdata(UserInfo userinfo);
	int update(UserInfo userinfo);
	int drop(UserInfo userinfo);
}
