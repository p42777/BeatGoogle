import java.io.*;
import java.text.*;
import java.util.*;

public class WebNode {

	public double nodeScore;
	public WebPage webPage;
	public WebNode parent;
	public ArrayList<WebNode> children;
	public String webType;

	public WebNode(WebPage webPage) {
		this.webPage = webPage;
		setNodeScore();
	}

	public void setNodeScore() {
		nodeScore = webPage.getScore();
	}

	public void setWebType(String type) {
		this.webType = type;
	}
	
	public void setPostNodeScore(ArrayList<Keyword> keywords) throws IOException{
		// called in post-order
		try {
			webPage.setScore(keywords);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		nodeScore = webPage.score;
		for(WebNode child : children){
			nodeScore += child.nodeScore;
		}
	}
	
	public boolean isTheLastChild(){
		if(this.parent == null) return true;
		ArrayList<WebNode> siblings = this.parent.children;	
		return this.equals(siblings.get(siblings.size() - 1));
	}
	
	public int getDepth(){
		int retVal = 1;
		WebNode currNode = this;
		while(currNode.parent!=null){
			retVal = retVal + 1;
			currNode = currNode.parent;
		}
		return retVal;
	}
}
