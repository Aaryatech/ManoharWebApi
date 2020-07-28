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
import com.ats.manoharweb.models.MUser;
import com.ats.manoharweb.models.UserType;
import com.ats.manoharweb.repo.DesignationRepo;
import com.ats.manoharweb.repo.MUserRepo;
import com.ats.manoharweb.repo.UserTypeRepo;

@RestController
public class UserApiController {
	
	@Autowired MUserRepo mnUserRepo;
	
	@Autowired DesignationRepo desigRepo;
	
	@Autowired UserTypeRepo userTypeRepo;
	
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
	@RequestMapping(value = { "/getDesignations" }, method = RequestMethod.GET)
	public @ResponseBody List<Designation> getDesignations(){
		
		List<Designation> list = new ArrayList<Designation>();
		try {
			list = desigRepo.findByDelStatusOrderByDesignationIdDesc(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	/********************************************************************************/
	
	@RequestMapping(value = { "/getAllUserTypes" }, method = RequestMethod.GET)
	public @ResponseBody List<UserType> getAllUserTypes(){
		
		List<UserType> list = new ArrayList<UserType>();
		try {
			list = userTypeRepo.findByDelStatus(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
}
