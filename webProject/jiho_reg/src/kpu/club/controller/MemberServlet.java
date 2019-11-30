package kpu.club.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kpu.club.domain.MemberVO;
import kpu.club.domain.MemberVO;
import kpu.club.persistence.MemberDAO;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String cmd = "";
		cmd = request.getParameter("key");
		System.out.println("doGet:" + cmd);
		
		if (cmd.equals("login")) {
			response.sendRedirect("login.jsp");
		} else if (cmd.equals("join")) {
			response.sendRedirect("register.jsp");
		} else if (cmd.equals("management")) {
			MemberDAO dao = new MemberDAO();

			System.out.println("managemnet 받음");
			List<MemberVO> list = dao.getmemberList();
			request.setAttribute("memberList", list);
			RequestDispatcher view = request.getRequestDispatcher("management.jsp");
			view.forward(request, response);
		} else if (cmd.equals("update")) {
			MemberDAO dao = new MemberDAO();
			MemberVO member = dao.read(request.getParameter("id"));
			request.setAttribute("member", member);
			RequestDispatcher view = request.getRequestDispatcher("update.jsp");
			view.forward(request, response);
		} else if (cmd.equals("delete")) {
			MemberVO vo = new MemberVO();
			MemberDAO dao = new MemberDAO();
			
			vo.setId(request.getParameter("id"));
			
			dao.delete(vo);
			
			List<MemberVO> list = dao.getmemberList();
			request.setAttribute("memberList", list);
			
			RequestDispatcher view = request.getRequestDispatcher("management.jsp");
			view.forward(request, response);
		} else if(cmd.equals("logout")) {
			response.sendRedirect("logoutSuccess.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String cmd = "";
		String message = "";
		cmd = request.getParameter("key");
		System.out.println("doPost : "+ cmd);


		// 계정생성
		if (cmd.equals("joinRequest")) {
			MemberDAO dao = new MemberDAO();
			MemberVO vo = new MemberVO();

			vo.setId(request.getParameter("id"));
			vo.setPassword(request.getParameter("passwd"));
			vo.setEmail(request.getParameter("username"));
			vo.setNickname(request.getParameter("email"));
			vo.setUsername(request.getParameter("nickname"));

			if (dao.join(vo))
				message = "가입이 완료되었습니다.";
			else
				message = "가입이 실패되었습니다.";

			request.setAttribute("message", message);
			request.setAttribute("member", vo);

			// 제어권을 넘겨주기 위한 클래스
			RequestDispatcher view = request.getRequestDispatcher("JoinResult.jsp");
			view.forward(request, response);
		}
		// 회원 정보 수정
		else if (cmd.equals("update")) {
			MemberVO vo = new MemberVO();
			MemberDAO dao = new MemberDAO();

			vo.setId(request.getParameter("id"));
			vo.setPassword(request.getParameter("passwd"));
			vo.setUsername(request.getParameter("username"));
			vo.setEmail(request.getParameter("email"));
			vo.setNickname(request.getParameter("nickname"));

			if (dao.update(vo))
				message = "수정이 완료되었습니다.";
			else
				message = "수정이 실패했습니다.";

			//request.setAttribute("message", message);

			List<MemberVO> list = dao.getmemberList();
			request.setAttribute("memberList", list);
			
			RequestDispatcher view = request.getRequestDispatcher("management.jsp");
			view.forward(request, response);
		} 
		// 로그인 확인
		else if(cmd.equals("loginCheck")) {
			System.out.println("로그인 체크 동작");
			MemberDAO dao = new MemberDAO();
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			int result = dao.login(id, password);

			if(result == 1) {
				System.out.println("로그인 성공");
				request.setAttribute("userID", id);
				message = "로그인을 성공했습니다.";
			}
			else if(result == 0) 
				message = "아이디가 틀렸습니다.";
			else if(result == -1) 
				message = "비밀번호가 틀렸습니다.";
			else
				message = "DB접속에 실패했습니다.";

			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("loginSuccess.jsp");
			view.forward(request, response);
		} 
		// 잘못된 key 값
		else {
			
		}
	}
}
