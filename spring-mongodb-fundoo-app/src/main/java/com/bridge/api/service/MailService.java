package com.bridge.api.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bridge.api.model.Email;

@Component
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void send(Email email, String id) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo(email.getTo());
		helper.setText("http://localhost:8080/user/emailvalidation?id=" + id);
		helper.setSubject("Hi");

		javaMailSender.send(message);

	}

	public void sendFogetPassWordLink(Email email) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo(email.getTo());
		helper.setText("http://localhost:8080/user/change");
		helper.setSubject(email.getSubject());

		javaMailSender.send(message);

	}

}
