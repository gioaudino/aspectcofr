package cofr;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class HandlerChainFactory {
	public static Handler createHandlerChain(ArrayList<String> handlers)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (handlers.isEmpty()) {
			throw new EmptyChainException();
		}
		String name = handlers.remove(0);
		if (!Pattern.matches(".*Handler", name)) {
			System.out.println("--- ERROR ---\n" + name + " does not match");
			throw new IllegalNameException();
		}
		try {
			Handler handler = (Handler) Class.forName(name).newInstance();
			handler.setName(name);
			handler.setNext(handlers.isEmpty() ? null : createHandlerChain(handlers));

			return handler;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}
