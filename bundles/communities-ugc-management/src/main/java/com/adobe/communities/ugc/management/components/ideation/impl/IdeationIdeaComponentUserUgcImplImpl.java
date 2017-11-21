package com.adobe.communities.ugc.management.components.ideation.impl;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgcImpl;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.deleteoperation.impl.CommentDeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.components.ideation.IdeationIdeaComponentUserUgc;
import com.adobe.cq.social.ideation.client.api.Ideation;
import com.adobe.cq.social.ideation.client.endpoints.IdeationOperations;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
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
@Component
@Service
public class IdeationIdeaComponentUserUgcImplImpl extends DefaultComponentUserUgcImpl implements IdeationIdeaComponentUserUgc{

    @Reference
    IdeationOperations ideationOperations;

    @Reference
    private UgcSearch ugcSearch;

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    @Activate
    public void init() {
        setUgcSearch(ugcSearch);
        setSocialResourceUtilities(socialResourceUtilities);
    }


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

    public DeleteOperation getOperations() {
        return new CommentDeleteOperation(ideationOperations);
    }
}
