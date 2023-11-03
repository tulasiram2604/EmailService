package com.example.demo.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configure.EmailSenderService;
import com.example.demo.model.EmailOtp;
import com.example.demo.services.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/emailService")
public class EmailController
{
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailSenderService emailSenderService;
	@RequestMapping("/sendOtp/{email}")
public String sendOtp(@PathVariable String email) throws MessagingException
{
		String otp=generateOtp();
		String to=email;
		String subject="This is Otp Transaction";
		String body="Here is your "+otp;
		emailSenderService.sendEmail(to, subject, body);
		EmailOtp obj=new EmailOtp();
	obj.setEmail(email);
	obj.setOtp(otp);
	String result=emailService.addEmailOtp(obj);
	return "Otp Sent Sucessfully";
}
	@RequestMapping("/verifyOtp")
	public String verifyOtp(@RequestBody EmailOtp emailOtp)
	{
		String email=emailOtp.getEmail();
		String otp=emailOtp.getOtp();
		List<EmailOtp> objs=emailService.getEmailOtpByEmail(email);
		for(EmailOtp obj: objs)
		{
		if(obj.getOtp().equals(otp))
		{
			return "Otp Verified Sucessfully";
		}
		}
		return "Otp Did not Mat";
	}
	public static String generateOtp()
	{
		String num="0123456789";
		Random obj=new Random();
		char[] ch=new char[6];
		for(int i=0;i<ch.length;i++)
		{
			ch[i]=num.charAt(obj.nextInt(num.length()));
		}
		String otp=new String(ch);
		return otp;
	}
}
