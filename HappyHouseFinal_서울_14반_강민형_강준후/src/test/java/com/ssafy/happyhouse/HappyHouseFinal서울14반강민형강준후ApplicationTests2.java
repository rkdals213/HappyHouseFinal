package com.ssafy.happyhouse;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.happyhouse.dto.Message;
import com.ssafy.happyhouse.service.MessageService;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@SpringBootTest
class HappyHouseFinal서울14반강민형강준후ApplicationTests2 {

	@Autowired
	MessageService mService;
	
	@Test 
	void test1() {
		System.out.println(mService.selectAll("workiee"));		
	}
	@Test 
	void test2() {
		mService.sendMessage(new Message(0, "workiee2", "test3", "test3", "ssafy", new Date(), 0));	
	}
	@Test
	void test3() {
		mService.readMessage(1);
	}
	@Test
	void test4() {
		mService.selectCount("강민형");
	}
	
}
