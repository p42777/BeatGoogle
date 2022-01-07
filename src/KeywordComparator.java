import java.util.Comparator;

public class KeywordComparator implements Comparator<Keyword>{

	public int compare(Keyword o1, Keyword o2){
		
		if(o1 == null || o2 == null) throw new NullPointerException();
		
		int retVal = 0;
		if(o1.count<o2.count) {
			retVal = -1;
			return retVal;
		}
		else if(o1.count>o2.count) {
			retVal = 1;
			return retVal;
		}
		else {
			return retVal;
   }
 }
}