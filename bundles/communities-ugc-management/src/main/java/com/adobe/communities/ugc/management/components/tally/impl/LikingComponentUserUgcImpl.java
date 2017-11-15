package com.adobe.communities.ugc.management.components.tally.impl;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgc;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.TallyDeleteOperation;
import com.adobe.communities.ugc.management.components.tally.LikingComponentUserUgc;
import com.adobe.cq.social.tally.client.api.LikingSocialComponent;
import com.adobe.cq.social.tally.client.endpoints.TallyOperationsService;
import com.adobe.cq.social.ugc.api.UgcFilter;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */
@Service
@Component
public class LikingComponentUserUgcImpl extends DefaultComponentUserUgc implements LikingComponentUserUgc{

    @Reference
    TallyOperationsService tallyOperationsService;

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String>  filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, LikingSocialComponent.LIKING_RESOURCE_TYPE);
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.USERIDENTIFIER;
    }

    public DeleteOperation getOperations() {
        return new TallyDeleteOperation(tallyOperationsService);
    }

    @Override
    public UgcFilter getUgcFilter(String user) {
        return super.getUgcFilter(user);
    }
}
