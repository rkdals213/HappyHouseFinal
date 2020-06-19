package com.ssafy.happyhouse.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
	private int no;
	private String toNickname;
	private String subject;
	private String text;
	private String writerNickname;
	private Date date;
	private int readcheck;
}
