package pomato.app.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pomato.app.customer.Entity.Users;

public interface CustomerRepository extends JpaRepository<Users, Long> {
}
