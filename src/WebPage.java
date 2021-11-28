import java.io.IOException;
import java.text.ParseException;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
// import java.util.Date;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public double score; // 重點

		public WebPage(String url, String name, String time) {
			this.url = url;
			this.name = name;
			this.counter = new WordCounter(url);
		}

		
	 /*
	 other methods?
		*/
	 
	 public void setScore(ArrayList<Keyword> keywords) throws IOException, ParseException {
		  try {
		   score = 0;
		   for (Keyword k : keywords) {
		    
		    score += counter.countKeyword(k.name) * k.weight;
		   }
		  } catch (Exception e) {
		   System.out.print(" ");
		  }
		 }
	
}
