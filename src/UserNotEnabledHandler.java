import cofr.Request;
import users.*;

public class UserNotEnabledHandler extends GenericUserHandler {

	protected final String EMAIL_TYPE = "USER_NOT_ENABLED";
	
	@Override
	protected boolean canHandle(Request request) {
		User user = request.getUser();
		if (user.isEnabled() || user.getLastAccess() != null) return false;
		return isPastInterval(user.getCreatedAt(), 7);
	}
	
	@Override
	protected String getEmailType() {
		return this.EMAIL_TYPE;
	}

}
