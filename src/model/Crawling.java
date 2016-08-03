package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DTO.ScheduleReservationDTO;


/**
 * �ش� Ŭ������ ũ�Ѹ��� �ϴ� Ŭ�����ν� ������ Get�� �ϴ� ������ ���� Ŭ�����̴�.
 * ������ �̽��� �����Ͽ� �̱��� ������ ����Ǿ� ����.
 * �� �޼ҵ��� ���ϰ��� �����Ͽ� ����ϸ� ��.
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
		System.out.println("����");
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
	 * �ش� ������ �̸��� ũ�Ѹ� �ϴ� �޼ҵ�.
	 * @param doc
	 * @return �ش� ���α׷��� ����(����,����,���� ��� ����)
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
	 * �ش� ���α׷��� �帣�� �����ϴ� �޼ҵ�.
	 * @param doc
	 * @return ���� �� �ش����α׷��� �帣�� ����. ���н� "-"����.
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
	 * �ش� ���α׷��� �ð������� ��������.
	 * @param doc
	 * @return ����Ǵ� ���ϰ��� 2��¥�� �迭�� ���ϵ�. 0���� ���� 3�� -> 1500 6��->1800 // 1���� ���� ����-> (��~��)or (��) 
	 */
	public String[] getProgramTime(Document doc) {
		Element row = doc.select("div.brcs_detail span.inline").first();
		String timeInfo = row.select("a").first().nextSibling().toString();
		String []programTime = timeInfo.substring(timeInfo.length()-5, timeInfo.length()).split(":");
		String programResultTime = "";
		String programDay = timeInfo.trim().split("��")[0].trim();
		String isAfternoon = timeInfo.trim().split("��")[1].substring(0,1);
		
		
		if(isAfternoon.equals("��"))
			programResultTime = (Integer.parseInt(programTime[PROGRAM_START_HOUR])+12)+programTime[PROGRAM_START_MINTE];
		else
			programResultTime = programTime[PROGRAM_START_HOUR]+programTime[PROGRAM_START_MINTE];
			
		return new String[]{programResultTime,programDay};
		
	}
	/**
	 * ��û���� ��������.
	 * @param doc
	 * @return ������ ��û�� float���� String���� wrap. ���н� "0.0"����.
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
	 * �ش� ������ �̹����� ũ�Ѹ��ϴ� �޼ҵ�.
	 * @param doc
	 * @return ������ �̹��� URL�� ����. ���н� "-"����.
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
	 * �ش� ������ ä�������� ������.
	 * @param doc
	 * @return KBS,SBS,MBC,TvN ���
	 */
	public String getProgramChanel(Document doc) {
		return doc.select("span.inline a").first().text().toString();
	}
}