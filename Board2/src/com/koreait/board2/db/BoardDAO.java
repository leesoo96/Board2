package com.koreait.board2.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board2.model.BoardVO;

public class BoardDAO {
	
	public static List<BoardVO> selBoard(final BoardVO param){
		List<BoardVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT i_board, title, ctnt, r_dt "
					 + " FROM t_board_? ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int i_board = rs.getInt("i_board");
				String title = rs.getString("title");
				String ctnt = rs.getString("ctnt");
				String r_dt = rs.getString("r_dt");
				
				BoardVO vo = new BoardVO();
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setCtnt(ctnt);
				vo.setR_dt(r_dt);
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}
}
