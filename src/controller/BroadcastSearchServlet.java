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
		/*�����׽�Ʈ �Ϸ�.*/
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/text;charset=utf-8");
		/*���� getParameter�� �־���� ��.*/
		String keyword = request.getParameter("");
		
		List<ScheduleReservationDTO> list = ScheduleReservationDAO.getInstance().searchAllKeyword(keyword);
		/*�����ִ��ڵ�*/
	}
}
/*for(ScheduleReservationDTO obj : list )
System.out.println(obj.toString());*/
