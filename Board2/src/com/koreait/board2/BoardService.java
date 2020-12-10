package com.koreait.board2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.koreait.board2.db.BoardDAO;
import com.koreait.board2.model.BoardVO;

public class BoardService {

	public static List<BoardVO> selBoardList(BoardVO param, int page){
		int s_idx = (page - 1) * param.getRowCntPerPage(); // 글 목록에 보일 게시물 개수
		param.setS_Index(s_idx);
		return BoardDAO.selBoard(param);
	}
	
	public static int regmod(BoardVO param) {
		if(param.getI_board() > 0) {
			return BoardDAO.upBoard(param);
		}else {
//			등록
			return BoardDAO.insBoard(param);
		}
	}
	
	public static int selPageCount(BoardVO param) {
		return BoardDAO.selPageCount(param);
	}
}
