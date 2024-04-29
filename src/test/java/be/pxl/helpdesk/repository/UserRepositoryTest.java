package be.pxl.helpdesk.repository;

import be.pxl.helpdesk.builder.UserBuilder;
import be.pxl.helpdesk.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {
	@PersistenceContext // ?? Autowired ook ok
	private EntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	private User user1 = UserBuilder.anUser().withEmail("test1@pxl.be").build();
	private User user2 = UserBuilder.anUser().withEmail("test2@pxl.be").build();

	@BeforeEach
	public void init() {
		userRepository.saveAll(Arrays.asList(user1, user2));
		entityManager.flush();
		entityManager.clear();
	}

	@Test
	public void testRetrieveUserByEmail() {
		Optional<User> userByEmail = userRepository.findUserByEmail("test2@pxl.be");

		Assertions.assertTrue(userByEmail.isPresent());
		Assertions.assertEquals("test2@pxl.be", userByEmail.get().getEmail());
	}

	@Test
	public void testFindUserByEmailReturnsOptionalEmpty_WhenEmailNotExists() {
		Optional<User> userByEmail = userRepository.findUserByEmail("test3@pxl.be");

		Assertions.assertTrue(userByEmail.isEmpty());
	}

}
