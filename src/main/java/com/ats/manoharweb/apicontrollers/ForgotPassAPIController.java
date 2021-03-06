package com.ats.manoharweb.apicontrollers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.manoharweb.commons.Common;
import com.ats.manoharweb.commons.EmailUtility;
import com.ats.manoharweb.models.Info;
import com.ats.manoharweb.models.MUser;
import com.ats.manoharweb.models.OTPVerification;
import com.ats.manoharweb.repo.MUserRepo;


@RestController
public class ForgotPassAPIController {
	
	@Autowired MUserRepo mnUserRepo;
	
	public static String senderEmail ="mahendra941213@gmail.com";
	public static String senderPassword ="m@hendra1213";
	static String mailsubject = "";
	String otp1 = null;
	
	@RequestMapping(value = { "/getMnUserDetailByMobNo" }, method = RequestMethod.POST)
	public @ResponseBody MUser getMnUserDetailByMobNo(@RequestParam String mobNo){
		OTPVerification.setConNumber(null);
		OTPVerification.setEmailId(null);
		OTPVerification.setOtp(null);
		OTPVerification.setPass(null);
		Info info = new Info();
		
		MUser user = new MUser();
		try {			
				
				user = mnUserRepo.findByUserMobileNoAndDelStatus(mobNo, 0);	
				if(user!= null) {
					OTPVerification.setUserId(user.getUserId());
					
					String emailId = user.getUserEmail();
					String conNumber = user.getUserMobileNo();
					System.err.println("User conNumber----------"+conNumber);
					char[] otp = Common.OTP(6);
					otp1 = String.valueOf(otp);
					System.err.println("User otp is : " + otp1);
					
					Info inf = EmailUtility.sendOtp(otp1, conNumber);
					
					 mailsubject = " OTP  Verification ";
					 String text = "\n OTP for change your Password: ";
					Info emailRes = EmailUtility.sendEmail(senderEmail, senderPassword,emailId, mailsubject,
							text, otp1);

				
					OTPVerification.setConNumber(conNumber);
					OTPVerification.setEmailId(emailId);
					OTPVerification.setOtp(otp1);
					OTPVerification.setPass(user.getPassword());
				}else {
					System.err.println("In Else ");

					info.setError(true);
					info.setMessage("not Matched");
					System.err.println(" not Matched ");
				}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;		
	}
	
	@RequestMapping(value = { "/verifyOTP" }, method = RequestMethod.POST)
	public @ResponseBody MUser verifyOTP(@RequestParam String otp) {
		Info info = new Info();
		
		Object object=new Object();
		HashMap<Integer, MUser>  hashMap=new HashMap<>();
		
		MUser user = new MUser();
		
		try {
			System.err.println("OTP Found------------------"+otp+"------"+OTPVerification.getOtp()+" "+OTPVerification.getUserId());
			if (otp.equals(OTPVerification.getOtp()) == true) {
				info.setError(false);
				info.setMessage("success");

				String mobile = OTPVerification.getConNumber();
				String email = OTPVerification.getEmailId();
				String pass = Common.getAlphaNumericString(7);
				// System.out.println("pass");
				//int res = staffrepo.chagePass(pass, OTPVerification.getUserId());
				
				user=mnUserRepo.findByUserIdAndDelStatus(OTPVerification.getUserId(),0);
				System.out.println("user---------"+user);
				hashMap.put(1, user);

			} else {
				info.setError(true);
				info.setMessage("failed");
			}
			
		} catch (Exception e) {

			System.err.println("Exce in verifyOTP " + e.getMessage());
			e.printStackTrace();
			info.setError(true);
			info.setMessage("excep");
		}

		return user;

	}
	
	@RequestMapping(value = { "/updateToNewPassword" }, method = RequestMethod.POST)
	public @ResponseBody Info updateToNewPassword(@RequestParam int userId, @RequestParam String newPass) {

		Info res = new Info();
		
		int a = mnUserRepo.updatePassword(newPass, userId);
		if (a > 0) {

			mailsubject = " New Credentials ";
			String text = "\n Your new username and password are : \n";

			MUser usr = new MUser();
			usr = mnUserRepo.findByUserIdAndDelStatus(userId, 0);
			if (usr != null) {
				String emailId = usr.getUserEmail();
				String password = "\n Username : " + usr.getUserName() + " \n Password : " + usr.getPassword();

				Info emailRes = EmailUtility.sendEmail(senderEmail, senderPassword, emailId, mailsubject, text, password);
			}
			res.setError(false);
			res.setMessage("success");
		}else {
			res.setError(true);
			res.setMessage("fail");
		}
	
		return res;
	}
}
