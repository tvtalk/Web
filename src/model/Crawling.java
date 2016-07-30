package model;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import javafx.beans.DefaultProperty;

public class Crawling {
	public Crawling() {
	};

	public static final String DEFAULT_URL = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&ie=utf8&query=%EC%98%81%ED%99%94%EA%B0%80%EC%A2%8B%EB%8B%A4&os=660621&pkid=57"; 
	public void crawling(String url) {
		HttpRequest httpRequest = HttpRequest
				.get("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&ie=utf8&query=%EC%B2%AD%EC%B6%98%EC%8B%9C%EB%8C%80&os=3619445&pkid=57");
		if (httpRequest.code() != 200)
			return;
		System.out.println("¼º°ø");
		String res = httpRequest.body();
		Document doc = Jsoup.parse(res);
		StringBuilder str = new StringBuilder();
		String programName = getProgramName(doc);
		str.append(programName+",");
		String programGener = getProgramGener(doc);
		str.append(programGener+",");
		String programTime = getProgramTime(doc);
		str.append(programTime+",");
		String programRating = getProgramRating(doc);
		str.append(programRating+",");
		String programImageLink = getProgramImageLink(doc);
		str.append(programImageLink+",");
		String programChanel = getProgramChanel(doc);
		str.append(programChanel+",");
		System.out.println(str.toString());
		
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