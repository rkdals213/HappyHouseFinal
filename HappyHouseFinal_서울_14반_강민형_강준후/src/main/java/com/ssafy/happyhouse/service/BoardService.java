package com.ssafy.happyhouse.service;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.dto.Board;

public interface BoardService {
	//카테고리 별로 게시판 정보 조회
	public Page<Board> searchAll(int boardInfoIdx, int pageNo);

	//게시글 번호로 조회
	public Board selectByNo(int no);
	
	//게시글 등록
	public int insert(Board board);
	
	//게시글 수정
	public int update(Board board);
	
	//게시글 삭제
	public int delete(int no);
	
	//제목 검색
	public Page<Board> searchBySub(int boardInfoIdx, int pageNo, String subject);
	
	//작성자로 검색
	public Page<Board> searchByWriter(int boardInfoIdx,int pageNo, String writer) ;

	

	
}
