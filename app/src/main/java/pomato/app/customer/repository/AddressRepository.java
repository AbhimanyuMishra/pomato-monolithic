package pomato.app.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pomato.app.customer.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
