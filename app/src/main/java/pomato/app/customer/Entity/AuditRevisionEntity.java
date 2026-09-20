package pomato.app.customer.Entity;

import jakarta.persistence.*;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import pomato.app.customer.Utils.AuditRevisionListener;

@Entity
@Table(name = "audit_revision")
@RevisionEntity(AuditRevisionListener.class)
public class AuditRevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    @Column(name = "revision_id")
    private Long revisionId;

    @RevisionTimestamp
    @Column(name = "revision_timestamp")
    private Long revisionTimestamp;

    @Column(name = "changed_by")
    private String changedBy;


    // Required by JPA
    protected AuditRevisionEntity() {
    }



    public Long getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(Long revisionId) {
        this.revisionId = revisionId;
    }


    public Long getRevisionTimestamp() {
        return revisionTimestamp;
    }

    public void setRevisionTimestamp(Long revisionTimestamp) {
        this.revisionTimestamp = revisionTimestamp;
    }


    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }
}