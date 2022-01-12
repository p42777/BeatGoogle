import java.io.*;
import java.util.*;
import java.net.*;
import javax.net.ssl.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class GoogleQuery{

	public int searchNum = 20;
	public String searchKeyword, url, content, title, results;
	public static String citeUrl;	
	public static KeywordList kLst;
	public PriorityQueue<WebNode> heap;
	public long startingTime;


	public GoogleQuery(String searchKeyword) throws UnsupportedEncodingException{
		
		//this.searchNum = searchNum;
		this.startingTime = System.currentTimeMillis();
		kLst = new KeywordList();
		String encodedKeyword = java.net.URLEncoder.encode(searchKeyword,"utf-8");
		this.searchKeyword = encodedKeyword;
		this.url = "http://www.google.com/search?q=" + this.searchKeyword + "&oe=utf8&num=50";

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
			retVal = retVal + line;
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
				citeUrl = li.select("a").get(0).attr("href").substring(7);
				title = li.select("a").get(0).select(".vvjwJb").text();
				//citeUrl = li.select("a").get(0).attr("href").substring();
				if (title.equals("")) {
					continue;
				}
				WebPage rootPage = new WebPage(citeUrl,title);		
				WebTree tree = new WebTree(rootPage);
					
				ArrayList<Keyword> lst = new ArrayList<Keyword>();
				String s1 = new String("音樂祭".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s1, 20.0));
				String s2 = new String("台灣".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s2, 4.5));
				String s3 = new String("臺灣".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s3, 4.5));
				String s4 = new String("獨立音樂".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s4, 10.0));
				String s401 = new String("樂團".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s401, 10.0));
				String s402 = new String("獨立樂團".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s402, 10.0));
				String s501 = new String("陣容".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s501, 3.0));
				String s502 = new String("票價".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s502, 3.0));
				String s503 = new String("開賣".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s503, 3.0));
				String s5 = new String("搖滾".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s5, 3.0));
				String s6 = new String("金屬".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s6, 3.0));
				String s7 = new String("龐克".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s7, 3.0));
				String s8 = new String("另類".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s8, 3.0));
				String s9 = new String("滅火器".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s9, 2.0));
				String s10 = new String("怕胖團".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s10, 2.0));
				String s11 = new String("血肉".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s11, 2.0));
				String s12 = new String("閃靈".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s12, 2.0));
				String s13 = new String("美秀".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s13, 2.0));
				String s14 = new String("拍謝少年".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s14, 2.0));
				String s15 = new String("老破麻".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s15, 2.0));
				String s16 = new String("荷爾蒙少年".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s16, 2.0));
				String s17 = new String("海豚刑警".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s17, 2.0));
				String s18 = new String("TB".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s18, 2.0));	
				String s19 = new String("無妄".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s19, 2.0));
					
				String s20 = new String("大港".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s20, 1.5));
				String s201 = new String("大港開唱".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s201, 1.5));
				String s202 = new String("開唱".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s202, 1.5));
				String s21 = new String("浪人".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s21, 1.5));
				String s22 = new String("漂游".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s22, 1.5));
				String s23 = new String("火球祭".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s23, 1.5));
				String s24 = new String("赤聲躁動".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s24, 1.5));
					
				String s25 = new String("山海屯".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s25, 1.5));
				String s26 = new String("爛泥".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s26, 1.5));
					 
				String s27 = new String("五月天".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s27, -10.0));
				String s28 = new String("周興哲".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s28, -10.0));
				String s29 = new String("盧秀燕".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s29, -10.0));
				String s30 = new String("流行音樂".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s30, -20.0));
				String s301 = new String("人力銀行".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s301, -20.0));
				String s302 = new String("臺語萌典".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s302, -20.0));
				String s303 = new String("教育雲".getBytes("GBK"),"UTF-8");
				lst.add(new Keyword(s303, -20.0));
					
					
					
					
				tree.setPostOrderScore(lst);
				tree.eularPrintTree();	
					
				kLst.getList().add(WebTree.result);
					
				
				
				System.out.println(citeUrl);
				System.out.println("-----------------------------");

				// System.out.println("    Text length: " + node.webPage.wordCounter.content.length());

				// System.out.println("    Score: " + node.nodeScore);
			
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
		
		return retVal;
	}

	
	static {
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
	
	

	