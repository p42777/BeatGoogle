
import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;



import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

import java.net.URLEncoder;



public class GoogleQuery 

{

	public String searchKeyword;

	public String url;

	public String content;
	
	public String time;
	
	//HashMap<String, String> title_time = new HashMap<String, String>();//store title and time
	 
	public Boolean results=true;

	public GoogleQuery(String searchKeyword)

	{
		
		this.searchKeyword = searchKeyword;

		this.url = "https://news.google.com/search?q="+encodeURL(searchKeyword)+"&hl=zh-TW&gl=TW&ceid=TW%3Azh-Hant";

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
	public HashMap<String, String> query() throws IOException

	{

		if(content==null)

		{

			content= fetchContent();

		}
		HashMap<String, String> retVal = new HashMap<String, String>();
		
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
//		 System.out.println(lis.size());
		
		
		for(Element li : lis)
		{
			try 

			{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				if(title.equals("")) {
					continue;
				}
				    //we directly catch the URL in google search page so the results 
				    //we catch may have some unnecessary web sites.Thus, we need to add 'googleURL' to 
				    //connect the web page
				//title_time.put(title,time);
				retVal.put(title, citeUrl);
	
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	
	public static String encodeURL(String url) {
		try {
		      String encodeURL = URLEncoder.encode( url, "UTF-8" );
		      return encodeURL;
		    } catch (UnsupportedEncodingException e) {
		      return "Error: " + e.getMessage();
		    }
	}
}