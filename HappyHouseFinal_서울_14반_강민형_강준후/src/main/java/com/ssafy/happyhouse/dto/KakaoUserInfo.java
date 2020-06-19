package com.ssafy.happyhouse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoUserInfo {
	private String token;
	private String id;
	private String email;
	private String nickname;
}
