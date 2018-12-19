package demo.martin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.martin.domain.User;
import demo.martin.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/addUser")
	public void addNewUser() {
		User user = new User();
		user.setName("Martin_Fu");
		
		userRepository.save(user);
	}
}
