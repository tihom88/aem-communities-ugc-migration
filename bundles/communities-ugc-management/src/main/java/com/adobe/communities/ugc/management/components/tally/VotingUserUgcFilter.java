package com.adobe.communities.ugc.management.components.tally;

import com.adobe.communities.ugc.management.commons.DefaultUserUgcFilter;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.cq.social.qna.client.api.QnaPost;
import com.adobe.cq.social.tally.TallyConstants;
import com.adobe.cq.social.tally.client.api.VotingSocialComponent;
import com.adobe.cq.social.ugc.api.UgcFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */
public class VotingUserUgcFilter extends DefaultUserUgcFilter {

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String>  filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, VotingSocialComponent.VOTING_RESOURCE_TYPE);
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
