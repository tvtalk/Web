package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ScheduleReservationDAO;

/**
 * Servlet implementation class ScheduleReservationServlet
 */
public class ScheduleReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*단윝테스트 완료.*/
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/text;charset=utf-8");
		System.out.println(ScheduleReservationDAO.getInstance().getAllData());	
	}
}
