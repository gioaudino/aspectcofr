package cofr;

public class CallerMockObject implements Caller{
	
	protected String name;
	public CallerMockObject(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
}
