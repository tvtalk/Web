package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookmarkDAO;
import DTO.BookmarkDTO;

/**
 * Servlet implementation class BookmarkGetServlet
 */
public class BookmarkGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�����׽�Ʈ�Ϸ�
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String nickName = (String)session.getAttribute("nickName");
		if(nickName==null) {
			System.out.println("�α� �ؾߵ�");
			response.getWriter().println("�α������ּ���");
		}
		List<BookmarkDTO> list = BookmarkDAO.getInstance().getBookmark(nickName);
		for(BookmarkDTO obj : list )
			System.out.println(obj.toString());
		/*���⿡ �����ĺκ�*/
	}
}
