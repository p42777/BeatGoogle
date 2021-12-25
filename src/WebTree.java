import java.io.IOException;
import java.util.ArrayList;


public class WebTree {
	public WebNode root;
	
	public WebTree(WebPage rootPage){
		this.root = new WebNode(rootPage);
	}
	
	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException{
		setPostOrderScore(root, keywords);
	}
	
	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException{
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
	
	private void eularPrintTree(WebNode startNode){
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
	
	private String repeat(String str,int repeat){
		String retVal  = "";
		for(int i=0;i<repeat;i++){
			retVal+=str;
		}
		return retVal;
	}
	public void setTreeOrder() {
		
		quickSort(0, root.children.size()-1);
	
	}
	
	private void quickSort(int leftbound, int rightbound){
		//implement quickSort algorithm
		if(leftbound>=rightbound) {
			return;
		}
		double pivot = root.children.get(rightbound).nodeScore;
		int swapIndex = leftbound;
		for(int x=leftbound; x<rightbound; x++) {
			if(root.children.get(x).nodeScore>=pivot) {
				swap(x, swapIndex);
				swapIndex++;
			}
		}
		swap(swapIndex,rightbound);
		quickSort(swapIndex+1,rightbound);
		quickSort(leftbound,swapIndex-1);
	}
	
	
	private void swap(int aIndex, int bIndex){
		WebNode temp = root.children.get(aIndex);
		root.children.set(aIndex, root.children.get(bIndex));
		root.children.set(bIndex, temp);
	}
}