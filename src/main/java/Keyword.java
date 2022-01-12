
public class Keyword {

	public String name;
	public double weight;
	public int count;

	public Keyword(String name, double weight) {
		
		this.name = name;
		this.weight = weight;
		
	}

	@Override
	public String toString() {
		
		return "[" + name + "," + weight + "]";
	
	}
	
	public String getName(){
		
		return name;
		
	}
		   
	public double getWeight(){
		    	
		return weight;
		
	}

}