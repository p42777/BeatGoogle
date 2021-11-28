import java.io.*;
import java.util.*;

public class WebTree {
	public WebNode root;
	
	public WebTree(WebPage rootPage){
		this.root = new WebNode(rootPage);
	}
	
	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException{
		setPostOrderScore(root, keywords);
	}
	
	protected void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException{
		// 1. compute the score of children nodes
		// 2. setNode score of startNode
		startNode.setNodeScore(keywords);
		if(!startNode.children.isEmpty()) {
			for(WebNode child : startNode.children) {
				setPostOrderScore(child,keywords);
				startNode.nodeScore += child.nodeScore;
			}
			
		}
	}
	
	public void eularPrintTree(){
		eularPrintTree(root);
	}
	
	public void eularPrintTree(WebNode startNode){
		int nodeDepth = startNode.getDepth();
		
		if(nodeDepth > 1) System.out.print("\n" + repeat("\t", nodeDepth-1));
		System.out.print("(");
		System.out.print(startNode.webPage.name+","+startNode.nodeScore);
		
		for(WebNode child : startNode.children){
			eularPrintTree(child);

		}
		System.out.print(")");
		if(startNode.isTheLastChild()) System.out.print("\n" + repeat("\t", nodeDepth-2));
		
	}
	
	public String repeat(String str,int repeat){
		String retVal  = "";
		for(int i = 0 ; i < repeat ; i++){
			retVal += str;
		}
		return retVal;
	}
	
	public void setOrder() {
		int end = root.children.size()-1;
		sort(0, end);
	
	}
	
	public void sort(int left, int right){
		
		// do sorting by quickSort
		
		if(left >= right) return;
		int index = left;
		double pivot = root.children.get(right).nodeScore;
		
		for(int x = left ; x < right ; x = x + 1) {
			
			if(root.children.get(x).nodeScore>=pivot) {
				swap(x, index);
				index = index + 1;
			}
		}
		// swapping
		swap(index, right);
		sort(index + 1, right);
		sort(left, index - 1);
	}
	
	
	public void swap(int index_1, int index_2){
		
		// new a WebNode tmp to do swapping
		WebNode tmp = root.children.get(index_1);
		root.children.set(index_1, root.children.get(index_2));
		root.children.set(index_2, tmp);
	}
}
