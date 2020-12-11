package com.koreait.board2;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board2.common.Utils;
import com.koreait.board2.db.BoardDAO;
import com.koreait.board2.db.SQLInterUpdate;
import com.koreait.board2.model.BoardCmtVO;
import com.koreait.board2.model.BoardVO;

@WebServlet("/cmt")
public class BoardCmtSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		댓글 입력 기능 처리
		String ctnt = request.getParameter("cmt_ctnt");
		int i_board = Utils.getIntParam(request, "i_board");
		int typ = Utils.getIntParam(request, "typ");
		
		BoardCmtVO cmtVo = new BoardCmtVO();
		cmtVo.setTyp(typ);
		cmtVo.setI_board(i_board);
		cmtVo.setCtnt(ctnt);
		
		BoardService.cmtInsert(cmtVo);
		
		response.sendRedirect("/bDetail?typ=" + typ + "&i_board=" + cmtVo.getI_board());
	}

}
