package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.dto.KakaoUserInfo;
import com.ssafy.happyhouse.service.KakaoUserService;

@Controller
@RequestMapping("/userinfo")
//@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class UserController {
	
	httpConnection conn = httpConnection.getInstance();
	
	@Autowired
	KakaoUserService kService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
//	http://localhost:9999/userinfo/kakaoLogin
	@RequestMapping(value = "/kakaoLogin", method = RequestMethod.GET)
	public String kakao() throws IOException {
		
		StringBuffer loginUrl = new StringBuffer();
		loginUrl.append("https://kauth.kakao.com/oauth/authorize?client_id=");
		loginUrl.append("a73b882aa1d4d394b9f15c7ed765d269"); //카카오 앱에 있는 REST KEY
		loginUrl.append("&redirect_uri=");
		loginUrl.append("http://localhost:9999/happyhouse/userinfo/redirect"); //카카오 앱에 등록한 redirect URL
		loginUrl.append("&response_type=code");
		
		return "redirect:"+loginUrl.toString();
	}	
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect(@RequestParam String code, HttpSession session , HttpServletResponse res) throws IOException {		
		//code
		//사용자가 취소 누르면 error 파라메터를 받음 
		// 그때 여기서 구분해야할듯
		Map<String, String> map = new HashMap<String, String>();
		map.put("grant_type", "=authorization_code");
		map.put("client_id", "=a73b882aa1d4d394b9f15c7ed765d269"); //카카오 앱에 있는 REST KEY
		map.put("redirect_uri", "=http://localhost:9999/happyhouse/userinfo/redirect"); //카카오 앱에 등록한 redirect URL
		map.put("code", "="+code);
		
		String out = conn.HttpPostConnection("https://kauth.kakao.com/oauth/token", map).toString();
		ObjectMapper mapper = new ObjectMapper();
		String access_token = mapper.readTree(out).findValue("access_token").toString();
		System.out.println(access_token);
		map.clear();
		map.put("Authorization", "Bearer "+ access_token);
		out = conn.HttpPostConnection("https://kapi.kakao.com/v2/user/me", map).toString();
		String id = mapper.readTree(out).findValue("id").toString();
		String email = mapper.readTree(out).findValue("kakao_account").findValue("email").toString();
		String nickname = mapper.readTree(out).findValue("properties").findValue("nickname").toString();
		System.out.println("id : "+  id);
		System.out.println("email : "+  email);
		System.out.println("nickname : "+  nickname);
		
		access_token = access_token.substring(1, access_token.length()-1);
		email = email.substring(1, email.length()-1);
		nickname = nickname.substring(1, nickname.length()-1);
		
		KakaoUserInfo list = new KakaoUserInfo(access_token, id, email, nickname);
		kService.makeKUser(list);
		System.out.println(list);
		System.out.println(kService.findid(id));
		if(kService.findid(id) != null) return "redirect:http://localhost:8080";
		else return "redirect:http://localhost:8080/userinfo/join";
	}
	
}