
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
	public String time;
	public int timescore;
	

		public WebPage(String url, String name, String time) {
			this.url = url;
			this.name = name;
			this.time = time;
			this.counter = new WordCounter(url);
		}
		 public void setScore(ArrayList<Keyword> keywords) throws IOException, ParseException {
			  try {
			  // timeInt();
			   score = 0;
			   for (Keyword k : keywords) {
			    
					score += k.weight * counter.countKeyword(k.name);
			   }
			  } catch (Exception e) {
			   // TODO: handle exception
			   System.out.print(" ");
			  }
			 }
		 
		 
}

