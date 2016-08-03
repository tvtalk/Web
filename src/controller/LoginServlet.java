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
		/*�����׽�Ʈ �Ϸ�.*/
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String nickName = request.getParameter("nickName");
		if(nickName ==null ) {
			response.getWriter().print("�г����� �Է��� �ּ���.");
			System.out.println("�г����� �Է��� �ּ���");
			return ;
		}
		HttpSession session = request.getSession();
		nickName = (String) session.getAttribute("nickName");
		if( nickName == null ) {
			response.getWriter().print("�г����� �Է��� �ּ���.");
			System.out.println("�г��� �Է��� �ּ���.");
			return ;
		}
		/*���̵� ���� x*/
		if(!UserDAO.getInstance().searchNickName(nickName)) {
			System.out.println("���̵� ���� ����");
			response.getWriter().printf("���̵� �������� �ʽ��ϴ�.");
		}
		
		/*������*/
		
			
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*�����׽�Ʈ �Ϸ�.*/
		/*request.setCharacterEncoding("UTF-8");
		response.setContentType("application/text;charset=utf-8");
		String nickName = request.getParameter("nickName");
		if(nickName ==null ) {
			response.getWriter().print("�г����� �Է��� �ּ���.");
			System.out.println("�г����� �Է��� �ּ���");
			return ;
		}
		HttpSession session = request.getSession();
		nickName = (String) session.getAttribute("nickName");
		if( nickName == null ) {
			response.getWriter().print("�г����� �Է��� �ּ���.");
			System.out.println("�г��� �Է��� �ּ���.");
			return ;
		}*/
		/*���̵� ���� x*/
		/*if(!UserDAO.getInstance().searchNickName(nickName)) {
			System.out.println("���̵� ���� ����");
			response.getWriter().printf("���̵� �������� �ʽ��ϴ�.");
		}*/
		
	}



}
