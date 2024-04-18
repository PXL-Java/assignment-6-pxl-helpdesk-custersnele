package be.pxl.helpdesk.api;

import be.pxl.helpdesk.api.data.UserDTO;
import be.pxl.helpdesk.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserDTO> getAllUsers() {
		return userService.getUsers();
	}
}
