package com.ssafy.happyhouse.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {

	int no;
	String subject;
	String text;
	String writerNickname;
	int boardInfoIdx;
	Date date;
}
