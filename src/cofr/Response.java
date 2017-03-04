package cofr;

import java.util.HashMap;

public class Response {
	private Handler handler;
	private HashMap<String, String> params;

	public Response() {
		this.params = new HashMap<String, String>();
	}

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

	@Override
	public String toString() {
		String[] str = {
				"Response handled by: ",
//				this.handler.getName(),
				"Response status: ",
				this.params.get("status"),
				"Response message: " +
				this.params.get("message")
		};
		return String.join("\n..", str);
	}
}
