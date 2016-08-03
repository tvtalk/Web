package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*단위테스트 완료.*/
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String nickName = request.getParameter("nickName");
		if(nickName ==null ) {
			response.getWriter().print("닉네임을 입력해 주세요.");
			System.out.println("닉네임을 입력해 주세요");
			return ;
		}
		HttpSession session = request.getSession();
		nickName = (String) session.getAttribute("nickName");
		if( nickName == null ) {
			response.getWriter().print("닉네임을 입력해 주세요.");
			System.out.println("닉넴을 입력해 주세요.");
			return ;
		}
		/*아이디 존재 x*/
		if(!UserDAO.getInstance().searchNickName(nickName)) {
			System.out.println("아이디 존재 ㄴㄴ");
			response.getWriter().printf("아이디가 존재하지 않습니다.");
		}
		
		/*디스패쳐*/
		
			
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*단위테스트 완료.*/
		/*request.setCharacterEncoding("UTF-8");
		response.setContentType("application/text;charset=utf-8");
		String nickName = request.getParameter("nickName");
		if(nickName ==null ) {
			response.getWriter().print("닉네임을 입력해 주세요.");
			System.out.println("닉네임을 입력해 주세요");
			return ;
		}
		HttpSession session = request.getSession();
		nickName = (String) session.getAttribute("nickName");
		if( nickName == null ) {
			response.getWriter().print("닉네임을 입력해 주세요.");
			System.out.println("닉넴을 입력해 주세요.");
			return ;
		}*/
		/*아이디 존재 x*/
		/*if(!UserDAO.getInstance().searchNickName(nickName)) {
			System.out.println("아이디 존재 ㄴㄴ");
			response.getWriter().printf("아이디가 존재하지 않습니다.");
		}*/
		
	}



}
