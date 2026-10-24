package pomato.app.menu.entity;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import pomato.app.customer.Entity.AuditingEntity;
import pomato.app.restaurant.entities.Restaurant;
import pomato.app.menuItem.entity.MenuItem;

import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
@Table(name = "menu")
public class Menu extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(
            mappedBy = "menu",
            fetch = FetchType.LAZY
    )
    private List<MenuItem> menuItems = new ArrayList<>();

    protected Menu() {
        // Required by JPA
    }

    public Menu(
            List<MenuItem> menuItems,
            Restaurant restaurant
    ) {
        this.menuItems = menuItems;
        this.restaurant = restaurant;
    }

    public Long getMenuId() {
        return menuId;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}