
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WordCounter {
	 private String urlStr;
	    private String content;
	    private String newcontent;
	    
	    public WordCounter(String urlStr){
	     this.urlStr = urlStr;
	    }
	    
	    private String fetchContent() throws IOException{
	     String retVal = "";

	  URL u = new URL(urlStr);
	     try {
	      URLConnection conn = u.openConnection();

	      conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

	      InputStream input = conn.getInputStream();

	      InputStreamReader inReader = new InputStreamReader(input,"utf-8");

	      BufferedReader bufReader = new BufferedReader(inReader);
	      String line = null;

	      while((line=bufReader.readLine())!=null)
	      {
	       retVal += line;

	      }
	   
	  } catch (Exception e) {
	   System.out.println("  ");
	  }
	  
	  return retVal;
	    }
	    
	  public int countKeyword(String keyword) throws IOException{
		  if (content == null){
			  content = fetchContent();
		  }
	  
	  //To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		  Document doc = Jsoup.parse(content);
		  String c = doc.text();
		  int site=0;
		  int start=0;
		  int end=0;
		  if ((site = c.indexOf("正在開啟 ", start)) != -1){
			  	if ((end = c.indexOf(" ", site+5)) != -1){
			  		urlStr=c.substring(site+5,end);
			  		urlStr=urlStr.toLowerCase();
			  	}  
		  }
	  //System.out.println(urlStr);
		  if (newcontent == null){
			  newcontent = fetchContent();
		  }
		  newcontent = newcontent.toUpperCase();
		  keyword = keyword.toUpperCase();
	 
		  int retVal = 0;
		  int fromIdx = 0;
		  int found = -1;
	 
		  while ((found = newcontent.indexOf(keyword, fromIdx)) != -1){
			  retVal++;
			  fromIdx = found + keyword.length();
		  }
	 
	  return retVal;
	  }
	    
}