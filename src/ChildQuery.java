import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ChildQuery {
	public String url;
	public String content;
	public String childurl1;
	public String childurl2;
	public String newsType;
	
	public ChildQuery(String url) {
		
		this.url = url;
	
	}
	public String getNewsType() {
		return newsType;
	}
	public String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null)
		{
			retVal += line;

		}
		return retVal;
	}
	
	public void getChildWeb() throws IOException{
		
			if(content==null){

				content= fetchContent();

			}
		
		
		Document doc = Jsoup.parse(content);
		
		String c=doc.text();
		int site=0;
		int start=0;
		int end=0;
		if ((site = c.indexOf("正在開啟 ", start)) != -1){
		  	if ((end = c.indexOf(" ", site+5)) != -1){
		 		url=c.substring(site+5,end);
		  		url=url.toLowerCase();
		  	}  
		}
		
		System.out.println(url);
		
		if(url.contains("emergelivehouse")) {
			
			newsType = "emerge";
			content= fetchContent();
			
			doc = Jsoup.parse(content);
		}
		else if(url.contains("megaportfest")) {
			
			newsType = "megaport";
			content= fetchContent();
			
			doc = Jsoup.parse(content);

		}
		else if(url.contains("rovation")) {
			
			newsType = "rovation";
			content= fetchContent();
			
			doc = Jsoup.parse(content);

		}
			
		else if(url.contains("https://www.storm.mg")) {
			
			newsType = "風傳媒";
			content= fetchContent();
			
			doc = Jsoup.parse(content);

		}
		else if(url.contains("https://udn.com")) {
			
			newsType = "udn聯合新聞網";
			content= fetchContent();
			
			doc = Jsoup.parse(content);

		}
		else if(url.contains("https://news.ltn.com.tw/")) {
			
			newsType = "自由時報";
			content= fetchContent();
			
			doc = Jsoup.parse(content);
		}
		else {
			
			newsType = "othernews";
		}
		
		
		Elements lis,his;
		switch(newsType) {
			case "我最愛中時電子豹" :
				lis = doc.getElementsByClass("recommended-article");
				his = lis.select("h4");
				if(his.select("a").size()>1) {
					childurl1 = his.select("a").get(0).attr("href");
					childurl2 = his.select("a").get(1).attr("href");
				}
				else {
					childurl1 ="noChild";
				}
				break;
				
			case "我最討厭set兩粒":
				lis = doc.getElementsByClass("extend news-list");
				his = lis.select("li");
				if(his.select("a").size()>1) {
					childurl1 = "https://www.setn.com/" + his.select("a").get(0).attr("href");
					childurl2 = "https://www.setn.com/" + his.select("a").get(1).attr("href");
				}
				else {
					childurl1 = "noChild";
				}
				break;
				
			case "TVBS":
				lis = doc.getElementsByClass("extended_box");
				his = lis.select("li");
				if(his.select("a").size()>1) {
					childurl1 = "https://news.tvbs.com.tw/politics/" + his.select("a").get(0).attr("href");
					childurl2 = "https://news.tvbs.com.tw/politics/" + his.select("a").get(1).attr("href");
				}
				else {
					childurl1 = "noChild";
				}
				break;
						
			case "風傳媒":
				lis = doc.getElementsByClass("article_content_inner");
				his = lis.select("li");
				if(his.select("a").size()>1) {
					childurl1 = "https://www.storm.mg" +his.select("a").get(0).attr("href");
					childurl2 = "https://www.storm.mg" +his.select("a").get(1).attr("href");
				}
				else {
					childurl1 = "noChild";
				}
				break;
			
			case "udn聯合新聞網":
				lis = doc.getElementsByClass("listing");
				his = lis.select(".more1");
				if(his.select("a").size()>1) {
					childurl1 = "https://udn.com" + his.select("a").get(0).attr("href");
					childurl2 = "https://udn.com" + his.select("a").get(1).attr("href");	
				}
				else {
					childurl1 = "noChild";
				}
				break;
				
			case "自由時報" :
				lis = doc.getElementsByClass("related boxTitle");
				if(lis.select("a").size()>1) {
					childurl1 = lis.select("a").get(0).attr("href");
					childurl2 = lis.select("a").get(1).attr("href");	
				}
				else {
					System.out.println("noChild");
					childurl1 = "noChild";
				}
				break;
			case "othernews":
				childurl1 = "noChild";
				childurl2 = "noChild";
				break;
			
			default :
				break;
		}
		
	}
	
	
}