package com.adobe.communities.ugc.management.commons.deleteoperation;

import com.adobe.communities.ugc.management.components.badges.BadgingComponentUserUgc;
import com.adobe.cq.social.badging.api.BadgingService;
import com.adobe.cq.social.scf.OperationException;
import com.adobe.cq.social.tally.client.endpoints.TallyOperationsService;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

/*
    Currently we have api to
 */
public class BadgeDeleteOperation implements DeleteOperation<BadgingComponentUserUgc> {

    BadgingService badgingService;

    public BadgeDeleteOperation(BadgingService badgingService){
        this.badgingService = badgingService;
    }

    public void delete(ResourceResolver resourceResolver, Resource resource, Session session, String authorizableId) throws OperationException {

        try {
            badgingService.deleteBadge(resourceResolver, authorizableId, null, null, resource.getPath(), true );
        } catch (RepositoryException e) {
            throw new OperationException(e, 0);
        } catch (PersistenceException e) {
            throw new OperationException(e, 0);
        }
    }
}
