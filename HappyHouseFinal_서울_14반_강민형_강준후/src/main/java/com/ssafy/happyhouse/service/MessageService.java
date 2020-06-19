package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.Message;


public interface MessageService {
	
	List<Message> selectAll(String id);
	int sendMessage(Message message);
	int deleteMessage(int no);
	int readMessage(int no);
	int selectCount(String nickname);


}
