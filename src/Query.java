import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.URLEncoder;



public class Query {

	public String searchKeyword;
	public String url;
	public String content;
	public String time;
	
	HashMap<String, String> title_time = new HashMap<String, String>();//store title and time
	public Boolean results = true;

	public Query(String searchKeyword){
		
		this.searchKeyword = searchKeyword;
		this.url = "https://news.google.com/search?q="+encodeURL(searchKeyword)+"&hl=zh-TW&gl=TW&ceid=TW%3Azh-Hant";

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
		while((line = br.readLine())!=null){
			
			retVal += line;

		}
		return retVal;
	}
	
	public HashMap<String, String> query() throws IOException{

		if(content==null){

			content = fetchContent();

		}
		HashMap<String, String> retVal = new HashMap<String, String>();
		
		Document doc = Jsoup.parse(content);
		
				    //Directly catch the URL in google search page so the results we catch may have some unnecessary web sites.
		            //Thus, we need to add 'googleURL' to connect the web page
				
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