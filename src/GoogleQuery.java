import java.io.*;
import java.util.*;
import java.net.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class GoogleQuery {

	public int searchNum;
	public String searchKeyword, url, content, results;
	public KeywordList kLst;
	public PriorityQueue<WebNode> heap;
	HashMap<String, String> title = new HashMap<String, String>();
	
	public GoogleQuery(String searchKeyword, int search) throws IOException {
		
		this.searchKeyword = searchKeyword;
		this.searchNum = search;
		setKeywords();
		this.content = fetchContent(url);
		this.heap = new PriorityQueue<WebNode>(2 * search, new WebComparator());
		this.title = new HashMap<String, String>();
		this.results = "";
		getUrl();

	}
	
	public static String fetchContent(String url) throws IOException {
		String retVal = "";
		String line = null;
		
		// URL Connection
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		
		// Web Data
		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		while ((line = bufReader.readLine()) != null) {
			retVal = retVal + line;
		}
		return retVal;
	}
	
	public void setKeywords() {
		String[] arr = searchKeyword.split(" ");
		String keyword = "";
		
		for (int i = 0; i < arr.length; i++) {
			keyword = keyword + arr[i] + "+";
		}
		this.url = "http://www.google.com/search?q=" + keyword + "音樂節" + "&oe=utf8&num=" + 2 * searchNum;
		
		this.kLst = new KeywordList(arr);
	}
	
	public static String encodeURL(String url) {
		try {
		      String encodeURL = URLEncoder.encode( url, "UTF-8" );
		      return encodeURL;
		    } catch (UnsupportedEncodingException e) {
		      return "Error: " + e.getMessage();
		    }
	}

	public HashMap<String, String> HashMapQuery() throws IOException{

		if(content==null){
			content = fetchContent(url);
		}
		HashMap<String, String> map = new HashMap<String, String>();	
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		for(Element li : lis){
			try{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				if(title.equals("")) {
					continue;
				}
				map.put(title, citeUrl);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public String[][] ArrQuery() throws IOException {
		WebNode node;
		String[][] init = new String[searchNum][2];
		String[][] arr = init;
		int i;
		for (i = 0; i < searchNum; i++) {
			node = heap.poll();
			Boolean condition = node!= null;
			if (condition == true) {
				arr[i][0] = node.webPage.title;
				arr[i][1] = node.webPage.url;
				}
		}
		return arr;
	}
	
	public void getUrl() {
		
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div.kCrYT");
		// Elements lis = doc.select("div.kCrYT > a");
		int i = 0;
		
		for (Element li : lis) {
			String title = li.select("h3").text();
			if (title.equals("")) {
				continue;
			}
			// add / offer websiteURL to heap
			String url = "https://www.google.com" + li.attr("href");
			try {
				WebPage init = new WebPage(title, url, kLst);
				WebNode node = new WebNode(init);
				//heap.offer(node);
				heap.add(node);
				i += 1;
				System.out.printf("%2d%s%n%s%d%n", i, ": " + title, "    Score: ", node.nodeScore);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				//continue;
			}
		}
		
		results = results + String.format("-".repeat(5) + "Searching Results" + "-".repeat(5) + "\n");
		results = results + String.format("Keyword: " + searchKeyword + "\n");
		results = results + String.format("Searched: " + searchNum + "\n");
		results = results + String.format("Fine website: " + i + "\n");
	}
	
	public String show() {
		return results;
	}


	

}