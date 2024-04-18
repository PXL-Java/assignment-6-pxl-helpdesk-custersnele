package be.pxl.helpdesk.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users_table") // error message!
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
	private String email;

	@Enumerated(value = EnumType.STRING)
    private UserRole role;
    private boolean locked;

	public User() {
		// JPA only
	}

	public User(String username, String email, UserRole role) {
		this.username = username;
		this.email = email;
		this.role = role;
	}

	public User(String username, String email) {
		this(username, email, UserRole.USER);
	}

	public long getId()
    {
        return this.id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

	public UserRole getRole() {
		return role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		User user = (User) o;

		return id == user.id;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
    public String toString()
    {
        return this.username;
    }

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getEmail() {
		return email;
	}
}
