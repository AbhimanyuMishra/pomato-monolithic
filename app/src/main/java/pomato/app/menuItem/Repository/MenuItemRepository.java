package pomato.app.menuItem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pomato.app.menuItem.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

}
