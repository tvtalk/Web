package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ScheduleReservationDAO;
import DTO.ScheduleReservationDTO;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String keyword = request.getParameter("keyword").trim();
		System.out.println(keyword);
		List<ScheduleReservationDTO> list = ScheduleReservationDAO.getInstance().searchAllKeyword(keyword);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
			System.out.println(list.get(i));
		request.setAttribute("realtimes", list);
		request.getRequestDispatcher("form_real_time.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String keyword = request.getParameter("keyword").trim();
		System.out.println(keyword);
		List<ScheduleReservationDTO> list = ScheduleReservationDAO.getInstance().searchAllKeyword(keyword);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
			System.out.println(list.get(i));
		request.setAttribute("realtimes", list);
		request.getRequestDispatcher("form_real_time.jsp");
	}
	

}
