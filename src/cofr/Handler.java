package cofr;
import java.util.HashMap;

public abstract class Handler {
	public static final String STATUS_OK = "OK";
	public static final String STATUS_NOT_HANDLED = "NOT_HANDLED";
	public static final String STATUS_MUST_HANDLE = "MUST_HANDLE";

	protected String name;
	protected Handler next;
	protected final String WHOAMI = this.getClass().getName().substring(0,  this.getClass().getName().length() - 7); 

	public Handler(){
		name = this.getClass().getName();
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNext(Handler next) {
		this.next = next;
	}

	protected boolean canHandle(Request request){
		HashMap<String, String> params = request.getParams();
		String target = params.get("target");
		return null != target && WHOAMI.compareToIgnoreCase(target) == 0;
	}

	public Response handleRequest(Request request) {
		if (this.canHandle(request)) {
			return this.doHandle(request);
		}
		if (this.next == null) {
			return this.mustHandle(request);
		}
		return next.handleRequest(request);
	}

	protected Response doHandle(Request request) {
		Response response = new Response();
		response.setHandler(this);
		HashMap<String, String> params = new HashMap<>();
		params.put("status", Handler.STATUS_OK);
		params.put("timestamp", String.valueOf(System.currentTimeMillis()));
		params.put("message", "Class with name '" + name + "' handled the request");
		response.setParams(params);
		return response;
	}

	protected Response mustHandle(Request request) {
		Response response = new Response();
		response.setHandler(this);
		HashMap<String, String> params = new HashMap<>();
		params.put("status", Handler.STATUS_MUST_HANDLE);
		params.put("timestamp", String.valueOf(System.currentTimeMillis()));
		params.put("message",
				"Class " + name + " is the last of the chain, therefore it has to handle the request somehow");
		response.setParams(params);
		return response;
	}
}
