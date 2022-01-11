
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.ForegroundAction;


public class WebTree {
	
	public WebNode root;
	public static Result result;
	
	public WebTree(WebPage rootPage){
		this.root = new WebNode(rootPage);
	}
	
	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException{
		setPostOrderScore(root, keywords);
	}
	
	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException{
		
		for(WebNode child : startNode.children){
			setPostOrderScore(child, keywords);	
		}
	
			startNode.setNodeScore(keywords);
		}
	
	public void eularPrintTree(){
		eularPrintTree(root);
	}
	
	private void eularPrintTree(WebNode startNode){
		int nodeDepth = startNode.getDepth();
		
		if(nodeDepth > 1) System.out.print("\n" + repeat("\t", nodeDepth-1));
		
		//print "("
		System.out.print("(");
		System.out.print(startNode.nodeScore+","+startNode.webPage.name);
		WebTree.result = new Result(startNode.webPage.name,startNode.nodeScore, GoogleQuery.citeUrl); 
		
		for(WebNode child : startNode.children){
			eularPrintTree(child);
		}
		
		//print ")"
		System.out.print(")");
		
		
		if(startNode.isTheLastChild()) System.out.print("\n" + repeat("\t", nodeDepth-2));
		
	}
	
	private String repeat(String str,int repeat){
		String retVal = " ";
		for(int i = 0 ; i < repeat ; i++){
			retVal += str;
		}
		return retVal;
	}
	
	public void setTreeOrder() {
		quickSort(0, root.children.size()-1);
	}
	

	private void swap(int aIndex, int bIndex){
		WebNode temp = root.children.get(aIndex);
		root.children.set(aIndex, root.children.get(bIndex));
		root.children.set(bIndex, temp);
	}
	
	private void quickSort(int leftbound, int rightbound){
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
	
	@SuppressWarnings("unused")
	private void bubbleSort(int array[]) {
	    int size = array.length;
	    for (int i = 0; i < size - 1; i++)
	      for (int j = 0; j < size - i - 1; j++)
	        if (array[j] > array[j + 1]) {
	          int temp = array[j];
	          array[j] = array[j + 1];
	          array[j + 1] = temp;
	        }
	  }
	
	@SuppressWarnings("unused")
	private void selectionSort(int array[]) {
	    int size = array.length;
	    for (int step = 0; step < size - 1; step++) {
	      int min_idx = step;
	      for (int i = step + 1; i < size; i++) {
	        if (array[i] < array[min_idx]) {
	          min_idx = i;
	        }
	      }
	      int temp = array[step];
	      array[step] = array[min_idx];
	      array[min_idx] = temp;
	    }
	  }
	
	@SuppressWarnings("unused")
	private void insertionSort(int array[]) {
	    int size = array.length;
	    for (int step = 1; step < size; step++) {
	      int key = array[step];
	      int j = step - 1;
	      while (j >= 0 && key < array[j]) {
	        array[j + 1] = array[j];
	        --j;
	      }
	      array[j + 1] = key;
	    }
	  }
	
}
