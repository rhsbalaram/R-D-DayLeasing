package com.dayLeasing.service.util;

import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc

/**
 * The Class MailUtil to for mail functionality
 *
 * @author Balaram
 */
@Service
public class MailUtil {

	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Send mail.
	 *
	 * @param emailId
	 *            the email id
	 * @param title
	 *            the title
	 * @param msgNotification
	 *            the msg notification
	 */
	public void sendMail(String emailId, String title, String msgNotification) {
		
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					try {
				MimeMessage mimeMessage = mailSender.createMimeMessage();
				MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
				mailMsg.setFrom("noreply@dayleasing.com");
				mailMsg.setTo(emailId);
				mailMsg.setSubject(title);
				mimeMessage.setContent(msgNotification, "text/html; charset=utf-8");
				mailSender.send(mimeMessage);
					} catch (MailException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			},  10 * 1000);
			
		
	}

}
