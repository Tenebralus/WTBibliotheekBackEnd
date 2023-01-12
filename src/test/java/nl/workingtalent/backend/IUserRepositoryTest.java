package nl.workingtalent.backend;

import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class IUserRepositoryTest {

    @Autowired
    private IUserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmailAddress("basdoddema12@gmail.com");
        user.setPassword("test");
        user.setFirstName("Bas");
        user.setLastName("Doddema");
        user.setDateAccountCreated(LocalDateTime.now());
        user.setActive(true);

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmailAddress()).isEqualTo(user.getEmailAddress());
    }

    @Test
    public void testFindUserByEmail() {
        String email = "basdoddema02@gmail.com";

        User user = repo.findByEmailAddress(email);

        assertThat(user).isNotNull();
    }
}
