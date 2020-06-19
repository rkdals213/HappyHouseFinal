package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.MessageDAO;
import com.ssafy.happyhouse.dto.Message;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageDAO mDao;
	
	@Override
	public List<Message> selectAll(String nickname) {
		return mDao.selectAll(nickname);
	}

	@Override
	public int sendMessage(Message message) {
		return mDao.sendMessage(message);
	}

	@Override
	public int deleteMessage(int no) {
		return mDao.deleteMessage(no);
	}

	@Override
	public int readMessage(int no) {
		return mDao.readMessage(no);
	}

	@Override
	public int selectCount(String nickname) {
		return mDao.selectCount(nickname);
	}

}
