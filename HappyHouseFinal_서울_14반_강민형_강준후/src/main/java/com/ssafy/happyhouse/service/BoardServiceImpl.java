package com.ssafy.happyhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.happyhouse.dao.BoardDAO;
import com.ssafy.happyhouse.dto.Board;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDAO boardDao;
	
	@Override
	public Page<Board> searchAll(int boardInfoIdx, int pageNo) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, 10);
		return boardDao.selectAll(boardInfoIdx);
	}

	@Override
	public Board selectByNo(int no) {
		// TODO Auto-generated method stub
		return boardDao.selectByNo(no);
	}

	@Override
	public int insert(Board board) {
		// TODO Auto-generated method stub
		return boardDao.insert(board);
	}

	@Override
	public int update(Board board) {
		// TODO Auto-generated method stub
		return boardDao.update(board);
	}

	@Override
	public int delete(int no) {
		// TODO Auto-generated method stub
		return boardDao.delete(no);
	}

	@Override
	public Page<Board> searchBySub(int boardInfoIdx, int pageNo, String subject) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, 10);
		return boardDao.selectBySub(boardInfoIdx,subject);
	}

	@Override
	public Page<Board> searchByWriter(int boardInfoIdx,int pageNo, String writer) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, 10);
		return boardDao.selectByWriter(boardInfoIdx,writer);
	}

}
