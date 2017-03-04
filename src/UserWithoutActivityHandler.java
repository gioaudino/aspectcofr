import cofr.Request;
import users.*;

public class UserWithoutActivityHandler extends GenericUserHandler {

	protected final String EMAIL_TYPE = "USER_NOT_ACTIVE";
	
	@Override
	protected boolean canHandle(Request request) {
		User user = request.getUser();
		Email email = user.getLastEmailSent();
		if (!user.isEnabled() || email == null || isInInterval(email.getSentAt(), 7)) return false;
		return isOlderThanInterval(user.getLastAccess(), 7);
	}

}
