import java.util.HashMap;

public class Request {
	private Object caller;
	private HashMap<String, String> params;
	
	public Object getCaller() {
		return caller;
	}
	public void setCaller(Object caller) {
		this.caller = caller;
	}
	public HashMap<String, String> getParams() {
		return params;
	}
	public void addParam(String key, String value){
		params.put(key, value);
	}
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
		
}
