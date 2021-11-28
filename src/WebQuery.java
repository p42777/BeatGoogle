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

public class WebQuery {
	public String url;
	public String content;
	public String childurl1;
	public String childurl2;
	public String newsType;
	
	public WebQuery(String url) {
		
		this.url = url;
	
	}
	public String getNewsType() {
		return newsType;
	}
	public String fetchContent() throws IOException{
		
		String retVal = "";
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream input = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(input,"utf-8");

		BufferedReader br = new BufferedReader(inReader);
		String line = null;

		while((line=br.readLine())!=null){
			retVal += line;

		}
		return retVal;
	}
	
	public void getChildWeb() throws IOException{
			if(content==null){
				content= fetchContent();

			}
		Document document = Jsoup.parse(content);
		String c = document.text();
		int site = 0, start = 0, end = 0;
		if ((site = c.indexOf("正在開啟 ", start)) != -1){
		  	if ((end = c.indexOf(" ", site+5)) != -1){
		 		url = c.substring(site + 5, end);
		  		url = url.toLowerCase();
		  	}  
		}
		
		System.out.println(url);
		if(url.contains("000001")) {
			
			newsType = "000001NAME";
			content= fetchContent();
			document = Jsoup.parse(content);
		}
		
		else if(url.contains("000002")) {
			
			newsType = "000002NAME";
			content= fetchContent();		
			document = Jsoup.parse(content);

		}
		
		else if(url.contains("000003")) {
			
			newsType = "000003NAME";
			content= fetchContent();	
			document = Jsoup.parse(content);

		}
			
		else if(url.contains("000004")) {
			
			newsType = "000004NAME";
			content= fetchContent();
			document = Jsoup.parse(content);

		}
		
		else if(url.contains("000005")) {
			
			newsType = "000005";
			content= fetchContent();		
			document = Jsoup.parse(content);

		}
		
		else if(url.contains("000006")) {
			
			newsType = "000006NAME";
			content= fetchContent();		
			document = Jsoup.parse(content);
		}
		
		else {
			newsType = "othernews";
		}
		
		
		
		switch(newsType) {
			case "000001NAME" :
				
				break;
				
			case "000002NAME":
				
				break;
				
			case "000003NAME":
				
				break;
						
			case "000004NAME":
				
				break;
			
			case "000005NAME":
				
				break;
				
			case "000006NAME" :
				
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