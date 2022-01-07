import java.io.*;
import java.net.*;

public class WordCounter {

	public String content;
	public KeywordList keywordList;
	private String urlStr;

	public WordCounter(String title, String url, KeywordList keywordList) throws IOException {
		this.content = title + GoogleQuery.fetchContent(url);
		this.keywordList = keywordList;
	}

	 @SuppressWarnings("unused")
	private String fetchContent() throws IOException{
	    	
	    	URL url = new URL(this.urlStr);
			URLConnection conn = url.openConnection();
			InputStreamReader reader = new InputStreamReader(conn.getInputStream(),"UTF8");
			BufferedReader br = new BufferedReader(reader);
			String retVal = "";
			String line = null;
			
			while ((line = br.readLine()) != null){
	    		retVal += line + "\n";
	    	}
	    	return retVal;
	    	
	    }
	 
	public int countScore() {
		int score = 0;
		if (content == null) {
			score = 0;
			return score;
		}
		
		content = content.toUpperCase();
		for (Keyword keyword : keywordList.lst) {
			score = score + countKeyword(keyword.name);
		}
		return score;
	}

	public int countKeyword(String keyword) {
		keyword = keyword.toUpperCase();

		int retVal = 0, fromIndex = 0, found = -1;
		int i = found = content.indexOf(keyword, fromIndex);
		while (i != -1) {
			retVal += 1;
			fromIndex = found + keyword.length();
		}
		return retVal;
	}

}
