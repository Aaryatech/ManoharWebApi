package com.ats.manoharweb.commons ;

import java.net.URLEncoder;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ats.manoharweb.models.Info;





public class EmailUtility {
	
	public static Info sendEmail(String senderEmail,String senderPassword,String recipientEmail,String mailsubject,
		String defUsrName,String defPass) {
		
		Info info=new Info();
		
		try {
			
		final String emailSMTPserver = "smtp.gmail.com";
		final String emailSMTPPort = "587";
		final String mailStoreType = "imaps";
		final String username = senderEmail;//"atsinfosoft@gmail.com";
		final String password =senderPassword;//"atsinfosoft@123";

		System.out.println("username" + username);
		System.out.println("password" + password);

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");


		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Store mailStore = session.getStore(mailStoreType);
			mailStore.connect(emailSMTPserver, username, password);

			String address =recipientEmail;// "atsinfosoft@gmail.com";// address of to

			String subject = mailsubject;//" Login Credentials For RUSA Login  ";

			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(username));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(defUsrName + defPass);
			
		
			Transport.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMessage("email_exce");
			//System.err.println("Mail Response1 : "+info);
			System.err.println("Ex1"+e.getMessage());
		}
			
			info.setError(false);
			info.setMessage("success_email");
			//System.err.println("Mail Response2 : "+info);
			
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("email_exce");
			//System.err.println("Mail Response3 : "+info);
			System.err.println("Ex2"+e.getMessage());
		}
		
		return info;
		
	}
	
	//OTP EMAIL
	public static Info sendEmailOTP(String senderEmail,String senderPassword,String recipientEmail,String mailsubject,
			String defUsrName,String defPass) {
			
			Info info=new Info();
			
			try {
				
			final String emailSMTPserver = "smtp.gmail.com";
			final String emailSMTPPort = "587";
			final String mailStoreType = "imaps";
			final String username = senderEmail;//"atsinfosoft@gmail.com";
			final String password =senderPassword;//"atsinfosoft@123";

			System.out.println("username" + username);
			System.out.println("password" + password);

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");


			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				Store mailStore = session.getStore(mailStoreType);
				mailStore.connect(emailSMTPserver, username, password);

				String address =recipientEmail;// "atsinfosoft@gmail.com";// address of to

				String subject = mailsubject;//" Login Credentials For RUSA Login  ";

				Message mimeMessage = new MimeMessage(session);
				mimeMessage.setFrom(new InternetAddress(username));
				mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
				mimeMessage.setSubject(subject);
				mimeMessage.setText("OTP Verification for  RUSA Software \n OTP is: " + defPass);
				
			
				Transport.send(mimeMessage);
			} catch (Exception e) {
				e.printStackTrace();
				info.setError(true);
				info.setMessage("email_exce");
			}
				
				info.setError(false);
				info.setMessage("success_email");
			}catch (Exception e) {
				
				info.setError(true);
				info.setMessage("email_exce");
			}
			
			return info;
			
		}
	
	public static Info sendMsg(String userName,String pass, String phoneNo) {
			
			Info info=new Info();
			
			try {
				   
				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				/*map.add("authkey", "74499AcqeCdljW5ae561dd");
				map.add("mobiles", phoneNo);
				map.add("message", "RUSA CREDENTIAL Your User Name :" + userName +" Your Password :" + pass +" Plz Dont Share To Any One ");
				map.add("sender", "ESYRTO");
				map.add("route", "4");
				map.add("country", "91");*/
				
				//
				
			/*
			 * map.add("senderID", "RUSAMH"); map.add("user",
			 * "spdrusamah@gmail.com:Cyber@mva"); map.add("receipientno", phoneNo.trim());
			 * map.add("dcs", "0"); map.add(
			 * "msgtxt","User Registration Successful for RUSA Software \n Username: " +
			 * userName + "\n Password: " + pass); map.add("state", "4");
			 * 
			 * 
			 * //String response =
			 * restTemplate.postForObject("http://control.bestsms.co.in/api/sendhttp.php",
			 * map, String.class);
			 * 
			 * String response = restTemplate.postForObject(
			 * "http://api.mVaayoo.com/mvaayooapi/MessageCompose", map, String.class);
			 */
				String msg="User Registration Successful for RUSA Software \n Username: " + userName + "\n Password: " + pass;
				
				map.add("username", "rusamah-wb");
				map.add("password", "Rus@@123456");
				map.add("senderid", "MHRUSA");
				map.add("mobileno", phoneNo.trim());
				map.add("content", msg); 
				map.add("smsservicetype", "singlemsg"); 
				String sms = restTemplate.postForObject("https://msdgweb.mgov.gov.in/esms/sendsmsrequest",
				map, String.class);
				 
				info.setError(false);
				info.setMessage(sms);
			  
			}catch (Exception e) {
				
				info.setError(true);
				info.setMessage("sendMsg");
			}
			
			return info;
		}

	
	public static Info sendOtp(String OTP, String phoneNo) {
		
		Info info=new Info();
		
		try {
			   
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			String msg = "We welcome You to Madhvi!\n"+
						 "Your OTP to change your password is ("+OTP+").";
						 
			String message = msg;
			String mob = phoneNo.trim();
		
		//NA-	String sms = restTemplate.getForObject("https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?APIKEY=pJMAaVPuGbh&MobileNo="+mob+"&SenderID=MADHVI&Message="+message+"&ServiceName=TEMPLATE_BASED", String.class);
		
			info.setError(false);
			//info.setMessage(sms);
		  
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("sendMsg");
		}
		
		return info;
	}
	
	public static Info send(String phoneNo, String msg) {
		
		Info info=new Info();
		
		try {
			   
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
//			String msg = "We welcome You to Madhvi!\n"+
//						 "Your OTP to change your password is ("+OTP+").";
						 
			String message = msg;
			String mob = phoneNo.trim();
		
//NA-		String sms = restTemplate.getForObject("https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?APIKEY=pJMAaVPuGbh&MobileNo="+mob+"&SenderID=MADHVI&Message="+message+"&ServiceName=TEMPLATE_BASED", String.class);
		
			info.setError(false);
			//info.setMessage(sms);
			System.err.println("SMS Resp : "+info);
		  
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("sendMsg");
			System.err.println("SMS Resp : "+info);
			System.err.println("SMS Ex : "+e.getMessage());			
		}
		
		return info;
	}

	public static void sendinBulk(String msg, List<String> mobList) {
		
		Info info=new Info();
		
		try {
			   
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
//			String msg = "We welcome You to Madhvi!\n"+
//						 "Your OTP to change your password is ("+OTP+").";
			String sms="";	 
			String message = msg;
			List<String> mob = mobList;
			for (int i = 0; i < mob.size(); i++) {
				System.out.println("Fr Mobile No.----"+mob.get(i));
				try {
			//NA-		sms = restTemplate.getForObject("https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?APIKEY=pJMAaVPuGbh&MobileNo="+mob.get(i)+"&SenderID=MADHVI&Message="+message+"&ServiceName=TEMPLATE_BASED", String.class);
				}catch (Exception e) {
					System.err.println("Ex in Bulk SMS : "+e.getMessage());
				}
			}
		
			 
			info.setError(false);
			//info.setMessage(sms);
			System.err.println("SMS Resp : "+info);
		  
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("sendMsg");
			System.err.println("SMS Resp : "+info);
			System.err.println("SMS Ex : "+e.getMessage());			
		}
		
	}

	public static Info sendOrderEmail(String senderEmail, String senderPassword, String frEmail, String mailsubject,
			String mailText) {
		
		Info info=new Info();
		
		try {
			
		final String emailSMTPserver = "smtp.gmail.com";
		final String emailSMTPPort = "587";
		final String mailStoreType = "imaps";
		final String username = senderEmail;//"atsinfosoft@gmail.com";
		final String password =senderPassword;//"atsinfosoft@123";

		System.out.println("username" + username);
		System.out.println("password" + password);

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");


		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Store mailStore = session.getStore(mailStoreType);
			mailStore.connect(emailSMTPserver, username, password);

			String address =frEmail;

			String subject = mailsubject;

			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(username));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
			mimeMessage.setSubject(subject);
			mimeMessage.setContent(mailText, "text/html");
			
		
			Transport.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMessage("email_exce");
			//System.err.println("Mail Response1 : "+info);
			System.err.println("Ex1"+e.getMessage());
			e.printStackTrace();
		}
			
			info.setError(false);
			info.setMessage("success_email");			
			//System.err.println("Mail Response2 : "+info);
			
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("email_exce");
			//System.err.println("Mail Response3 : "+info);
			System.err.println("Ex2"+e.getMessage());
			e.printStackTrace();
		}
		
		return info;
		
	}

	public static Info send(String frMob, Formatter f) {
		
		Info info=new Info();
		
		try {
			   
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
//			String msg = "We welcome You to Madhvi!\n"+
//						 "Your OTP to change your password is ("+OTP+").";
						 
			
			String mob = frMob.trim();
		
	//NA-		String sms = restTemplate.getForObject("https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?APIKEY=pJMAaVPuGbh&MobileNo="+mob+"&SenderID=MADHVI&Message="+f+"&ServiceName=TEMPLATE_BASED", String.class);
		
			info.setError(false);
		//	info.setMessage(sms);
			System.err.println("SMS Resp : "+info);
		  
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("sendMsg");
			System.err.println("SMS Resp : "+info);
			System.err.println("SMS Ex : "+e.getMessage());			
		}
		
		return info;
	}
	
	

}