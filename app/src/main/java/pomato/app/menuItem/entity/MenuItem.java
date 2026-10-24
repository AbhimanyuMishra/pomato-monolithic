package pomato.app.menuItem.entity;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import pomato.app.customer.Entity.AuditingEntity;
import pomato.app.menu.entity.Menu;

import java.math.BigDecimal;

@Entity
@Audited
@Table(name = "menu_items")
public class MenuItem extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long menuItemId;

    @Column(name = "item_name", nullable = false)
    private String menuItemName;

    @Column(name = "description")
    private String menuItemDescription;

    @Column(name = "item_price", nullable = false)
    private BigDecimal menuItemPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    // JPA requires a no-argument constructor
    protected MenuItem() {
    }

    public MenuItem(
            String menuItemName,
            String menuItemDescription,
            BigDecimal menuItemPrice,
            Menu menu
    ) {
        this.menuItemName = menuItemName;
        this.menuItemDescription = menuItemDescription;
        this.menuItemPrice = menuItemPrice;
        this.menu = menu;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getMenuItemDescription() {
        return menuItemDescription;
    }

    public void setMenuItemDescription(String menuItemDescription) {
        this.menuItemDescription = menuItemDescription;
    }

    public BigDecimal getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemPrice(BigDecimal menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}