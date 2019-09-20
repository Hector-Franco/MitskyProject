package tk.mitsky.web.model.controller;

import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mitsky.web.dao.UserDAO;
import tk.mitsky.web.model.User;
import tk.mitsky.web.model.Password;
import tk.mitsky.web.util.SecurityUtils;

@Controller
@RequestMapping(value = "/")
public class UserController {

	@Autowired
	UserDAO userDAO;

	@ResponseBody
	@RequestMapping("users")
	public List<User> createRestGET() {

		List<User> users = userDAO.findAll();

		return users;
	}
	
	@ResponseBody
	@GetMapping("users/user")
	public User findUserByEmail(@RequestParam String email) throws Exception {

		if (!StringUtils.isEmpty(email)) {
			User user = userDAO.findByEmail(email);
			if (user != null)
				return user;
			else
				throw new Exception("Email no encontrado: " + email);

		} else {
			throw new Exception("Por favor ingresa el E-MAIL del usuario");
		}
	}

	@GetMapping("users/user/list")
	public String findUserByEmailGET(@RequestParam String email, Model model) throws Exception {

		model.addAttribute("list", Arrays.asList(userDAO.findByEmail(email)));
		return "users/users";

	}

	@RequestMapping("users/all")
	public String createGET(Model model) {

		model.addAttribute("list", userDAO.findAll());

		return "users/users";
	}

	@ResponseBody
	@PostMapping("users/create")
	public User createNewUserPost(@RequestParam String email, @RequestParam String name, @RequestParam String lastname,
			@RequestParam Date birthdate, @RequestParam String password) throws Exception {

		if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(name) && !StringUtils.isEmpty(lastname)
				&& !StringUtils.isEmpty(birthdate.toString()) && !StringUtils.isEmpty(password)) {

			if (userDAO.findByEmail(email) != null) {
				throw new Exception("El e-mail: " + email + " ya ha sido registrado anteriormente");
			}

			// CREATE A NEW USER OBJECT
			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setLastname(lastname);
			user.setBirthdate(birthdate);

			// CREATE A NEW PASSWORD OBJECT
			// ENLAZAR USER CON PASSWORD
			Password newPassword = new Password();
			newPassword.setSalt(SecurityUtils.generateRandomString());
			newPassword.setPassword(SecurityUtils.getSecurePassword(password, newPassword.getSalt()));
			newPassword.setUser(user);
			newPassword.setCreationInstant(Instant.now());

			user.setPasswords(Arrays.asList(newPassword));

			return userDAO.save(user);

		} else {
			throw new Exception("Debe llenar todos los campos");
		}
	}

	@ResponseBody
	@PostMapping("users/reset-password")
	public User resetPasswordPost(@RequestParam String email, @RequestParam String oldPassword,
			@RequestParam String newPassword) throws Exception {

		if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(oldPassword) && !StringUtils.isEmpty(newPassword)) {

			User user = userDAO.findByEmail(email);

			if (user == null) {
				throw new Exception("El email o la contraseña no coinciden");
			}

			Password pass = user.getPasswords().get(0);
			String hashPassword = SecurityUtils.getSecurePassword(oldPassword, pass.getSalt());

			if (!pass.getPassword().equals(hashPassword)) {
				throw new Exception("Contraseña incorrecta");
			}

			Password password = new Password();
			password.setSalt(SecurityUtils.generateRandomString());
			password.setPassword(SecurityUtils.getSecurePassword(newPassword, password.getSalt()));
			password.setUser(user);
			password.setCreationInstant(Instant.now());

			user.getPasswords().add(password);

			return userDAO.save(user);

		} else {
			throw new Exception("Faltan datos obligatorios");
		}
	}

	@ResponseBody
	@DeleteMapping("users/delete-user")
	public String deleteUserByEmail(@RequestParam String email) throws Exception {

		if (!StringUtils.isEmpty(email)) {

			User user = userDAO.findByEmail(email);

			if (user == null) {
				throw new Exception("Usuario con e-mail: " + email + " no encontrado");
			}

			userDAO.deleteById(user.getId());
			return "Usuario con e-mail: " + email + " eliminado";

		} else {
			throw new Exception("Debe ingresar un E-mail");
		}

	}

	@ResponseBody
	@PutMapping("users/change-email")
	public String changeUserEmail(@RequestParam String oldEmail, @RequestParam String password,
			@RequestParam String newEmail) throws Exception {

		if (!StringUtils.isEmpty(oldEmail) && !StringUtils.isEmpty(password) && !StringUtils.isEmpty(newEmail)) {

			if (userDAO.findByEmail(newEmail) != null) {
				
				throw new Exception("El e-mail: " + newEmail + " ya ha sido registrado anteriormente");
				
			} else {

				if (!oldEmail.equalsIgnoreCase(newEmail)) {
					User user = userDAO.findByEmail(oldEmail);

					if (user == null)
						throw new Exception("El email o la contraseña no coinciden");

					
					user.setEmail(newEmail);
					userDAO.save(user);

					return "Email cambiado exitosamente";

				} else {
					
					throw new Exception("No puede modificar el email, con uno ya registado a su nombre");
				}

			}

		} else {
			throw new Exception("Faltan datos obligatorios");
		}

	}

}
