package com.adobe.communities.ugc.management.components.tally;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgc;
import com.adobe.communities.ugc.management.commons.DefaultSrpComponentUserUgc;
import com.adobe.communities.ugc.management.commons.DefaultTallyComponentUserUgc;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.tally.client.api.LikingSocialComponent;
import com.adobe.cq.social.tally.client.endpoints.TallyOperationsService;
import com.adobe.cq.social.ugc.api.UgcFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */
public class LikingComponentUserUgc extends DefaultTallyComponentUserUgc {

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

    @Override
    public UgcFilter getUgcFilter(String user) {
        return super.getUgcFilter(user);
    }
}
