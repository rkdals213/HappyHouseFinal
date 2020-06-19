package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.KakaoUserInfo;
import com.ssafy.happyhouse.dto.UserInfo;
import com.ssafy.happyhouse.service.KakaoUserService;
import com.ssafy.happyhouse.service.UserService;

@RestController // rest 전용
@RequestMapping("/userinfoR")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class UserRestController {

	static Logger logger = LoggerFactory.getLogger( UserRestController.class);

	httpConnection conn = httpConnection.getInstance();
	
	@Autowired
	KakaoUserService kService;
	
	@Autowired
	UserService uService;
	
	@PostMapping("/normalLogin")
	public ResponseEntity<Map<String, Object>> login(HttpSession session, @RequestBody UserInfo userinfo) throws JsonProcessingException, IOException {
		System.out.println("login");
		System.out.println(userinfo.toString());
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			UserInfo info = uService.login(userinfo);
			System.out.println(info);
			entity = handleSuccess(info);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	@GetMapping("/getkakao")
	public ResponseEntity<Map<String, Object>> getkakao() throws JsonProcessingException, IOException {
		System.out.println("getkakao");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			KakaoUserInfo list = kService.getKUser();
			entity = handleSuccess(list);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	
	
	@PostMapping("/logout")
	public ResponseEntity<Map<String, Object>> logout(HttpSession session, @RequestBody KakaoUserInfo info) throws JsonProcessingException, IOException {
		System.out.println("logout");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			String access_token = info.getToken();
			System.out.println(access_token);
			Map<String, String> map = new HashMap<String, String>();
			map.put("Authorization", "Bearer "+ access_token);
			
			String result = conn.HttpPostConnection("https://kapi.kakao.com/v1/user/unlink", map).toString();
			System.out.println("result : "+result);
			kService.deleteKUser();
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@PostMapping("/join")
	public ResponseEntity<Map<String, Object>> join(HttpSession session, @RequestBody UserInfo userinfo) throws JsonProcessingException, IOException {
		System.out.println("logout");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			System.out.println(userinfo);
			uService.join(userinfo);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@PostMapping("/getdata")
	public ResponseEntity<Map<String, Object>> getdata(HttpSession session, @RequestBody UserInfo userinfo) throws JsonProcessingException, IOException {
		System.out.println("getdata");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			System.out.println(userinfo);
			UserInfo info = uService.getdata(userinfo);
			System.out.println(info);
			entity = handleSuccess(info);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@PostMapping("/update")
	public ResponseEntity<Map<String, Object>> update(HttpSession session, @RequestBody UserInfo userinfo) throws JsonProcessingException, IOException {
		System.out.println("update");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			int result = uService.update(userinfo);
			System.out.println(result);
			entity = handleSuccess(result);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@PostMapping("/dropId")
	public ResponseEntity<Map<String, Object>> dropId(HttpSession session, @RequestBody UserInfo userinfo) throws JsonProcessingException, IOException {
		System.out.println("drop");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			int result = uService.drop(userinfo);
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
