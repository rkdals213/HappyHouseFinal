package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.service.BoardService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	BoardService boardService;
	
	@ApiOperation(value = "모든 게시글의 정보를 반환한다.",response = Page.class)
	@GetMapping("/list/{board_idx}/{pageno}")
	public ResponseEntity<Map<String,Object>> selectAll(@PathVariable int board_idx, @PathVariable int pageno){
		System.out.println("selectAllpageno"+pageno);
		ResponseEntity<Map<String,Object>> entity=null;
		try {
			Page<Board> list = boardService.searchAll(board_idx,pageno);
			System.out.println(list);
			entity = handleSuccess(list);
			entity.getBody().put("totalPages",list.getPages());
		}
		catch(RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@ApiOperation(value = "검색결과를 반환한다.",response = Page.class)
	@GetMapping("/results/{board_idx}/{pageno}")
	public ResponseEntity<Map<String,Object>> selectAll(@PathVariable int board_idx,@PathVariable int pageno, @RequestParam("keyword") String keyword, 
			@RequestParam("selected") String selected){
		System.out.println("searchBy"+selected+":"+keyword);
		System.out.println("boardidx:"+board_idx+"pageno"+pageno);
		ResponseEntity<Map<String,Object>> entity=null;
		try {
			Page<Board> list=null;
			if(selected.equals("제목")) {
				 list = boardService.searchBySub(board_idx,pageno,keyword);
				 System.out.println("제목");
			}
			else {
				System.out.println("작성자");
				list = boardService.searchByWriter(board_idx,pageno,keyword);
			}
			System.out.println(list); 	
			entity = handleSuccess(list);
			entity.getBody().put("totalPages",list.getPages());
		}
		catch(RuntimeException e) {
			System.out.println("에러");
			entity = handleException(e);
		}
		return entity;
	}
	
	@ApiOperation(value="게시글 번호로 조회", response = Board.class)
	@GetMapping("/{no}")
	public ResponseEntity<Map<String,Object>> selectByNo(@PathVariable int no){
		System.out.println("selectByNo");
		ResponseEntity<Map<String,Object>> entity=null;
		try {
			Board result = boardService.selectByNo(no);
			System.out.println(result);
			entity = handleSuccess(result);
			
		}catch(RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	
	@ApiOperation(value="게시글 등록",response = String.class)
	@PostMapping("/write")
	public ResponseEntity<String> writeBoard(@RequestBody Board board) {
		logger.debug("writeBoard - 호출");
		if (boardService.insert(board)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{no}")
	public ResponseEntity<String> updateBoard(@RequestBody Board board) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + board);
		
		if (boardService.update(board)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{no}")
	public ResponseEntity<String> deleteBoard(@PathVariable int no) {
		logger.debug("deleteBoard - 호출");
		if (boardService.delete(no)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", true);
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> handleException(Exception e) {
//		logger.error("예외 발생", e);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", false);
		resultMap.put("data", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
