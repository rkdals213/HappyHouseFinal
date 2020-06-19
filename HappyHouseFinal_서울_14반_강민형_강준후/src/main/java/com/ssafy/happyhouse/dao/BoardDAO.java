package com.ssafy.happyhouse.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.dto.Board;

@Mapper
public interface BoardDAO {
	//카테고리 별로 게시판 정보 조회
	public Page<Board> selectAll(int boardInfoIdx);

	//게시글 번호로 조회
	public Board selectByNo(int no);
	
	//게시글 등록
	public int insert(Board board);
	
	//게시글 수정
	public int update(Board board);
	
	//게시글 삭제
	public int delete(int no);
	
	//제목 검색
	public Page<Board> selectBySub(@Param("boardInfoIdx") int boardInfoIdx,@Param("subject") String subject);
	
	//작성자로 검색
	public Page<Board> selectByWriter(@Param("boardInfoIdx") int boardInfoIdx,@Param("writerNickname") String writerNickname);
	
}
