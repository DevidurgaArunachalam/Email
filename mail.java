package hello;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail {
	static String recipient = "pandiaraj@thelaunchclub.in";
	static String sender = "devi@thelaunchclub.in";
	static String password = "deepika@12345";
	static String subject = "Holiday Tracker";
	static String message;

	public static void main(String[] args) {

		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		System.out.println("Enter the date : ");
		Scanner scanner = new Scanner(System.in);
		String inputDateStr = scanner.nextLine();
		try {
			ArrayList<Date> leaveDates = addLeaveDates();
			Date inputDate = format.parse(inputDateStr);
			if (leaveDates.contains(inputDate)) {
				message = inputDateStr + " is holiday";
				System.out.println(message);
			} else {
				message = inputDateStr + " is woking day";
				System.out.println(message);
			}
			System.out.println("Sending mail from " + sender + " to " + recipient);
			sendMail(sender, password, recipient, subject, message);
		} catch (ParseException e) {
			System.out.println("Check the input format(DD/MM/YYYY).");
			System.out.println("Mail not sent...");
		}

		scanner.close();

	}

 	public static ArrayList<Date> addLeaveDates() throws ParseException {
		ArrayList<Date> leaveDates = new ArrayList<Date>();
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		Date d1 = format.parse("01/01/2021");
		Date d2 = format.parse("26/01/2021");
		Date d3 = format.parse("01/05/2021");
		Date d4 = format.parse("15/08/2021");
		Date d5 = format.parse("02/10/2021");
		leaveDates.add(d1);
		leaveDates.add(d2);
		leaveDates.add(d3);
		leaveDates.add(d4);
		leaveDates.add(d5);
		return leaveDates;

	}

	public static void sendMail(String sender, String password, String recipient, String subject, String body) {

		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties , new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() { 
				return new PasswordAuthentication("devidurga201299@gmail.com", "deepika@12345");
			}

		});

		try {

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(sender));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
			System.out.println("Mail sent successfully...");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
