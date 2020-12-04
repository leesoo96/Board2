package com.koreait.board2;

import java.util.List;

import com.koreait.board2.db.BoardDAO;
import com.koreait.board2.model.BoardVO;

public class BoardService {

	public static List<BoardVO> selBoardList(BoardVO param){
		return BoardDAO.selBoard(param);
	}
	
	public static int regmod(BoardVO param) {
		if(param.getI_board() > 0) {
//			TODO 수정
			return 0;
		}else {
//			등록
			return BoardDAO.insBoard(param);
		}
	}
}
