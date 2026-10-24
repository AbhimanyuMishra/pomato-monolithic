package pomato.app.restaurant.entities;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import pomato.app.customer.Entity.Address;
import pomato.app.customer.Entity.AuditingEntity;
import pomato.app.menu.entity.Menu;

import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
@Table(name = "restaurants")
public class Restaurant extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "restaurant_phoneno", nullable = false)
    private String phoneno;

    @Column(name = "restaurant_email", nullable = false, unique = true)
    private String email;

    @OneToMany(
            mappedBy = "restaurant",
            fetch = FetchType.LAZY
    )
    private List<Menu> menus = new ArrayList<>();

    protected Restaurant() {
        // Required by JPA
    }

    public Restaurant(
            Address address,
            String email,
            List<Menu> menus,
            String phoneno,
            String restaurantName
    ) {
        this.address = address;
        this.email = email;
        this.menus = menus;
        this.phoneno = phoneno;
        this.restaurantName = restaurantName;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}