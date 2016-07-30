package controller;

import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ScheduleReservationDAO;
import DTO.ScheduleReservationDTO;
import javafx.scene.transform.Shear;

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
		/*단위테스트 완료.*/
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/text;charset=utf-8");
		/*여기 getParameter값 넣어줘야 함.*/
		String keyword = request.getParameter("");
		
		List<ScheduleReservationDTO> list = ScheduleReservationDAO.getInstance().searchAllKeyword(keyword);
		/*보내주는코드*/
	}
}
/*for(ScheduleReservationDTO obj : list )
System.out.println(obj.toString());*/
