import java.util.ArrayList;


public class KeywordList {
	
	protected ArrayList<Result> lst;

	public KeywordList(){
		this.lst = new ArrayList<Result>();
    }
	
	public ArrayList<Result> getList(){
		return this.lst;
	}
	
	public void add(Result result){
		lst.add(result);
    }
	
	private void quickSort(int L, int R){
		
		if (L < R) {
			int swapIndex = (L-1);
			for (int x = L; x <= R - 1; x++){
				if (lst.get(x).nodeScore < lst.get(R).nodeScore) {
					swapIndex += 1;
					swap(swapIndex, x);
				}
			}
			swap((swapIndex+1), R);
			quickSort(L, swapIndex);
			quickSort(swapIndex + 2, R);
		}
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
	
	public void sort(){
		if(lst.size() == 0)
		{
			System.out.println("Invalid");
		}
		else 
		{
			quickSort(0, lst.size()-1);

		}

	}
	
	
	

	private void swap(int a, int b){
		Result tmp = lst.get(a);
		lst.set(a, lst.get(b));
		lst.set(b, tmp);
	}
	
	public void show(){
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < lst.size() ; i++){
			Result result = lst.get(i);
			if(i > 0) sb.append(" ");
			
			sb.append(result.toString());
		}
		
		System.out.println(sb.toString());	
	}
	
}
