import java.io.*;
import java.util.*;
import java.net.*;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery{

	public int searchNum = 20;
	public String searchKeyword, url, content, title, results;
	public static String citeUrl;	
	public KeywordList kLst;
	public PriorityQueue<WebNode> heap;
	public long startingTime;


	public GoogleQuery(String searchKeyword) throws UnsupportedEncodingException{
		
		//this.searchNum = search;
		this.startingTime = System.currentTimeMillis();
		kLst = new KeywordList();
		String encodedKeyword = java.net.URLEncoder.encode(searchKeyword,"utf-8");
		this.searchKeyword = encodedKeyword;
		this.url = "http://www.google.com/search?q=" + this.searchKeyword + "&oe=utf8&num=50";

	}
	public GoogleQuery() {
		
		}
	
	private String fetchContent() throws IOException{
		
		String retVal = "";
		String line = null;
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bf = new BufferedReader(inReader);
		
		while ((line = bf.readLine()) != null) {
			retVal += line;
		}
		return retVal;
	}

	public HashMap<String, String> query() throws IOException,MalformedURLException,FileNotFoundException {

		if (content == null){
			content = fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		
		for (Element li : lis) {
			try{
				title = li.select("a").get(0).select(".vvjwJb").text();
				citeUrl = li.select("a").get(0).attr("href").substring(8);
				if (title.equals("")) {
					continue;
				}
				WebPage rootPage = new WebPage(citeUrl,title);		
				WebTree tree = new WebTree(rootPage);
				
				// absolute path
				File file = new File("/Users/hsuehpo42777/Desktop/SEProject/input.txt");
				Scanner sc = new Scanner(file);
				

				while(sc.hasNextLine()){
					int num = sc.nextInt();
					ArrayList<Keyword> keywords = new ArrayList<Keyword>();
					for(int i = 0 ; i < num ; i++){
						
						String s = sc.next();
						String name = new String(s.getBytes("GBK"),"UTF-8");
						
						double weight = sc.nextDouble();
						Keyword keyword = new Keyword(name, weight);
						keywords.add(keyword);
					}
					tree.setPostOrderScore(keywords);
					tree.eularPrintTree();	
					
					kLst.getList().add(WebTree.result);
				}
				sc.close();
				System.out.println(citeUrl);
				System.out.println("-----------------------------");
//				System.out.println("    Text length: " + node.webPage.wordCounter.content.length());
//				System.out.println("    Score: " + node.nodeScore);
			
				
			

			} catch (Exception e) {
				 System.out.println("Skip: " + e.getMessage());
				 continue;
			} 
			}
		
		kLst.sort();
		Collections.reverse(kLst.lst);
		kLst.show();
		
		for(Result result:kLst.lst) {
			retVal.put(result.name, result.url);

		}
		
		//results = results + String.format("-".repeat(5) + "Searching Results" + "-".repeat(5) + "\n");
		//results = results + String.format("Keyword: " + searchKeyword + "\n");
		//results = results + String.format("Searched: " + searchNum + "\n");
		//results = results + String.format("Fine website: " + okUrl + "\n");
		//results += "Read error website: " + readErrorUrl + "\n";
		//results += "---------------------------" + "\n";
		//results += "Searching Time: " + ((System.currentTimeMillis() - startingTime) / 1000) + " sec.\n\n";
	
	 
		return retVal;
	}

	
	 static{
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname,SSLSession session) {
				return true;
			}
		}
		);
	}
	 
	 public ArrayList<String> hyper() throws IOException{
			if(content==null){
				content= fetchContent();
			}
			Document doc = Jsoup.parse(content);
			Elements lis = doc.select("a");
			ArrayList<String>list = new ArrayList<String>();
			for(Element li : lis) {
				String url = li.attr("href");
				list.add(url);	
			}
			return list;
			
	 }
	 public static String encodeURL(String url) {
			try {
				String encodeURL = URLEncoder.encode(url, "UTF-8");
				return encodeURL;
			} catch (UnsupportedEncodingException e) {
				return "Error: " + e.getMessage();
			}
		}
	 
	 
}
	
	
