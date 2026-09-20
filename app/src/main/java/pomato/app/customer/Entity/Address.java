package pomato.app.customer.Entity;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;

@Entity
@Audited
@Table(name = "address")
public class Address extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "zipcode", nullable = false, length = 10)
    private String zipcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false)
    private AddressType addressType;

    @Column(
            name = "latitude",
            nullable = false,
            precision = 9,
            scale = 6
    )
    private BigDecimal latitude;

    @Column(
            name = "longitude",
            nullable = false,
            precision = 9,
            scale = 6
    )
    private BigDecimal longitude;


    // Required by JPA
    protected Address() {
    }


    // Constructor for creating a new Address
    public Address(
            Users user,
            String street,
            String city,
            String state,
            String country,
            String zipcode,
            AddressType addressType,
            BigDecimal latitude,
            BigDecimal longitude
    ) {
        this.user = user;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.addressType = addressType;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }


    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }


    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }


    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}