package users;

import java.sql.Timestamp;

public class Email {

	private Timestamp sentAt;
	private String type;

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
	public String toString(){
		String [] str = {
			"",
			"type:\t\t" + type,
			"Sent at:\t" + String.valueOf(sentAt)
		};
		return String.join("\n\t", str);
	}

}
