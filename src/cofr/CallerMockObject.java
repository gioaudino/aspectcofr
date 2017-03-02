package cofr;

public class CallerMockObject implements CallerI{
	
	protected String name;
	public CallerMockObject(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
}
