package com.ssafy.happyhouse.dao;

import java.util.List;

import com.ssafy.happyhouse.dto.QnA;

public interface QnADAO {
	List<QnA> selectQnA();
	List<QnA> selectQnAByQnATitle(String qnaTitle);
	QnA selectQnAByQnANo(int qnaNo);
	int deleteQnA(int qnaNo);
	int updateQnA(QnA qna);
	int createQnA(QnA qna);	
	int writeReply(QnA qna);
}
