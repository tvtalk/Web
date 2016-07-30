package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookmarkDAO;
import DTO.BookmarkDTO;

/**
 * Servlet implementation class BookmarkAddServlet
 */
public class BookmarkAddServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*�����׽�Ʈ �Ϸ� ����*/
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String nickName = (String) session.getAttribute("nickName");
		if(nickName == null) {
			response.getWriter().println("�α������ּ���");
			System.out.println("�α��� ���ּ���");
			return ;
		}
		String title = request.getParameter("title");
		
		/*schedule_reservation*/
		String sr = (String) request.getParameter("sr");
		BookmarkDTO bookmarkDTO = BookmarkDAO.getInstance().isBookmark(nickName, title, 1);
		if(bookmarkDTO != null ) {
			System.out.println("�ϸ�ũ ����");
			return ;
		}
		
		
		int result = BookmarkDAO.getInstance().insertBookmark(nickName, title, Integer.parseInt(sr));
		System.out.println("result value - "+result);
		if(result == 0 ){
			response.getWriter().println("insert fail");
			System.out.println("insert fail");
		}	
	}
}
