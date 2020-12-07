package com.koreait.board2.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board2.model.BoardVO;

public class BoardDAO {
	
//	서브 페이지의 글 목록 확인
	public static List<BoardVO> selBoard(final BoardVO param){
		List<BoardVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM t_board_? ORDER BY i_board DESC ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				
				vo.setI_board(rs.getInt("i_board"));
				vo.setTitle(rs.getString("title"));
				vo.setR_dt(rs.getString("r_dt"));
				
				list.add(vo);
					
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
//	글 읽기
	public static void readBoard(final BoardVO param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM t_board_? WHERE i_board = ? ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			pstmt.setInt(2, param.getI_board());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				param.setI_board(rs.getInt("i_board"));
				param.setTitle(rs.getString("title"));
				param.setCtnt(rs.getString("ctnt"));
				param.setR_dt(rs.getString("r_dt"));
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
	}
	
//	글 쓰기
	public static int insBoard(BoardVO param) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO t_board_? (title, ctnt) "
					 + " VALUES (?, ?) "; 
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			pstmt.setString(2, param.getTitle());
			pstmt.setString(3, param.getCtnt());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt);
		}
		
		return result;
	}
	
//	글 수정
	
//	글 삭제
	public static int delBoard(final BoardVO param) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = " DELETE FROM t_board_? WHERE i_board = ? ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			pstmt.setInt(2, param.getI_board());
			
			result = pstmt.executeUpdate();	
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt);
		}
		
		return result;
	}
}
