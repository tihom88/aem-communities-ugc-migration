package com.adobe.communities.ugc.management.commons;

import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.cq.social.scf.OperationException;
import com.adobe.cq.social.tally.client.endpoints.TallyOperationsService;
import com.adobe.cq.social.ugc.api.SearchResults;
import com.adobe.cq.social.ugc.api.UgcFilter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

/**
 * Created by mokatari on 10/12/17.
 */
public interface TallyComponentUserUgc {
    DeleteOperation getOperations();
    UgcFilter getUgcFilter(ResourceResolver resourceResolver, String userId);
    SearchResults<Resource> getUserUgc(ResourceResolver resourceResolver, String userId);
    public boolean deleteUserUgc(ResourceResolver resourceResolver, String userId) throws OperationException;
}
