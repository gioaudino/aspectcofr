import java.util.ArrayList;

import cofr.*;

public class AOPCofRTester {


	public static void main(String[] args) throws Throwable {
		generateApplicationCalls();
		generateUserCalls();
	}



	private static void generateUserCalls() {
		String [] userChain = {"UserBirthdayHandler", "UserNotEnabledHandler", "UserWithoutActivityHandler"};
		Chain.setHandlersForType(UserChain.TYPE, userChain);
		
		
		UserChain uc = new UserChain();
		
		String[] prints = { "Test #3:",
				"Using the chain of responsibility called " + UserChain.TYPE
						+ ". It contains the following (so ordered) classes: ",
				Chain.getHandlersForType(UserChain.TYPE).toString(), "" };
		System.out.println(String.join("\n", prints));
		ArrayList<Request> requests = RequestGenerator.generateForUser();
		callUserDelegation(uc, requests);
		
	}

	private static void callUserDelegation(UserChain uc, ArrayList<Request> requests) {
		for (Request r : requests) {
			System.out.println(r + "\n");
			Response response = uc.delegateRequest(r);
			System.out.println(response + "\n");
		}
	}

	private static void generateApplicationCalls() {
		String[] appChain = { "OkButtonHandler", "PrintButtonHandler", "PrintDialogHandler", "SaveDialogHandler" };
		Chain.setHandlersForType(ApplicationChain.TYPE, appChain);
		ApplicationChain ac = new ApplicationChain();
		ArrayList<Request> requests = RequestGenerator.generateForApp();

		String[] prints1 = { "Test #1:",
				"Using the chain of responsibility called " + ApplicationChain.TYPE
						+ ". It contains the following (so ordered) classes: ",
				Chain.getHandlersForType(ApplicationChain.TYPE).toString(), "" };
		System.out.println(String.join("\n", prints1));
		callAppDelegation(ac, requests);

		Chain.addHandlerForType(ApplicationChain.TYPE, "ApplicationHandler");
		String[] prints2 = { "\nTest #2:",
				"One handler (ApplicationHandler) has been appended to the handlers list",
				"Using again the chain of responsibility called " + ApplicationChain.TYPE
						+ ". It now contains the following (so ordered) classes: ",
				Chain.getHandlersForType(ApplicationChain.TYPE).toString() , ""};

		System.out.println(String.join("\n", prints2));
		requests = RequestGenerator.generateForApp();
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
