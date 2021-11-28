
import java.io.*;
import java.net.*;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class WordCounter {
	 
	private String URLString;
	private String content;
	private String newcontent;
	    
	public WordCounter(String URLString){
	  
		this.URLString = URLString; 
	}
	    
	private String fetchContent() throws IOException{
		String retVal = "";
		URL url = new URL(URLString);
	    try {
	    	URLConnection conn = url.openConnection();
	    	//conn.setRequestProperty("User-agent", ""); 
	    	InputStream input = conn.getInputStream();
	    	InputStreamReader in = new InputStreamReader(input,"utf-8");
	    	BufferedReader br = new BufferedReader(in);
	    	String line = null;

	    	while((line=br.readLine())!=null){
	    		retVal = retVal + line;
	    	}
	   
	    } catch (Exception e) {
	    	System.out.println(" Exception occur. ");
	
	    }
	    return retVal;
	    
	} 
	
	public int countKeyword(String keyword) throws IOException{
		if (content == null){
			content = fetchContent();	
		}
	  
	    //To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		Document doc = Jsoup.parse(content);
		String s = doc.text();
		int site = 0, start = 0, end = 0;
		int i = site + 5;
		if ((site = s.indexOf("正在開啟 ", start)) != -1){
			if ((end = s.indexOf(" ", i)) != -1){
				URLString = s.substring(i, end);
			  	URLString = URLString.toLowerCase();
			  	}  
		  }
	    // check : System.out.println(urlString);
		if (newcontent == null){
			newcontent = fetchContent();
		}
		newcontent = newcontent.toUpperCase();
		keyword = keyword.toUpperCase();
		
		int count = 0, from = 0,found = -1;
		while ((found = newcontent.indexOf(keyword, from)) != -1){
			count++;
			from = found + keyword.length();
		}
		int retVal = count;
		return retVal;
	 
	}
	    
}
