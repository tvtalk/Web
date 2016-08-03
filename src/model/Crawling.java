package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DTO.ScheduleReservationDTO;


/**
 * 해당 클래스는 크롤링을 하는 클래스로써 정보를 Get만 하는 목적을 갖은 클래스이다.
 * 스레드 이슈에 안전하여 싱글톤 패턴이 적용되어 있음.
 * 각 메소드의 리턴값을 참고하여 사용하면 됨.
 * @author kwongyo
 *
 */
public class Crawling {
	private static Crawling instance;
	private Crawling() {};
	public static Crawling getInstance(){return instance;}
	static {
		instance = new Crawling();
	}

	public static final int BROADCAST_START_TIME = 0;
	public static final int BROADCAST_DAY = 1;
	
	
	public static void main(String[]args){
		System.out.println(Crawling.getInstance().crawling("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&ie=utf8&query=%EC%9E%90%EB%8F%99%EA%B3%B5%EB%B6%80%EC%B1%85%EC%83%81%EC%9C%84%ED%82%A42&os=3311748&pkid=57"));
	}
	public ScheduleReservationDTO crawling(String url) {
		HttpRequest httpRequest = HttpRequest.get(url).acceptEncoding("UTF-8");
		if (httpRequest.code() != 200)
			return null;
		System.out.println("성공");
		Document doc = Jsoup.parse(httpRequest.body());
		
		String programImageLink = getProgramImageLink(doc);
		System.out.println(programImageLink);
		
		String programName = getProgramName(doc);
		System.out.println(programName);
		
		
		
		String [] programTime = getProgramTime(doc);
		System.out.println("program start hour - "+programTime[BROADCAST_START_TIME]);
		System.out.println("program start minte - "+programTime[PROGRAM_START_MINTE]);
		
		String programChanel = getProgramChanel(doc);
		System.out.println(programChanel);
		
		
		String programGener = getProgramGener(doc);
		if(programGener == null ){
			programGener="-";
		}
		System.out.println(programGener);
		
		String programRating = getProgramRating(doc);
		
		
		return new ScheduleReservationDTO(0,programImageLink,programName,programChanel,programTime[BROADCAST_START_TIME],programTime[PROGRAM_START_MINTE],programGener,Float.parseFloat(programRating));
		
		
	}
	/**
	 * 해당 프로의 이름을 크롤링 하는 메소드.
	 * @param doc
	 * @return 해당 프로그램의 제목(띄어쓰기,숫자,영어 모두 포함)
	 */
	public String getProgramName(Document doc){
		Elements rows = doc.select("div.title");
		for (Element row : rows) {
			for (Element r : row.children()) {
				Elements i = r.getElementsByTag("a");
				for(Element e : i) {
					return e.text();
				}
			}
		}
		return null;
	}
	/**
	 * 해당 프로그램의 장르를 리턴하는 메소드.
	 * @param doc
	 * @return 성공 시 해당프로그램의 장르를 리턴. 실패시 "-"리턴.
	 */
	public String getProgramGener(Document doc){
		Elements rows = doc.select("div.info_bar");
		try {
			for (Element row : rows) {
				for (Element r : row.children()) {
					Elements i = r.getElementsByTag("a");
					for(Element e : i) {
						return e.text();
					}
				}
			}
		} catch( NullPointerException npe ) {
			System.out.println("getProgramGener Exception - "+npe.getMessage());
		}
		return "-";
	}
	public static final int PROGRAM_START_HOUR = 0 ;
	public static final int PROGRAM_START_MINTE = 1 ;
	/**
	 * 해당 프로그램의 시간정보를 리턴해줌.
	 * @param doc
	 * @return 예상되는 리턴값은 2개짜리 배열이 리턴됨. 0번방 예시 3시 -> 1500 6시->1800 // 1번방 예시 요일-> (월~목)or (뭘) 
	 */
	public String[] getProgramTime(Document doc) {
		Element row = doc.select("div.brcs_detail span.inline").first();
		String timeInfo = row.select("a").first().nextSibling().toString();
		String []programTime = timeInfo.substring(timeInfo.length()-5, timeInfo.length()).split(":");
		String programResultTime = "";
		String programDay = timeInfo.trim().split("오")[0].trim();
		String isAfternoon = timeInfo.trim().split("오")[1].substring(0,1);
		
		
		if(isAfternoon.equals("후"))
			programResultTime = (Integer.parseInt(programTime[PROGRAM_START_HOUR])+12)+programTime[PROGRAM_START_MINTE];
		else
			programResultTime = programTime[PROGRAM_START_HOUR]+programTime[PROGRAM_START_MINTE];
			
		return new String[]{programResultTime,programDay};
		
	}
	/**
	 * 시청률을 리턴해줌.
	 * @param doc
	 * @return 성공시 시청률 float형을 String으로 wrap. 실패시 "0.0"리턴.
	 */
	public String getProgramRating(Document doc) {
		try {
			String programRating = doc.select("dd.bdcast_rate em.fred").first().text().toString();
			return programRating.substring(0, programRating.length()-1);
		} catch (NullPointerException npe){
			System.out.println("getProgramRating exception - "+npe.getMessage());
		}
		return "0.0";
		
	}
	/**
	 * 해당 프로의 이미지를 크롤링하는 메소드.
	 * @param doc
	 * @return 성공시 이미지 URL을 리턴. 실패시 "-"리턴.
	 */
	public String getProgramImageLink(Document doc) {
		try {
			return doc.select("div.brcs_box  div.brcs_thumb img").first().attr("src").toString();
		} catch (NullPointerException npe){
			npe.printStackTrace();
			System.out.println(npe.getMessage());
		}
		return "-";
	}
	/**
	 * 해당 프로의 채널정보를 가져옴.
	 * @param doc
	 * @return KBS,SBS,MBC,TvN 등등
	 */
	public String getProgramChanel(Document doc) {
		return doc.select("span.inline a").first().text().toString();
	}
}