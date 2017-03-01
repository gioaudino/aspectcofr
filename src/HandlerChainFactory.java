import java.util.ArrayList;
import java.util.regex.Pattern;

public class HandlerChainFactory {
	public static Handler createHandlerChain(ArrayList<String> handlers) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(handlers.isEmpty()){
			throw new EmptyChainException();
		}
		String name = handlers.remove(0);
		if(!Pattern.matches(".*Handler", name)){
			throw new IllegalNameException();
		}
		Handler handler = (Handler) Class.forName(name).newInstance();
		handler.setName(name);
		handler.setNext(handlers.isEmpty() ? null : createHandlerChain(handlers));
		
		return handler;
	}
}
