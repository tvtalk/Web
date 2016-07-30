package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LinkDAO;
import DAO.ScheduleReservationDAO;
import DTO.ScheduleReservationDTO;
import model.Crawling;

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
			//new Crawling().crawling("");			
	}	
}
/*		List<String> list = LinkDAO.getInstance().getAllLink();
for(int i=0;i<list.size();i++)
	System.out.println(list.get(i));
	

ArrayList<ScheduleReservationDTO> ary = new ArrayList<ScheduleReservationDTO>();

for(int i=0;i<list.size();i++){
	try {
		ary.add(new Crawling().crawling(list.get(i)));
	} catch(Exception ex) {
		System.out.println("index - "+i);
		continue;
	}
}
for(int i=0;i<ary.size();i++) {
	System.out.println(ScheduleReservationDAO.getInstance().insertScheduleReservation(ary.get(i))+"  "+i);	
}*/