package cofr;

import java.util.ArrayList;
import cofr.*;

public abstract aspect CORPattern {

	public abstract pointcut creation(Request request);

	protected abstract String getType(Request request);

	Response around(Request request): creation(request){
		String type = this.getType(request);
		ArrayList<String> handlers = Chain.getHandlersForType(type);
		try {
			HandlerI chain = HandlerChainFactory.createHandlerChain(handlers);
			Response response = chain.handleRequest(request);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proceed(request);
	}
}
