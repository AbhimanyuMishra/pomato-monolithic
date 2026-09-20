package pomato.app.customer.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingEntity {
    @CreatedDate
    @Column(name="Created_At",nullable=false)
    private Instant createdAt;
    @CreatedBy
    @Column(name="created_By",nullable=false)
    private String createdBy;
    @Column(name="Updated_At",nullable=false)
    @LastModifiedDate
    private Instant updatedAt;
    @Column(name="Updated_By",nullable=false)
    @LastModifiedBy
    private String updatedBy;
    // Required by JPA
    protected AuditingEntity() {
    }

    public AuditingEntity(String updatedBy, Instant updatedAt, String createdBy, Instant createdAt) {
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
