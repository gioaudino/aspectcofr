import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;

import cofr.Handler;
import cofr.HandlerI;
import cofr.Request;
import cofr.Response;
import users.*;

public abstract class GenericUserHandler extends Handler {

	protected final String EMAIL_TYPE = "DEFAULT";

	@Override
	protected Response doHandle(Request request) {
		Email email = new Email();
		email.setType(this.getEmailType());

		Response response = new Response();
		response.setHandler(this);
		HashMap<String, String> params = new HashMap<>();
		params.put("timestamp", String.valueOf(System.currentTimeMillis()));
		params.put("message", "Class with name '" + name + "' handled the request");
		params.put("user", request.getUser().toString());
		params.put("email", email.toString());

		if (EmailService.sendEmail(email, request.getUser())) {
			params.put("status", HandlerI.STATUS_OK);
		} else {
			params.put("status", HandlerI.STATUS_ERROR);
		}

		response.setParams(params);
		return response;
	}

	@Override
	protected Response mustHandle(Request request) {
		Response response = new Response();
		response.setHandler(this);
		HashMap<String, String> params = new HashMap<>();
		params.put("status", HandlerI.STATUS_MUST_HANDLE);
		params.put("timestamp", String.valueOf(System.currentTimeMillis()));
		params.put("user", request.getUser().toString());
		params.put("message", "Class " + name + " is the last of the chain. This user is not to be sent any email.");
		response.setParams(params);
		return response;
	}

	protected abstract String getEmailType();

	protected boolean isPastInterval(Timestamp timestamp, int days) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Calendar cal2 = (Calendar) cal.clone();
		cal2.set(Calendar.DAY_OF_MONTH, cal2.get(Calendar.DAY_OF_MONTH) + 1);

		return timestamp.getTime() >= cal.getTimeInMillis() && timestamp.getTime() <= cal2.getTimeInMillis();

	}

	protected boolean isInInterval(Timestamp timestamp, int days) {
		long now = System.currentTimeMillis();
		now -= days * 1000 * 86400;
		return timestamp.getTime() >= now;
	}

	protected boolean isOlderThanInterval(Timestamp timestamp, int days) {
		long now = System.currentTimeMillis();
		now -= days * 1000 * 86400;
		return timestamp.getTime() <= now;
	}

}
