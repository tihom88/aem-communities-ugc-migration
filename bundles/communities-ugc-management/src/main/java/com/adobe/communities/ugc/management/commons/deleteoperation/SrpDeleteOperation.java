package com.adobe.communities.ugc.management.commons.deleteoperation;

import com.adobe.communities.ugc.management.commons.SrpComponentUserUgc;
import com.adobe.communities.ugc.management.commons.TallyComponentUserUgc;
import com.adobe.cq.social.scf.OperationException;
import com.adobe.cq.social.tally.client.endpoints.TallyOperationsService;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.Session;

public class SrpDeleteOperation implements DeleteOperation<SrpComponentUserUgc> {

    SrpOperations srpOperations;

    public SrpDeleteOperation(SrpOperations srpOperations){
        this.srpOperations = srpOperations;
    }
    public void delete(ResourceResolver resourceResolver, Resource resource, Session session) throws OperationException{
        try {
            srpOperations.delete(resourceResolver,resource, session);
        } catch (PersistenceException e) {
            throw new OperationException(e, 0);
        }
    }
}
