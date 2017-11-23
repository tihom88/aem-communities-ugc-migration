package com.adobe.communities.ugc.management.commons.deleteoperation.impl;

import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.commons.srp.operations.SrpOperations;
import com.adobe.cq.social.scf.OperationException;
import com.adobe.cq.social.tally.client.endpoints.TallyOperationsService;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.Session;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TallyDeleteOperation implements DeleteOperation {

    private static final String UNSET = "unset";
    TallyOperationsService tallyOperationsService;
    SrpOperations srpOperations;
    String tallyType;

    public TallyDeleteOperation(SrpOperations srpOperations, TallyOperationsService tallyOperationsService, String tallyType){
        this.tallyOperationsService = tallyOperationsService;
        this.tallyType = tallyType;
        this.srpOperations = srpOperations;
    }
    public void delete(ResourceResolver resourceResolver, Resource resource, Session session, String authorizableId) throws OperationException {
        Map<String, Object> conditionals = new HashMap<String, Object>();
        conditionals.put("ignoreEventPost", true);
        tallyOperationsService.setTallyResponse(resource.getParent(), authorizableId, session, UNSET, tallyType, Collections.<String, Object> emptyMap(), conditionals);
        try {
            srpOperations.delete(resourceResolver, resource, session);
        } catch (PersistenceException e) {
            throw new OperationException(e, 0);
        }
    }
}
