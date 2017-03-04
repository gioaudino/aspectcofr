import java.util.Calendar;

import cofr.Request;
import users.User;

public class UserBirthdayHandler extends GenericUserHandler {

	protected final String EMAIL_TYPE = "BIRTHDAY";
	
	@Override
	protected boolean canHandle(Request request) {
		User user = request.getUser();
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(user.getBirthday());
		return cal.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}

}
