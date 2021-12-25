import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class KeywordList {
	public LinkedList<Keyword> lst;
	
	
	public KeywordList(){
		this.lst = new LinkedList<Keyword>();	
	}
	
	public void add(Keyword keyword){
		lst.add(keyword);
	}
	
	public void cases() throws FileNotFoundException {
		File file = new File("klist.txt");
		Scanner sc= new Scanner(file, "utf-8");
		KeywordList kLst = new KeywordList();
	
		while(sc.hasNext()){
			String cmd = sc.next();
			switch(cmd){
        	case "add":
        	{
        		String name = sc.next();
        		//int count = sc.nextInt();
        		double weight = sc.nextDouble();
        		Keyword k = new Keyword(name, weight);
        		kLst.add(k);
        	}
        		break;
        	 default:
     	    	;       	    	      
		    }
		}
		sc.close();
	}

}


