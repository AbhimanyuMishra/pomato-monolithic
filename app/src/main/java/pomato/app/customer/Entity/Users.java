package pomato.app.customer.Entity;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Audited
@Table(name = "users")
public class Users extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_id", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    // Compatibility with the existing users.role column; authorization uses roles.
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRoles legacyRole;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Set<UserRoles> roles = new HashSet<>();

    @Column(name = "DOB", nullable = false)
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public Users() {
    }

    public Users(String firstName, String lastName, String email, String password,
                 String phoneNumber, UserRoles role, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        addRole(role);
        this.legacyRole = role;
    }

    @PrePersist
    @PreUpdate
    private void keepLegacyRoleColumnPopulated() {
        if (legacyRole == null && !roles.isEmpty()) {
            legacyRole = roles.iterator().next();
        }
    }

    @PostLoad
    private void includeLegacyRoleForExistingAccounts() {
        if (legacyRole != null && roles.isEmpty()) {
            roles.add(legacyRole);
        }
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Deprecated
    public UserRoles getRole() { return legacyRole; }

    @Deprecated
    public void setRole(UserRoles role) {
        legacyRole = role;
        if (role != null) roles.add(role);
    }

    public Set<UserRoles> getRoles() {
        if (roles.isEmpty() && legacyRole != null) roles.add(legacyRole);
        return roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles == null ? new HashSet<>() : new HashSet<>(roles);
        if (legacyRole == null && !this.roles.isEmpty()) {
            legacyRole = this.roles.iterator().next();
        }
    }

    public void addRole(UserRoles role) {
        if (role == null) return;
        if (roles.isEmpty() && legacyRole != null) roles.add(legacyRole);
        roles.add(role);
        if (legacyRole == null) legacyRole = role;
    }

    public List<Address> getAddresses() { return addresses; }
    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
}
