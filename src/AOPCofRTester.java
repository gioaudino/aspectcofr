import java.util.ArrayList;
import java.util.Calendar;

import cofr.*;
import users.*;

public class AOPCofRTester {
	private static final long NOW = System.currentTimeMillis();
	private static final long SECONDS_IN_DAY = 86400 * 1000;
	private static final long YESTERDAY = NOW - SECONDS_IN_DAY;
	private static final long LAST_WEEK = NOW - 7*SECONDS_IN_DAY;
	
	public static void main(String[] args) throws Throwable {
//		generateApplicationCalls();
		Calendar cal = Calendar.getInstance();
		cal.set(1991, 3, 12);
		User usr = new User();
		Email email = new Email();
		email
			.setSentAt(NOW)
			.setType("Tryout");
		
		usr
			.setCreatedAt(LAST_WEEK)
			.setLastAccess(YESTERDAY)
			.setName("Giorgio")
			.setSurname("Audino")
			.setBirthday(cal.getTime())
			.setTitle("Student")
			.setLastEmailSent(email);
		System.out.println(usr);

	}

	private static void generateApplicationCalls(){
		String[] appChain = { "OkButtonHandler", "PrintButtonHandler", "PrintDialogHandler", "SaveDialogHandler" };
		Chain.setHandlersForType(ApplicationChain.TYPE, appChain);
		ApplicationChain ac = new ApplicationChain();
		ArrayList<Request> requests = RequestGenerator.generate(ApplicationChain.TYPE);

		String[] prints1 = { "Test #1:",
				"Using the chain of responsibility called " + ApplicationChain.TYPE
						+ ". It contains the following (so ordered) classes: ",
				Chain.getHandlersForType(ApplicationChain.TYPE).toString() };
		System.out.println(String.join("\n", prints1));
		callAppDelegation(ac, requests);

		Chain.addHandlerForType(ApplicationChain.TYPE, "ApplicationHandler");
		String[] prints2 = { "\nTest #2:",
				"One handler (ApplicationHandler) has been added to the end of the handlers list",
				"Using again the chain of responsibility called " + ApplicationChain.TYPE
						+ ". It now contains the following (so ordered) classes: ",
				Chain.getHandlersForType(ApplicationChain.TYPE).toString() };

		System.out.println(String.join("\n", prints2));
		requests = RequestGenerator.generate(ApplicationChain.TYPE);
		callAppDelegation(ac, requests);
	}

	private static void callAppDelegation(ApplicationChain ac, ArrayList<Request> requests) {
		for (Request r : requests) {
			System.out.println(r + "\n");
				Response response = ac.delegateRequest(r);
				System.out.println(response + "\n");

		}
	}

}
