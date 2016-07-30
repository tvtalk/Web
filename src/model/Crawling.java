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
		System.out.println("¤¾2");
		HttpRequest httpRequest = HttpRequest
				.get("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&ie=utf8&query=%EC%98%81%ED%99%94%EA%B0%80%EC%A2%8B%EB%8B%A4&os=660621&pkid=57");
		String body = "";
		if (httpRequest.code() != 200)
			return;
		String res = httpRequest.body();
		Document doc = Jsoup.parse(res);
		Elements rows = doc.select("div.title");
		// Elements rows = doc.select("html body #wrap #container di div div.list_program_wrap.ch11 ul.list_program li.on");
		for (Element row : rows) {
			for (Element r : row.children()) {
				Elements i = r.getElementsByTag("a");
				for(Element e : i) {
					System.out.println(e.text());
					
				}
			}
			
		}
	}
}
