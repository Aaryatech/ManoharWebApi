package com.ats.manoharweb.apicontrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.manoharweb.models.Designation;
import com.ats.manoharweb.models.Info;
import com.ats.manoharweb.models.Language;
import com.ats.manoharweb.models.MUser;
import com.ats.manoharweb.models.UserType;
import com.ats.manoharweb.repo.DesignationRepo;
import com.ats.manoharweb.repo.LanguageRepo;
import com.ats.manoharweb.repo.MUserRepo;
import com.ats.manoharweb.repo.UserTypeRepo;

@RestController
public class UserApiController {
	
	@Autowired MUserRepo mnUserRepo;
	
	@Autowired DesignationRepo desigRepo;
	
	@Autowired UserTypeRepo userTypeRepo;
	
	@Autowired LanguageRepo langRepo;
	
	/************************************************************************/
	//User
	@RequestMapping(value = { "/getAllUsers" }, method = RequestMethod.POST)
	public @ResponseBody List<MUser> getAllMnUsers(@RequestParam int compId){
		
		List<MUser> userList = new ArrayList<MUser>();
		try {
			userList = mnUserRepo.findByAllCompanyId(compId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	@RequestMapping(value = { "/getUserById" }, method = RequestMethod.POST)
	public @ResponseBody MUser getMnUserById(@RequestParam int userId, @RequestParam int compId){
		
		MUser mnUser = new MUser();
		try {
			mnUser = mnUserRepo.findByUserIdAndDelStatusAndCompanyId(userId, 0, compId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mnUser;
	}
	
	@RequestMapping(value = { "/getUserByMobNo" }, method = RequestMethod.POST)
	public @ResponseBody MUser getMnUserByMobNo(@RequestParam String mobNo, @RequestParam int userId){
		
		MUser user = new MUser();
		try {
			if(userId==0) {
				
				user = mnUserRepo.findByUserMobileNoAndDelStatus(mobNo, 0);
			}else {
				
				user = mnUserRepo.findByUserMobileNoAndDelStatusAndUserIdNot(mobNo, 0, userId);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;		
	}
	
	@RequestMapping(value = { "/deleteUserById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteMnUserById(@RequestParam int userId){
		
		Info info = new Info();
		try {
			int res = mnUserRepo.deleteUserById(userId);
			if(res>0) {
				info.setError(false);
				info.setMessage("User Deleted Successfully");
			}else {
				info.setError(true);
				info.setMessage("Failed to Delete User");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return info;		
	}
	
	@RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
	public @ResponseBody MUser addMnUser(@RequestBody MUser user){
		
		MUser mnUser = new MUser();
		try {
			mnUser = mnUserRepo.save(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mnUser;		
	}
	/*****************************************************************************/
	@RequestMapping(value = { "/getDesignations" }, method = RequestMethod.POST)
	public @ResponseBody List<Designation> getDesignations(@RequestParam int compId){
		
		List<Designation> list = new ArrayList<Designation>();
		try {
			list = desigRepo.findByDelStatusAndExInt1AndIsActiveOrderByDesignationIdDesc(0, compId, 0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	@RequestMapping(value = { "/getAllDesignations" }, method = RequestMethod.POST)
	public @ResponseBody List<Designation> getAllDesignations(@RequestParam int compId){
		
		List<Designation> list = new ArrayList<Designation>();
		try {
			list = desigRepo.findByDelStatusAndExInt1OrderByDesignationIdDesc(0, compId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	@RequestMapping(value = { "/getDesignationById" }, method = RequestMethod.POST)
	public @ResponseBody Designation getDesignationById(@RequestParam int desigId, @RequestParam int compId){
		
		Designation desig = new Designation();
		try {
			desig = desigRepo.findByDesignationIdAndExInt1(desigId, compId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return desig;
	}
	
	@RequestMapping(value = { "/deleteDeignationById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteDeignationById(@RequestParam int desigId, @RequestParam int compId){
		
		Info info = new Info();
		try {
			int res = desigRepo.deleteDesgnation(desigId, compId);
			if(res>0) {
				info.setError(false);
				info.setMessage("Designation Deleted Successfully");
			}else {
				info.setError(true);
				info.setMessage("Failed to Delete Designation");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return info;		
	}
	
	@RequestMapping(value = { "/insertDesignation" }, method = RequestMethod.POST)
	public @ResponseBody Designation insertDesignation(@RequestBody Designation designation){
		
		Designation desig = new Designation();
		try {
			desig = desigRepo.save(designation);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return desig;
	}
	
	@RequestMapping(value = { "/getDesignationByName" }, method = RequestMethod.POST)
	public @ResponseBody Designation getDesignationByName(@RequestParam String designation, @RequestParam int compId, 
			@RequestParam int desigId){
		
		Designation res = new Designation();
		try {
			if(desigId!=0) {
				res = desigRepo.findByDesignationIgnoreCaseAndExInt1AndDelStatusAndDesignationIdNot(designation, compId, 0, desigId);
			}else {
				res = desigRepo.findByDesignationIgnoreCaseAndExInt1AndDelStatus(designation, compId, 0);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/********************************************************************************/
	
	@RequestMapping(value = { "/getAllUserTypes" }, method = RequestMethod.POST)
	public @ResponseBody List<UserType> getAllUserTypes(@RequestParam int compId){
		
		List<UserType> list = new ArrayList<UserType>();
		try {
			list = userTypeRepo.findByDelStatusAndComapnyIdRequired(0, compId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	@RequestMapping(value = { "/addUserType" }, method = RequestMethod.POST)
	public @ResponseBody UserType addUserType(@RequestBody UserType userTyp){
		
		UserType userType = new UserType();
		try {
			userType = userTypeRepo.save(userTyp);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return userType;
	}
	
	@RequestMapping(value = { "/getUserTypeById" }, method = RequestMethod.POST)
	public @ResponseBody UserType getUserTypeById(@RequestParam int userTypeId, @RequestParam int compId){
		
		UserType userType = new UserType();
		try {
			userType = userTypeRepo.findByUserTypeIdAndComapnyIdRequired(userTypeId, compId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return userType;
	}
	
	@RequestMapping(value = { "/deleteUserType"}, method = RequestMethod.POST)
	public @ResponseBody Info getUserById(@RequestParam int userTypeId, @RequestParam int compId){
		
		Info info = new Info();
		try {
			int res = userTypeRepo.deleteUserTypeById(userTypeId, compId);
			if(res>0) {
				info.setError(false);
				info.setMessage("User Type Deleted Successfully");
			}else {
				info.setError(true);
				info.setMessage("Failed to Delete User Type");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	
	
	@RequestMapping(value = { "/uniqueUserType" }, method = RequestMethod.POST)
	public @ResponseBody UserType uniqueUserType(@RequestParam String userType, @RequestParam int userId, @RequestParam int compId){
		
		UserType user = new UserType();
		try {
			if(compId!=0) {
				user = userTypeRepo.findByUserTypeNameIgnoreCaseAndComapnyIdRequiredAndUserTypeIdNot(userType, compId, userId);
			}else {
				user = userTypeRepo.findByUserTypeNameIgnoreCaseAndComapnyIdRequired(userType, compId);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	/******************************************************************************/
	//Language
	@RequestMapping(value = { "/getAllLanguages" }, method = RequestMethod.POST)
	public @ResponseBody List<Language> getAllLanguages(@RequestParam int compId){
		
		List<Language> langList = new ArrayList<Language>();
		try {
			langList = langRepo.findByDelStatusAndCompanyId(0, compId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return langList;
		
	}
	
	@RequestMapping(value = { "/getLanguageById" }, method = RequestMethod.POST)
	public @ResponseBody Language getLanguageById(@RequestParam int langId, @RequestParam int compId){
		
		Language lang = new Language();
		try {
			lang = langRepo.findByLangIdAndDelStatusAndCompanyId(langId, 0, compId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lang;
		
	}
	
	@RequestMapping(value = { "/getLanguageByCode" }, method = RequestMethod.POST)
	public @ResponseBody Language getLanguageByCode(@RequestParam String code, @RequestParam int langId,  @RequestParam int compId){
		
		Language lang = new Language();
		try {
			if(langId==0) {
				
				lang = langRepo.findByLangCodeIgnoreCaseAndCompanyId(code, compId);
			}else {
				
				lang = langRepo.findByLangCodeIgnoreCaseAndCompanyIdAndLangIdNot(code, langId, compId);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lang;
		
	}
	
	@RequestMapping(value = { "/deleteLanguageById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLanguageById(@RequestParam int langId){
		
		Info info = new Info();
		try {
			int res = langRepo.deleteLanguage(langId);
			if(res>0) {
				info.setError(false);
				info.setMessage("Language Deleted Successfully");
			}else {
				info.setError(true);
				info.setMessage("Failed to Delete Language");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return info;		
	}
	
	@RequestMapping(value = { "/addLanguage" }, method = RequestMethod.POST)
	public @ResponseBody Language addLanguage(@RequestBody Language lang){
		
		Language newLang = new Language();
		try {
			newLang = langRepo.save(lang);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return newLang;		
	}
	
	/***********************************************************************/
	
	//User Type
	
}
