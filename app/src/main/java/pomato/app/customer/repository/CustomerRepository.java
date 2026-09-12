package pomato.app.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pomato.app.customer.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
