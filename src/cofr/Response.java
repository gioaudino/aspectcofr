package cofr;
import java.util.HashMap;

public class Response {
	private Handler handler;
	private HashMap<String, String> params;

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public void addParam(String key, String value) {
		params.put(key, value);
	}

	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
}
