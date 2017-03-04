package users;

public class EmailService {
	public static boolean sendEmail(Email email, User user) {
		System.out.println("Sending email...");
		System.out.println(email);
		System.out.println("To user:");
		System.out.println(user);
		user.setLastEmailSent(email);
		System.out.println("EMAIL SENT!");
		return true;
	}
}
