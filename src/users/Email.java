package users;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Email {

	private Timestamp sentAt;
	private String type;
	private final SimpleDateFormat datetimeformat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public Email(){
		setSentAt(System.currentTimeMillis());
	}
	
	public Timestamp getSentAt() {
		return sentAt;
	}

	public Email setSentAt(long sentAt) {
		this.sentAt = new Timestamp(sentAt);
		return this;
	}

	public String getType() {
		return type;
	}

	public Email setType(String type) {
		this.type = type;
		return this;
	}

	@Override
	public String toString() {
		String[] str = { "", "type:\t\t" + type, "Sent at:\t" + datetimeformat.format(sentAt) };
		return String.join("\n\t", str);
	}

}
