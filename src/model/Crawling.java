package model;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import DTO.ScheduleReservationDTO;
import javafx.beans.DefaultProperty;

public class Crawling {
/*	private static Crawling instance;
	private Crawling() {};
	public static Crawling getInstance(){return instance;}
	static {
		instance = new Crawling();
	}
*/
	public Crawling(){}
	//public static final String DEFAULT_URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&ie=utf8&query=%EC%98%81%ED%99%94%EA%B0%80%EC%A2%8B%EB%8B%A4&os=660621&pkid=57"; 
	public ScheduleReservationDTO crawling(String url) {
		HttpRequest httpRequest = HttpRequest.get(url);
		if (httpRequest.code() != 200)
			return null;
		System.out.println("¼º°ø");
		
		String res = httpRequest.body();
		Document doc = Jsoup.parse(res);
		
		String programImageLink = getProgramImageLink(doc);
		
		
		String programName = getProgramName(doc);
		
		
		String originalTime = getProgramTime(doc);
		String programTime = ".";
		String programDay = ".";
		if(originalTime.length()>4) {
			 programTime = originalTime.substring(originalTime.length()-4, originalTime.length());
			 programDay = originalTime.split("¿À")[0].trim();
		}
		
		String programChanel = getProgramChanel(doc);
		
		
		String programGener = getProgramGener(doc);
		if(programGener == null ){
			programGener="-";
		}
		
		String programRating = getProgramRating(doc);
		
		
		return new ScheduleReservationDTO(0,programImageLink,programName,programChanel,programTime,programDay,programGener,Float.parseFloat(programRating.substring(0, programRating.length()-1)));
		
		
	}
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
	public String getProgramGener(Document doc){
		Elements rows = doc.select("div.info_bar");
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
	public String getProgramTime(Document doc) {
		Element row = doc.select("div.brcs_detail span.inline").first();
		return row.select("a").first().nextSibling().toString();
	}
	public String getProgramRating(Document doc) {
		return doc.select("dd.bdcast_rate em.fred").first().text().toString();
	}
	public String getProgramImageLink(Document doc) {
		return doc.select("div.brcs_box  div.brcs_thumb img").first().attr("src").toString();
	}
	public String getProgramChanel(Document doc) {
		return doc.select("span.inline a").first().text().toString();
	}

}