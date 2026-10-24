package pomato.app.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pomato.app.customer.Entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailIgnoreCase(String email);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
