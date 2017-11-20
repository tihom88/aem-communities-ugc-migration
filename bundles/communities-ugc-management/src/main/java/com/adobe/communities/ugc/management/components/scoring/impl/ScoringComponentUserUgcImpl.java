package com.adobe.communities.ugc.management.components.scoring.impl;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgc;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.SrpDeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.SrpOperations;
import com.adobe.communities.ugc.management.components.scoring.ScoringComponentUserUgc;
import com.adobe.cq.social.activitystreams.api.SocialActivityManager;
import com.adobe.cq.social.scoring.api.ScoringConstants;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.ugc.api.UgcFilter;
import com.adobe.cq.social.ugc.api.UgcSearch;
import org.apache.felix.scr.annotations.Activate;
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
public class ScoringComponentUserUgcImpl extends DefaultComponentUserUgc implements ScoringComponentUserUgc {

    @Reference
    private UgcSearch ugcSearch;

    @Reference
    private SrpOperations srpOperations;

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    @Reference
    SocialActivityManager socialActivityManager;

    @Activate
    public void init() {
        setUgcSearch(ugcSearch);
        setSocialResourceUtilities(socialResourceUtilities);
    }

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String> filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, ScoringConstants.RESOURCE_TYPE_SCORE);
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.USERIDENTIFIER;
    }

    public DeleteOperation getOperations() {
        return new SrpDeleteOperation(srpOperations);
    }

}
