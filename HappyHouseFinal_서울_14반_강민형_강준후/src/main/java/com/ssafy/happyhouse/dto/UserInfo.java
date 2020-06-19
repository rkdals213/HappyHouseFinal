package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
	private String id;
	private String pw;
	private String nickname;
	private String email;
	private String phone;
	private String address;
	private int admin;
}
