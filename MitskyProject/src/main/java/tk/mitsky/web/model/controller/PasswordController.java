package tk.mitsky.web.model.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mitsky.web.dao.UserDAO;
import tk.mitsky.web.model.Password;
import tk.mitsky.web.model.User;
import tk.mitsky.web.util.SecurityUtils;

@Controller
public class PasswordController {

	@Autowired
	UserDAO userDAO;
	
	@ResponseBody
	@PostMapping("login")
	private boolean loginWithEmailAndPassword(@RequestParam String email, @RequestParam String password) {

		boolean result = false;
		
		try {
			if(!StringUtils.isEmpty(email) && !StringUtils.isEmpty(password)) {
				User user = userDAO.findByEmail(email);
				
				if(user != null && !user.getPasswords().isEmpty()) {
					
					Password pass = user.getPasswords().get(0);
					String hashPassword = SecurityUtils.getSecurePassword(password, pass.getSalt());
					
					if(pass.getPassword().equals(hashPassword)) {
						result = true;
					}
				}
			}
			
		} catch (Exception e) {
			result = false;
		}
		
		return result;
	}

}
