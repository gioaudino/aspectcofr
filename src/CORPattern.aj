import java.util.ArrayList;

public abstract aspect CORPattern {
	pointcut creation(Object caller): call(* Chain.delegateRequest(..)) && this(caller);
	
	protected abstract String getType(Object caller);
	
	Response around (Object caller) throws InstantiationException, IllegalAccessException, ClassNotFoundException: creation(caller){
		String type = this.getType(caller);
		ArrayList<String> handlers = Chain.getHandlersForType(type);
		Handler chain = HandlerChainFactory.createHandlerChain(handlers);
		Request request = new Request();
		request.setCaller(caller);
		Response response = chain.handleRequest(request);
		System.out.println("Request was handled by " + response.getHandler().getName() + ". Response status is " + response.getParams().get("status"));
		return response;
	}
}
