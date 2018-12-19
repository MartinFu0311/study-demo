package demo.martin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.martin.domain.User;

@RestController
@RequestMapping("/")
public class HomeController {
	
	@GetMapping("/user")
	public User getUser() {
		User user = new User();
		user.setId(1L);
		user.setName("Martin Fu");
		
		return user;
	}
	
}
