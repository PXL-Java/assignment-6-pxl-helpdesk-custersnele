package be.pxl.helpdesk.builder;

import be.pxl.helpdesk.domain.User;
import be.pxl.helpdesk.domain.UserRole;

public final class UserBuilder {

	private boolean locked = false;
	private String username = "user1";
	private String email = "user1@pxl.be";
	private UserRole role = UserRole.USER;

	private UserBuilder() {}

	public static UserBuilder anUser() {return new UserBuilder();}

	public UserBuilder withLocked(boolean locked) {
		this.locked = locked;
		return this;
	}

	public UserBuilder withUsername(String username) {
		this.username = username;
		return this;
	}

	public UserBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder withRole(UserRole role) {
		this.role = role;
		return this;
	}

	public User build() {
		User user = new User(username, email, role);
		user.setLocked(locked);
		return user;
	}
}
