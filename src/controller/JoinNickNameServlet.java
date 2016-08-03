package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;

/**
 * Servlet implementation class ChatJoinServletXS
 */
public class JoinNickNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			String nickName = request.getParameter("nickName").split(":")[1];
			HttpSession session = request.getSession();
			/*존재 x*/
			if(!UserDAO.getInstance().searchNickName("nickName")) {
				UserDAO.getInstance().insertUser(nickName);
				response.getWriter().write("닉네임 등록& 로그인 완료");
			}
			else
				response.getWriter().write("로그인 완료.");
			session.setAttribute("nickName", nickName);
			
	}	
}
