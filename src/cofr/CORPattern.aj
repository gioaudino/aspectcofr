package cofr;
import java.util.ArrayList;
import cofr.*;


public abstract aspect CORPattern {
	
	public abstract pointcut creation(Request request);
	protected abstract String getType(Request request);

	Response around(Request request) throws Exception: creation(request){
		String type = this.getType(request);
		ArrayList<String> handlers = Chain.getHandlersForType(type);
		Handler chain = HandlerChainFactory.createHandlerChain(handlers);
		Response response = chain.handleRequest(request);
		return response;
	}
}
