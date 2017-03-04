

import cofr.*;

public aspect ApplicationCofR extends CORPattern {

	public pointcut creation(Request request): call(* ApplicationChain.delegateRequest(Request)) && args(request);
	
	@Override
	protected String getType(Request request) {
		return ApplicationChain.TYPE;
	}

}
