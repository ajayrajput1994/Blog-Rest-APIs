package com.bloging.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bloging.configure.ConfMailSender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class MailSender {
	
	@Value("${spring.mail.username}")
	private String mailfrom;
	@Value("${app.url}")
	private String appurl;
	@Autowired
	private ConfMailSender confMailSender;
	public  Boolean sendMail(String to,String sub,String msg,String uuid) throws MessagingException {
		boolean f=false;
		JavaMailSender sender=confMailSender.getJavaMailSender();
		MimeMessage mime=sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mime,true,"UTF-8");
	   //	String[] cc= {""};
		String message="<h4>Verify your email address to complete registration</h4>"
				+ "Hi "+msg+",<br>Thanks for your interest in joining Upwork! To complete your registration,"
				+ " we need you to verify your email address. <a href='"+appurl+"/user/verify/"+uuid+"'> verify </a>"
				+ "Please note that not all applications to join Upwork are accepted."
				+ "We will notify you of our decision by email within 24 hours.";
		
		try {
			helper.setFrom(mailfrom);
			// if you want to send single email use setTo() method
			 helper.setTo(to); 
			 helper.setText(message,true);
			 helper.setSubject(sub);
			// if you want to send multiple email use setBcc() method
			//helper.setBcc(cc);
			sender.send(mime);
			f=true;
			log.info("send mail successfuly:{}",f);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("exception while send mail :{}",e.getMessage());
		}
		return f;
	}
}
