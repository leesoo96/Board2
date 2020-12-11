package com.koreait.board2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.koreait.board2.db.BoardDAO;
import com.koreait.board2.db.SQLInterUpdate;
import com.koreait.board2.model.BoardCmtVO;
import com.koreait.board2.model.BoardVO;

public class BoardService {

	public static List<BoardVO> selBoardList(BoardVO param, int page){
		int s_idx = (page - 1) * param.getRowCntPerPage(); // 글 목록에 보일 게시물 개수
		param.setS_Index(s_idx);
		return BoardDAO.selBoard(param);
	}
	
	public static int regmod(BoardVO param) { // 먼저 호출된 후에 DAO가 호출된다
		if(param.getI_board() > 0) {
			String sql = " UPDATE t_board_? SET title = ?, ctnt = ? WHERE i_board = ? ";
			
			return BoardDAO.myExecuteUpdate(sql, new SQLInterUpdate() {
				
				@Override
				public void proc(PreparedStatement pstmt) throws SQLException {
					pstmt.setInt(1, param.getTyp());
					pstmt.setString(2, param.getTitle());
					pstmt.setString(3, param.getCtnt());
					pstmt.setInt(4, param.getI_board());
				}
			});
		}else {
//			등록
			return BoardDAO.insBoard(param);
		}
	}
	
	public static int selPageCount(BoardVO param) {
		return BoardDAO.selPageCount(param);
	}
	
/*	public static int delBoard(BoardVO param) {  -> 에러발생  해결하기
		String sql = " DELETE FROM t_board_? WHERE i_board = ? ";
		
		return BoardDAO.myExecuteUpdate(param, new SQLInterUpdate() {
			익명함수로 만들면 param에 접근 가능
			@Override  // 콜백함수
			public void proc(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, param.getTyp()); // 결국 같은 객체를 가리킨다
				pstmt.setInt(2, param.getI_board());
			}
		});
	} */
	
//	댓글 쓰기
	public static int cmtInsert(BoardCmtVO cmtVo) {
		String sql = " INSERT INTO t_board_cmt_? (i_board, ctnt) VALUES (?, ?) ";
		
		return BoardDAO.myExecuteUpdate(sql, new SQLInterUpdate() {
			
			@Override
			public void proc(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, cmtVo.getTyp());
				pstmt.setInt(2, cmtVo.getI_board());
				pstmt.setString(3, cmtVo.getCtnt());			
			}
		});
	}
	
//	댓글 읽기
	public static List<BoardCmtVO> readCmtList(BoardVO param){
		return BoardDAO.readCmtList(param);
	}
}


