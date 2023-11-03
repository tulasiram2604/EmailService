package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmailOtp;
import com.example.demo.repository.EmailOtpRepository;

@Service
public class EmailService 
{
	@Autowired
	private EmailOtpRepository emailOtpRepository;
public String addEmailOtp(EmailOtp emailOtp)
{
	emailOtpRepository.save(emailOtp);
	if(emailOtp!= null)
	{
		return "Email Otp Saved Sucessfully";
	}
	return "Something Went Wrong";
}
public List<EmailOtp> getEmailOtpByEmail(String email)
{
	List<EmailOtp> obj=emailOtpRepository.findByEmail(email);
	return obj;
}
}
