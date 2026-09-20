package pomato.app.customer.Utils;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pomato.app.customer.Entity.AuditRevisionEntity;

public class AuditRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {

        AuditRevisionEntity revision =
                (AuditRevisionEntity) revisionEntity;

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication != null &&
                authentication.isAuthenticated() &&
                !"anonymousUser".equals(authentication.getName())) {

            revision.setChangedBy(authentication.getName());
        } else {
            revision.setChangedBy("SYSTEM");
        }
    }
}