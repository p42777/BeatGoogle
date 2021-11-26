
import java.io.*;
import java.text.*;
import java.util.*;

public class WebNode {
	
	public WebNode parent;
	public ArrayList<WebNode> children;
	public WebPage webPage;	
	public double nodeScore;
	public String webType;
	
	public WebNode(WebPage webPage){
		
		this.webPage = webPage;
		this.children = new ArrayList<WebNode>();
	}
	
	public void setWebType(String type) {
		this.webType = type;
	}
	
	public void setNodeScore(ArrayList<Keyword> keywords) throws IOException{
		//this method should be called in post-order mode
		//1. compute webPage score
		//2. set webPage score to nodeScore
		//3. webPage.score += all childrens nodeScore
		try {
			webPage.setScore(keywords);
			// compute webPage score
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		nodeScore = webPage.score;		
	}
	
	
	public void addChild(WebNode child){
		// add the WebNode to its children list
		children.add(child);
		child.parent = this;
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
			retVal ++;
			currNode = currNode.parent;
		}
		return retVal;
	}
}