package com.adobe.communities.ugc.management.components.activitystreams.impl;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgc;
import com.adobe.communities.ugc.management.commons.DefaultSrpComponentUserUgc;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.SrpComponentUserUgc;
import com.adobe.communities.ugc.management.components.activitystreams.ActivityStreamsComponentUserUgc;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.scf.OperationException;
import com.adobe.cq.social.srp.SocialResourceProvider;
import com.adobe.cq.social.srp.config.SocialResourceConfiguration;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.ugc.api.SearchResults;
import com.adobe.cq.social.ugc.api.UgcFilter;
import com.adobe.cq.social.ugc.api.UgcSearch;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */

@Service
@Component
public class ActivityStreamsComponentUserUgcImpl extends DefaultSrpComponentUserUgc implements ActivityStreamsComponentUserUgc {

    @Reference
    UgcSearch ugcSearch;

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String> filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, "social/activitystreams/components/activity");
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.ACTOR_ID;
    }

//    public CommentOperations getCommentOperations() {
//        return null;
//    }

    @Override
    public UgcFilter getUgcFilter(String user) {
        return super.getUgcFilter(user);
    }




}
