import java.io.*;
import java.text.*;
import java.util.*;

public class WebPage {
	public String name;
	public String title;
	public String url;
	public WordCounter wordCounter, counter;
	public double score;
	
	public WebPage(String title, String url, KeywordList keywordList) throws IOException {
		this.title = title;
		this.url = url;
		this.wordCounter = new WordCounter(title, url, keywordList);
	}

	public int getScore() {
		return wordCounter.countScore();
	}


	 public void setScore(ArrayList<Keyword> keywords) throws IOException, ParseException {
		  try {
			  
			  score = 0;
			  for (Keyword keyword : keywords) {
				  score = score + keyword.weight * counter.countKeyword(keyword.name);
			  }
		  }catch (Exception e) {
			  e.printStackTrace();
		  
		  }
		
	 }

}


