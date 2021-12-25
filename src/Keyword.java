
public class Keyword {
	public String name;
	//public int count;
	public double weight;
	
	public Keyword(String name,double weight){
		this.name = name;
		//this.count = count;
		this.weight = weight;
	}
	
	@Override
	public String toString(){
		return "["+name+","+","+weight+"]";
	}
	
	   
	
	public String getName(){
	    	
		return name;
	}
	   
	public double getWeight(){
	    	
		return weight;
	}
	
}