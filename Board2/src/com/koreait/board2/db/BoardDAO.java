package com.koreait.board2.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board2.model.BoardVO;

public class BoardDAO {
//	페이징
	public static int selPageCount(final BoardVO param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT ceil(COUNT(i_board) / ?) "
					 + " FROM t_board_? ";
//					데이터의 총 개수 / 리스트에 보여질 게시물의 최대 개수
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getRowCntPerPage());
			pstmt.setInt(2, param.getTyp());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1); // 가장 첫번째 결과값을 가지고 온다
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return 0;
	}
	
//	서브 페이지의 글 목록 확인
	public static List<BoardVO> selBoard(final BoardVO param){
		List<BoardVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT i_board, title, hits, r_dt "
					 + " FROM t_board_? ORDER BY i_board DESC "
					 + " LIMIT ?, ? ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			pstmt.setInt(2, param.getS_Index()); // 0
			pstmt.setInt(3, param.getRowCntPerPage()); // 가져올 데이터 개수 
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				
				vo.setI_board(rs.getInt("i_board"));
				vo.setTitle(rs.getString("title"));
				vo.setR_dt(rs.getString("r_dt"));
				vo.setHits(rs.getInt("hits"));
				
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
		String sql = " SELECT i_board, title, ctnt, r_dt, hits "
					 + " FROM t_board_? WHERE i_board = ? ";
		
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
				param.setHits(rs.getInt("hits"));
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
		ResultSet rs = null;
		String sql = " INSERT INTO t_board_? (title, ctnt) "
					 + " VALUES (?, ?) "; 
		
		try {
			conn = DBUtils.getConn();          
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, param.getTyp());
			pstmt.setString(2, param.getTitle());
			pstmt.setString(3, param.getCtnt());
			result =  pstmt.executeUpdate();
			
//			 		   PK값을 가져온다
			rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				int i_board = rs.getInt(1); // 1번쩨 컬럼을 얻어와서 
				System.out.println("ins i_board = " + i_board);
				param.setI_board(i_board);  // i_board의 값을 1번으로 바꾼다(원래 0번)
			}
			
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
//	글 수정
	public static int upBoard(BoardVO param) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = " UPDATE t_board_? SET title = ?, ctnt = ? WHERE i_board = ? ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			pstmt.setString(2, param.getTitle());
			pstmt.setString(3, param.getCtnt());
			pstmt.setInt(4, param.getI_board());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt);
		}
		
		return result;
	}
//	조회수 증가
	public static void addHits(BoardVO param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = " UPDATE t_board_? SET hits = hits + 1 "
					 + " WHERE i_board = ? ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			pstmt.setInt(2, param.getI_board());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt);
		}
	}
	
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
