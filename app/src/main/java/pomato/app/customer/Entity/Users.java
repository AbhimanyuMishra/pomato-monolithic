package pomato.app.customer.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


