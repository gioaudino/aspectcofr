
import cofr.Chain;
import cofr.Handler;
import cofr.Request;
import cofr.Response;

public class ApplicationChain extends Chain {

	public static final String TYPE = "Application";
	
	@Override
	public Response delegateRequest(Request request) {
		Response response = new Response();
		response.addParam("status", Handler.STATUS_NOT_HANDLED);
		return response;
	}

}
