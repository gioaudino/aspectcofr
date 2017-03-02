import java.util.ArrayList;
import cofr.*;

public class AOPCofRTester {
	public static void main(String[] args) throws Throwable {
		generateApplicationCalls();

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
		callDelegation(ac, requests);

		Chain.addHandlerForType(ApplicationChain.TYPE, "ApplicationHandler");
		String[] prints2 = { "\nTest #2:",
				"One handler (ApplicationHandler) has been added to the end of the handlers list",
				"Using again the chain of responsibility called " + ApplicationChain.TYPE
						+ ". It now contains the following (so ordered) classes: ",
				Chain.getHandlersForType(ApplicationChain.TYPE).toString() };

		System.out.println(String.join("\n", prints2));
		requests = RequestGenerator.generate(ApplicationChain.TYPE);
		callDelegation(ac, requests);
	}

	private static void callDelegation(ApplicationChain ac, ArrayList<Request> requests) {
		for (Request r : requests) {
			System.out.println(r + "\n");
				Response response = ac.delegateRequest(r);
				System.out.println(response + "\n");

		}
	}

}
