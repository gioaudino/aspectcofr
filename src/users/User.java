package users;

import java.sql.Timestamp;
import java.util.Date;

public class User {
	private String name;
	private String surname;
	private String title;
	private Date birthday;
	private Timestamp lastAccess = null;
	private Timestamp createdAt = null;
	private boolean enabled = false;
	private Email lastEmailSent = null;

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getSurname() {
		return surname;
	}

	public User setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public User setTitle(String title) {
		this.title = title;
		return this;
	}

	public Date getBirthday() {
		return birthday;
	}

	public User setBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	}

	public Timestamp getLastAccess() {
		return lastAccess;
	}

	public User setLastAccess(long lastAccess) {
		this.lastAccess = new Timestamp(lastAccess);
		return this;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public User setCreatedAt(long createdAt) {
		this.createdAt = new Timestamp(createdAt);
		return this;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public User setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public Email getLastEmailSent() {
		return lastEmailSent;
	}

	public User setLastEmailSent(Email lastEmailSent) {
		this.lastEmailSent = lastEmailSent;
		return this;
	}

	@Override
	public String toString(){
		String [] str = {
			"-- USER -- ",
			"Name:\t" + name,
			"Surname:\t" + surname,
			"Birthday:\t" + birthday,
			"Created at:\t" + createdAt.toString(),
			"Enabled:\t" + String.valueOf(enabled),
			"Last access:\t" + (lastAccess != null ? lastAccess.toString() : "null"),
			"Last email sent: " + (lastEmailSent == null ? "null" : lastEmailSent.toString())
		};
		return String.join("\n..", str);
	}
	
}
