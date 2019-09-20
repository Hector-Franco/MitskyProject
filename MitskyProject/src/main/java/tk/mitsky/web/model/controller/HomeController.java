package tk.mitsky.web.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class HomeController {

	
	@GetMapping("home")
	public String createGET(Model model) {

		return "home/home";
	}
	
	
	/*@GetMapping("/**")
	public String getErrorPage() {
		return "error/error";
	}


	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "error";
	} */
	
	
}
