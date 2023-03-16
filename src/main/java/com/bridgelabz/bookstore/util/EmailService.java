package com.bridgelabz.bookstore.util;

import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;

//	provide email service to application
	public void sendEmail(String toEmail, String subject, String body) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("rahulbhosale5252@gmail.com");
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setText(body);
		simpleMailMessage.setSubject(subject);
		mailSender.send(simpleMailMessage);
		System.out.println("Mail send to the user!!");
	}
}
