import cofr.CORPattern;
import cofr.Request;

public aspect UserCofR extends CORPattern {

	public pointcut creation(Request request): call(* UserChain.delegateRequest(Request)) && args(request);

	@Override
	protected String getType(Request request) {
		return UserChain.TYPE;
	}

}
