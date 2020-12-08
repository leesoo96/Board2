package com.koreait.board2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.koreait.board2.db.BoardDAO;
import com.koreait.board2.model.BoardVO;

public class BoardService {

	public static List<BoardVO> selBoardList(BoardVO param){
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
}
