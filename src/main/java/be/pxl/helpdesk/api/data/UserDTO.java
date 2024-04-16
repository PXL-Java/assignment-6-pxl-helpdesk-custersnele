package be.pxl.helpdesk.api.data;

import be.pxl.helpdesk.domain.User;
import be.pxl.helpdesk.domain.UserRole;

public class UserDTO {
	private final String username;
	private final String email;
	private final UserRole role;
	private final boolean locked;

	public UserDTO(User user) {
		this.username = user.getUsername();
		this.role = user.getRole();
		this.email = user.getEmail();
		this.locked = user.isLocked();
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public boolean isLocked() {
		return locked;
	}

	public UserRole getRole() {
		return role;
	}
}
