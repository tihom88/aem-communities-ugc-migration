package com.adobe.communities.ugc.management.commons.deleteoperation;

import com.adobe.communities.ugc.management.commons.ComponentUserUgc;
import com.adobe.communities.ugc.management.commons.TallyComponentUserUgc;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.scf.OperationException;
import com.adobe.cq.social.tally.client.endpoints.TallyOperationsService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.Session;

public class TallyDeleteOperation implements DeleteOperation<TallyComponentUserUgc> {

    TallyOperationsService tallyOperationsService;

    public TallyDeleteOperation(TallyOperationsService tallyOperationsService){
        this.tallyOperationsService = tallyOperationsService;
    }
    public void delete(ResourceResolver resourceResolver, Resource resource, Session session) throws OperationException {
        tallyOperationsService.removeCurrentUserResponse(resource, resource.getPath());
    }
}
