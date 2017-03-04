

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

import cofr.*;
import users.*;

public class RequestGenerator {
	private static final long NOW = System.currentTimeMillis();
	private static final long ONE_DAY = 86400 * 1000;
	private static final long YESTERDAY = NOW - ONE_DAY;
	private static final long LAST_WEEK = NOW - 7 * ONE_DAY;
	
	public static ArrayList<Request> generateForApp() {
		ArrayList<String> handlers = Chain.getHandlersForType(ApplicationChain.TYPE);
		ArrayList<Request> requests = new ArrayList<Request>();
		for(int i = 0; i <= handlers.size()/2; i++){
			int randomNum = ThreadLocalRandom.current().nextInt(0, handlers.size());
			Request request = new Request();
			String target = handlers.get(randomNum).substring(0, handlers.get(randomNum).length() - 7);

			request.addParam("target", target);
			request.setCaller(callerMock(ApplicationChain.TYPE + "Caller"));
			request.addParam("timestamp", String.valueOf(System.currentTimeMillis()));
			requests.add(request);
		}
		Request request = new Request();
		request.addParam("target", "unknown");
		request.setCaller(callerMock("None"));
		request.addParam("timestamp", String.valueOf(System.currentTimeMillis()));
		requests.add(request);
		return requests;
	}
	
	public static ArrayList<Request> generateForUser(){
		ArrayList<User> users = createUsers();
		ArrayList<Request> requests = new ArrayList<Request>();
		for(User user: users){
			Request request = new Request();
			request.setCaller(callerMock(UserChain.TYPE + "Caller"));
			request.setUser(user);
			request.addParam("target", "users");
			request.addParam("timestamp", String.valueOf(System.currentTimeMillis()));
			requests.add(request);
		}
		
		return requests;
	}
	
	private static ArrayList<User> createUsers() {
		ArrayList<User> users = new ArrayList<User>();
		Calendar cal = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		today.set(Calendar.YEAR, 1980);
		
		cal.set(1991, 3, 12);
		User user = new User();
		user
			.setCreatedAt(LAST_WEEK)
			.setName("User")
			.setSurname("Not enabled")
			.setBirthday(cal.getTime());
		users.add(user);
		
		Email email = new Email();
		email.setType("Generic").setSentAt(YESTERDAY);
		
		User user2 = new User();
		user2
			.setEnabled(true)
			.setName("User")
			.setSurname("Not active but notified recently")
			.setBirthday(cal.getTime())
			.setLastAccess(LAST_WEEK)
			.setLastEmailSent(email);
		
		users.add(user2);
		
		User user3 = new User();
		user3
			.setEnabled(true)
			.setName("User")
			.setSurname("Not active")
			.setBirthday(cal.getTime())
			.setLastAccess(LAST_WEEK);
		users.add(user3);
		
		User user4 = new User();
		user4
			.setName("User")
			.setSurname("Celebrating birthday")
			.setBirthday(today.getTime());
		
		users.add(user4);
		
		return users;
	}
	
	private static CallerI callerMock(String name){
		return new CallerMockObject(name);
	}

}
