
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import cofr.*;

public class RequestGenerator {
	public static ArrayList<Request> generate(String type) {
		ArrayList<String> handlers = Chain.getHandlersForType(type);
		ArrayList<Request> requests = new ArrayList<Request>();
		for(int i = 0; i <= handlers.size()/2; i++){
			int randomNum = ThreadLocalRandom.current().nextInt(0, handlers.size());
			Request request = new Request();
			String target = handlers.get(randomNum).substring(0, handlers.get(randomNum).length() - 7);

			request.addParam("target", target);
			request.setCaller(callerMock(type + "Caller"));
			request.addParam("timestamp", String.valueOf(System.currentTimeMillis()/1000));
			requests.add(request);
		}
		Request request = new Request();
		request.addParam("target", "unknown");
		request.setCaller(callerMock("None"));
		request.addParam("timestamp", String.valueOf(System.currentTimeMillis()/1000));
		requests.add(request);
		return requests;
	}
	
	private static CallerI callerMock(String name){
		return new CallerMockObject(name);
	}

}
