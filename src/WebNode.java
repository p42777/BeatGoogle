import java.io.*;
import java.text.*;
import java.util.*;

public class WebNode {
	
	public double nodeScore;
	public WebNode parent;
	public WebPage webPage;	
	public ArrayList<WebNode> children;
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
		
		//**compute webPage score
		webPage.setScore(keywords);
		//**set webPage score to nodeScore
		nodeScore = webPage.score;
		
		//**nodeScore += all children°¶s nodeScore 
		for(WebNode child : children){
			nodeScore += child.nodeScore;
		}
		
				
			
	}
	
	public void addChild(WebNode child){
		
		this.children.add(child);
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
