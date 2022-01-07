import java.util.ArrayList;

public class KeywordList {

 public ArrayList<Keyword> lst;

 
 public KeywordList(String[] keywords) {
	 this.lst  = new ArrayList<Keyword>();
	 this.initSettings();
	 addKeyword(keywords);
 
 }


 public String toString() {
	 
	 String s = "";
	 s = "Keywords:";
	 for (Keyword keyword :lst) {
		 s = s + " " + keyword.name;
	 }
	 return s;
 	}
 
 public void initSettings() {
	 // set KeywordList of musicFes, genres, bands and politicians
 
	 lst.add(new Keyword("音樂祭", 5.0));
	 lst.add(new Keyword("台灣", 4.5));
	 lst.add(new Keyword("臺灣", 4.5));
	 lst.add(new Keyword("獨立音樂", 4.0));
	 lst.add(new Keyword("搖滾", 3.0));
	 lst.add(new Keyword("金屬", 3.0));
	 lst.add(new Keyword("龐克", 3.0));
	 lst.add(new Keyword("另類", 3.0));
	 lst.add(new Keyword("滅火器", 2.0));
	 lst.add(new Keyword("怕胖團", 2.0));
	 lst.add(new Keyword("血肉", 2.0));
	 lst.add(new Keyword("閃靈", 2.0));
	 lst.add(new Keyword("美秀", 2.0));
	 lst.add(new Keyword("拍謝少年", 2.0));
	 lst.add(new Keyword("老破麻", 2.0));
	 lst.add(new Keyword("荷爾蒙少年", 2.0));
	 lst.add(new Keyword("海豚刑警", 2.0));
	 lst.add(new Keyword("TB", 2.0));
	 lst.add(new Keyword("甜約翰", 2.0));
	 lst.add(new Keyword("無妄", 2.0));
	 lst.add(new Keyword("Leo王", 2.0));
	 lst.add(new Keyword("春豔", 2.0));
	 lst.add(new Keyword("李權哲", 2.0));
	 lst.add(new Keyword("大港開唱", 1.5));
	 lst.add(new Keyword("浪人", 1.5));
	 lst.add(new Keyword("漂游者", 1.5));
	 lst.add(new Keyword("火球祭", 1.5));
	 lst.add(new Keyword("赤聲躁動", 1.5));
	 lst.add(new Keyword("浮現祭", 1.5));
	 lst.add(new Keyword("貴人散步", 1.5));
	 lst.add(new Keyword("山海屯", 1.5));
	 lst.add(new Keyword("爛泥", 1.5));
	 
	 lst.add(new Keyword("五月天", -2.0));
	 lst.add(new Keyword("周興哲", -2.0));
	 lst.add(new Keyword("盧秀燕", -2.0));
	 lst.add(new Keyword("流行音樂", -3.0));
	 
 }
 
 public void addKeyword(String[] keywords) {
  
	 for (String keyword : keywords) {
		
		 if(keyword.equals("")) {
			 continue;
		 } 
		 else {
			 for(Keyword k: lst) {
				 if(k.name.equals(keyword)) {
					 k.weight = k.getWeight();
					 break;
				 }
				 lst.add(new Keyword(keyword, k.getWeight()));
				 
			 }
			 /*
				if(!b) {
				lst.add(new Keyword(keyword, k.getWeight() ));
			 }
			 */
		 }
	 }
 
 }


}