package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.codehaus.jackson.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.Message;
import com.ssafy.happyhouse.service.MessageService;

import springfox.documentation.spring.web.json.Json;

@RestController // rest 전용
@RequestMapping("/message")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class MessageController {

	static Logger logger = LoggerFactory.getLogger( UserRestController.class);

	httpConnection conn = httpConnection.getInstance();
	
	@Autowired 
	MessageService mService;
	
	@PostMapping("/selectAll")
	public ResponseEntity<Map<String, Object>> selectAll(HttpSession session, @RequestBody Map<String, String> data) throws JsonProcessingException, IOException {
		System.out.println("selectAllMessage : " + data.get("nickname"));
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			List<Message> messages = mService.selectAll(data.get("nickname"));
			System.out.println(messages);
			entity = handleSuccess(messages);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@PostMapping("/selectCount")
	public ResponseEntity<Map<String, Object>> selectCount(HttpSession session, @RequestBody Map<String, String> data) throws JsonProcessingException, IOException {
		System.out.println("selectCount : " + data.get("nickname"));
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			int result = mService.selectCount(data.get("nickname"));
			System.out.println(result);
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@PostMapping("/sendMessage")
	public ResponseEntity<Map<String, Object>> sendMessage(HttpSession session, @RequestBody Message message) throws JsonProcessingException, IOException {
		System.out.println("sendMessage");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			int result = mService.sendMessage(message);
			System.out.println(result);
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@PostMapping("/deleteMessage")
	public ResponseEntity<Map<String, Object>> deleteMessage(HttpSession session, @RequestBody Map<String, Integer> data) throws JsonProcessingException, IOException {
		System.out.println("deleteMessage");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			int result = mService.deleteMessage(data.get("no"));
			System.out.println(result);
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@PostMapping("/readMessage")
	public ResponseEntity<Map<String, Object>> readMessage(HttpSession session, @RequestBody Map<String, Integer> data) throws JsonProcessingException, IOException {
		System.out.println("readMessage : " + data);
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			int result = mService.readMessage(data.get("no"));
			System.out.println(result);
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", true);
		resultMap.put("data", data);
		System.out.println(resultMap);
		System.out.println(data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> handleException(Exception e) {
		logger.error("예외 발생", e);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", false);
		resultMap.put("data", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
