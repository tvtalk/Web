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
 * Servlet implementation class BroadcastChoiceServlet
 */
public class BroadcastSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/text;charset=utf-8");
		String keyword = request.getParameter("");
		List<ScheduleReservationDTO> list = ScheduleReservationDAO.getInstance().searchAllKeyword(keyword);
		/*보내주는코드*/
	}
}
