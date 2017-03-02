package cofr;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

abstract class Chain {
	protected static HashMap<String, ArrayList<String>> handlerMap = new HashMap<String, ArrayList<String>>();

	public static void addHandlerForType(String type, String handlerName) {
		ArrayList<String> handlers = handlerMap.get(type);
		if (handlers == null) {
			handlers = new ArrayList<String>();
		}
		if (!handlers.contains(handlerName)) {
			handlers.add(handlerName);
		}
		handlerMap.put(type, handlers);
	}

	public static void removeHandlerForType(String type, String handlerName) {
		ArrayList<String> handlers = handlerMap.get(type);
		if (handlers != null) {
			handlers.remove(handlerName);
		}
		handlerMap.put(type, handlers);

	}

	public static void setHandlersForType(String type, String[] handlers) {
		handlerMap.put(type, new ArrayList<String>(Arrays.asList(handlers)));
	}

	public static ArrayList<String> getHandlersForType(String type) {
		return handlerMap.get(type);
	}

}
