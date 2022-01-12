
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public WordCounter wordCounter;
	public double score;
	
	public WebPage(String url,String name) throws UnsupportedEncodingException{
		
		this.url = url;
		this.name = name;
		this.wordCounter = new WordCounter(url);	
	}
	
	public void setScore(ArrayList<Keyword> keywords) throws IOException{
					  score = 0;
			  for (Keyword keyword : keywords) {
				  score = score + keyword.weight * wordCounter.countKeyword(keyword.name);
			  }
		  
	}
			
}
