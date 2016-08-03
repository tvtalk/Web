package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ScheduleReservationDAO;
import DTO.ScheduleReservationDTO;

/**
 * Servlet implementation class RealtimeScheduleServlet
 */
public class RealtimeScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		List<ScheduleReservationDTO> list = ScheduleReservationDAO.getInstance().getAllData();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		List<ScheduleReservationDTO> list = ScheduleReservationDAO.getInstance().getAllData();
		List<ScheduleReservationDTO> nowList = get(list);
		request.setAttribute("realtimes", nowList);

		request.getRequestDispatcher("form_real_time.jsp").forward(request, response);
	}
	public List<ScheduleReservationDTO> get(List<ScheduleReservationDTO> list) {
		int nowTime = getTime();
		List<ScheduleReservationDTO> nowList = new ArrayList<ScheduleReservationDTO>();
		for(int i=0;i<list.size();i++){
			String [] startEnd = list.get(i).getBroadcastingTime().split("~");
			int startT = Integer.parseInt(startEnd[0]);
			int endT = Integer.parseInt(startEnd[1]);
			System.out.println("start~end == > "+startT+""+endT);
			System.out.println("now == > "+nowTime);
			if(startT <nowTime && nowTime<endT)
				nowList.add(list.get(i));
		}
		return nowList;
		
	}
	/*방송사 수 - 14*/
	/*private List<ScheduleReservationDTO> methodd(List<ScheduleReservationDTO> list){
		HashMap<String,String> map = new HashMap<String,String>();
		HashMap<String,Integer> hash = new HashMap<String,Integer>();
	}*/
	/*private List<ScheduleReservationDTO> methodd(List<ScheduleReservationDTO> list){
		List<ScheduleReservationDTO> lst = new ArrayList<ScheduleReservationDTO>();
		HashMap<String,ScheduleReservationDTO> now = new HashMap<String,ScheduleReservationDTO>();
		HashMap<String,Integer> nnooww = new HashMap<String,Integer>();
		HashMap<Integer,String> rememBrand = new HashMap<Integer,String>();
		String time = getTime();
		
		int hour = Integer.parseInt(time.split(":")[0].trim());
		int min = Integer.parseInt(time.split(":")[1].trim());
		int j=0;
		try {
			for(int i=0;i<list.size();i++){
				System.out.println("asdf");
				int rememMin=0;
				String broadTime = list.get(i).getBroadcastingTime();
				int hour2 = Integer.parseInt(broadTime.split(":")[0]);
				int min2 = Integer.parseInt(broadTime.split(":")[1]);
				if(hour2>hour) // 방송 시작 ㄴㄴ 
					continue;
				if(hour2==hour) // 방송시간
					if(min<min2) //방송시간이지만 분이 되지 않았다면 방송 시작한거 아님. 
						continue;
					else // 방송시작
						rememMin = min-min2; // 몇분 지났는지 체크.
				rememMin+= (hour-hour2)*60;
				Integer vs = nnooww.get(list.get(i).getBoradcast_brand());
				if(vs == null) {
					nnooww.put(list.get(i).getBoradcast_brand(),rememMin);
					vs = rememMin;
				}
				else
					continue;
				if(vs.intValue() > rememMin) {
					nnooww.put(list.get(i).getBoradcast_brand(),rememMin);
				}
				else
					continue;
				nnooww.put(list.get(i).getBoradcast_brand(), rememMin);
				now.put(list.get(i).getBoradcast_brand(), list.get(i));
				rememBrand.put(j++, list.get(i).getBoradcast_brand());
				
			}
			list.clear();
			for(int i=0;i<j;i++){
				lst.add(now.get(nnooww.get(rememBrand.get(i))));
			}
		} catch(Exception ex) {
		return lst;
	}*/
	private int getTime(){
		Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
		int min = oCalendar.get(Calendar.MINUTE);
		if(min<10)
			return Integer.parseInt((oCalendar.get(Calendar.HOUR)+12)+"0"+oCalendar.get(Calendar.MINUTE));
		else
			return Integer.parseInt((oCalendar.get(Calendar.HOUR)+12)+""+oCalendar.get(Calendar.MINUTE));
		
	       
	}
	

}
