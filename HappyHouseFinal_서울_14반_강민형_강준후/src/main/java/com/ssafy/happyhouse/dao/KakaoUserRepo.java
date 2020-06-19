package com.ssafy.happyhouse.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.UserInfo;

@Mapper
public interface KakaoUserRepo {
	
	UserInfo findid(String id);	

}
