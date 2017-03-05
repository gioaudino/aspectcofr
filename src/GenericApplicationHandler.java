
import java.util.HashMap;

import cofr.Handler;
import cofr.HandlerI;
import cofr.Request;
import cofr.Response;

public abstract class GenericApplicationHandler extends Handler {
	protected final String WHOAMI = this.getClass().getName().substring(0, this.getClass().getName().length() - 7);

	@Override
	protected boolean canHandle(Request request) {
		HashMap<String, String> params = request.getParams();
		String target = params.get("target");
		return null != target && WHOAMI.compareToIgnoreCase(target) == 0;
	}

	@Override
	protected Response doHandle(Request request) {
		Response response = new Response();
		response.setHandler(this);
		HashMap<String, String> params = new HashMap<>();
		params.put("status", HandlerI.STATUS_OK);
		params.put("timestamp", String.valueOf(System.currentTimeMillis()));
		params.put("message", "Class with name '" + name + "' handled the request");
		response.setParams(params);
		return response;
	}

	@Override
	protected Response mustHandle(Request request) {
		Response response = new Response();
		response.setHandler(this);
		HashMap<String, String> params = new HashMap<>();
		params.put("status", HandlerI.STATUS_MUST_HANDLE);
		params.put("timestamp", String.valueOf(System.currentTimeMillis()));
		params.put("message",
				"Class " + name + " is the last of the chain, therefore it has to handle the request somehow");
		response.setParams(params);
		return response;
	}
}
