package kpu.club.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kpu.club.domain.BoardVO;
import kpu.club.persistence.BoardDAO;

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text; charset=UTF8");

		String cmd = "";
		cmd = request.getParameter("key");
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();

		System.out.println(cmd);

		if (cmd.equals("board")) {
			List<BoardVO> list = dao.getBoardList();
			request.setAttribute("boardList", list);

			RequestDispatcher view = request.getRequestDispatcher("bbs.jsp");
			view.forward(request, response);
		} else if (cmd.equals("view")) {
			int id = Integer.parseInt(request.getParameter("id"));
			vo = dao.view(id);

			request.setAttribute("bbs", vo);

			RequestDispatcher view = request.getRequestDispatcher("bbs_view.jsp");
			view.forward(request, response);
		} else if (cmd.equals("FAQ")) {
			response.sendRedirect("FAQ.jsp");
		} else if (cmd.equals("read")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String message = "";
			
			BoardVO board = dao.read(id);
			
			request.setAttribute("bbs", board);
			request.setAttribute("meesage", message);
			RequestDispatcher view = request.getRequestDispatcher("write_modified.jsp");
			view.forward(request, response);

		} else if (cmd.equals("update")) {

		} else if (cmd.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String message = "";
			if (dao.delete(id))
				message = "삭제가 완료되었습니다.";
			else
				message = "삭제가 실패했습니다.";

			request.setAttribute("meesage", message);
			RequestDispatcher view = request.getRequestDispatcher("bbs_view_result.jsp");
			view.forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text; charset=UTF8");

		String cmd = "";
		cmd = request.getParameter("key");
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();

		System.out.println(cmd);

		if (cmd.equals("board")) {
			List<BoardVO> list = dao.getBoardList();
			request.setAttribute("boardList", list);

			RequestDispatcher view = request.getRequestDispatcher("bbs.jsp");
			view.forward(request, response);
		} else if (cmd.equals("left_board")) {

		} else if (cmd.equals("right_board")) {

		} else if (cmd.equals("write")) {
			String message = "";

			vo.setBbs_id(0);
			vo.setBbs_topic1(request.getParameter("bbs_topic1"));
			vo.setBbs_topic2(request.getParameter("bbs_topic2"));
			vo.setBbs_content(request.getParameter("bbs_content"));
			vo.setBbs_name(request.getParameter("userID"));
			vo.setBbs_date("bbs_date");

			if (dao.write(vo)) {
				message = "글 작성 완료";
			} else {
				message = "글 작성 실패";
			}

			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("writeSuccess.jsp");
			view.forward(request, response);
		} else if(cmd.equals("update")) {
			String message = "";
			
			vo.setBbs_id(Integer.parseInt(request.getParameter("id")));
			vo.setBbs_topic1(request.getParameter("bbs_topic1"));
			vo.setBbs_topic2(request.getParameter("bbs_topic2"));
			vo.setBbs_content(request.getParameter("bbs_content"));
			vo.setBbs_name(request.getParameter("userID"));
			vo.setBbs_date(dao.getDate());
			if(dao.update(vo)) 
				message = "수정이 완료되었습니다.";
			else
				message = "수정이 실패했습니다.";
			
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("writeSuccess.jsp");
			view.forward(request, response);
		}

	}

}
