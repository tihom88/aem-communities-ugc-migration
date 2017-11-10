package com.adobe.communities.ugc.management.components.ideation;

import com.adobe.communities.ugc.management.commons.DefaultUserUgcFilter;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.cq.social.forum.client.api.Forum;
import com.adobe.cq.social.ideation.client.api.Ideation;
import com.adobe.cq.social.ugc.api.UgcFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */
public class IdeationIdeaUserUgcFilter extends DefaultUserUgcFilter {

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String>  filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, Ideation.RESOURCE_TYPE_IDEA);
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.AUTHORIZABLE_ID;
    }

    @Override
    public UgcFilter getUgcFilter(String user) {
        return super.getUgcFilter(user);
    }
}
