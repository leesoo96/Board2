package com.koreait.board2;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board2.common.Utils;
import com.koreait.board2.db.BoardDAO;
import com.koreait.board2.model.BoardVO;

@WebServlet("/bDetail")
public class BoardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int typ = Utils.getIntParam(request, "typ");
		int i_board = Utils.getIntParam(request, "i_board");
		int hits = Utils.getIntParam(request, "hits");
		
		if(typ == 0 || i_board == 0) {
			Utils.forwardErr(request, response);
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setTyp(typ);
		param.setI_board(i_board);

//		아이피가 같으면 조회수증가 X -------------------------------------------------------
		String ip = request.getRemoteAddr();
		String key = String.format("b_%d_%d", param.getTyp(), param.getI_board());
		
//		application -> 똑같은 주소값을 쓴다(공용)
		ServletContext application = request.getServletContext();
		String savedIp = (String) application.getAttribute(key); // 처음 들어오면 null값이 들어있다
		if(!ip.equals(savedIp)) { // 아이피가 다르면 조회수 증가 O
			application.setAttribute(key, ip);
			BoardDAO.addHits(param);
		}
		
		Enumeration<String> strArr = application.getAttributeNames();
		while (strArr.hasMoreElements()) {
			String str = (String) strArr.nextElement();
			if(str.startsWith("b_")) {
				System.out.println("key = " + str);
				System.out.println("value = " + application.getAttribute(str));
			}
		}
//     ---------------------------------------------------------------------------
		
		BoardDAO.readBoard(param);
				
		request.setAttribute("contents", param);
	
		Utils.forward("글읽기", "/bDetail", request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
//		글 삭제
		int typ = Utils.getIntParam(request, "typ");
		int i_board = Utils.getIntParam(request, "i_board");
		
		BoardVO param = new BoardVO();
		param.setTyp(typ);
		param.setI_board(i_board);
		
		int result = BoardDAO.delBoard(param); 
		if(result == 0) {
			request.setAttribute("msg", "삭제할 수 없습니다.");
			doGet(request, response);
			return;
		}
		
		response.sendRedirect("/bList?typ=" + typ);
	}
}