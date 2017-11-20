package com.adobe.communities.ugc.management.commons.deleteoperation;

import com.adobe.communities.ugc.management.commons.ComponentUserUgc;
import com.adobe.cq.social.scf.OperationException;
import com.adobe.cq.social.tally.client.endpoints.TallyOperationsService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.Session;

public class TallyDeleteOperation implements DeleteOperation {

    TallyOperationsService tallyOperationsService;
    String tallyType;

    public TallyDeleteOperation(TallyOperationsService tallyOperationsService, String tallyType){
        this.tallyOperationsService = tallyOperationsService;
        this.tallyType = tallyType;
    }
    public void delete(ResourceResolver resourceResolver, Resource resource, Session session, String authorizableId) throws OperationException {

        tallyOperationsService.removeCurrentUserResponse(resource.getParent(), this.tallyType);
    }
}
