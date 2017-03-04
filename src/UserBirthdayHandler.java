import java.text.SimpleDateFormat;
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
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM");
		return fmt.format(cal.getTime()).equals(fmt.format(cal2.getTime()));
	}

}
