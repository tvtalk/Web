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
 * Servlet implementation class GenreSearchServlet
 */
public class GenreSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String generNo = request.getParameter("generNo").trim();
		System.out.println(generNo);
		switch(generNo){
		case "예능" : generNo = "a"; break;
		case "드라마" : generNo = "b"; break;
		case "시사" : generNo = "c"; break;
		case "교양" : generNo = "d"; break;
		case "스포츠" : generNo = "e"; break;
		}
		List<ScheduleReservationDTO> list = ScheduleReservationDAO.getInstance().searchGenreNo(generNo);
		request.setAttribute("realtimes", list);
		request.getRequestDispatcher("form_real_time.jsp").forward(request, response);;
	}



}
