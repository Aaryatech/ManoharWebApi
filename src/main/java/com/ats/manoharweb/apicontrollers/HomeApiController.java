package com.ats.manoharweb.apicontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.manoharweb.models.ErrorMessage;
import com.ats.manoharweb.models.LoginResponse;
import com.ats.manoharweb.models.MUser;
import com.ats.manoharweb.repo.MUserRepo;

@RestController
public class HomeApiController {
	@Autowired MUserRepo userRepository;
	
	String jsonUser = "{}";
	MUser user = null;
	ErrorMessage errorMessage;
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse loginUser(@RequestParam("username") String username,
			@RequestParam("password") String password,@RequestParam("type") int type) {

		String dbUsername = null;
		String dbPassword = null;
		LoginResponse loginResponse = null;
		try {
			user = userRepository.getUserCradentials(username, password, type);
			dbUsername = user.getUserMobileNo();
			dbPassword = user.getPassword();
		} catch (Exception e) {
			loginResponse = new LoginResponse();
			errorMessage = new ErrorMessage();
			errorMessage.setError(true);
			errorMessage.setMessage("User is not registered");

			loginResponse.setErrorMessage(errorMessage);
			loginResponse.setUser(user);

		}
		try {
			if (user == null || username == null || username.equalsIgnoreCase("")) {

				loginResponse = new LoginResponse();

				errorMessage = new ErrorMessage();
				errorMessage.setError(true);
				errorMessage.setMessage("User is not registered");

				loginResponse.setErrorMessage(errorMessage);
				loginResponse.setUser(user);

			} else if (password == null || password.equalsIgnoreCase("")) {

				loginResponse = new LoginResponse();
				errorMessage = new ErrorMessage();

				errorMessage.setError(true);
				errorMessage.setMessage("Please enter Password");
				loginResponse.setErrorMessage(errorMessage);
				loginResponse.setUser(user);

			} else if (dbUsername.equals(username) && dbPassword.equals(password)) {

				loginResponse = new LoginResponse();
				errorMessage = new ErrorMessage();

				errorMessage.setError(false);
				errorMessage.setMessage("User Logged in Successfully");
				loginResponse.setErrorMessage(errorMessage);
				loginResponse.setUser(user);

			} else if (dbUsername.equals(username) && dbPassword != password) {

				loginResponse = new LoginResponse();
				errorMessage = new ErrorMessage();

				errorMessage.setError(true);
				errorMessage.setMessage("Invalid Password");
				loginResponse.setErrorMessage(errorMessage);
				loginResponse.setUser(user);

			}
		} catch (Exception e) {

			loginResponse = new LoginResponse();
			errorMessage = new ErrorMessage();

			errorMessage.setError(true);
			errorMessage.setMessage("User is not registered");
			loginResponse.setErrorMessage(errorMessage);
			loginResponse.setUser(user);

		}
		return loginResponse;

	}
}
