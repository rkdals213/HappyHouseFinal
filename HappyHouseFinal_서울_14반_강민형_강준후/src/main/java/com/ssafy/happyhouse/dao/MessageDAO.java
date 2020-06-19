package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Message;


@Mapper
public interface MessageDAO {
	List<Message> selectAll(String nickname); 
	int sendMessage(Message message);
	int deleteMessage(int no);
	int readMessage(int no);
	int selectCount(String nickname);
}