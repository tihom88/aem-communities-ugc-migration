package com.adobe.communities.ugc.management.components.activitystreams;

import com.adobe.communities.ugc.management.commons.ComponentEnum;
import com.adobe.communities.ugc.management.commons.DefaultUserUgcFilter;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.cq.social.activitystreams.api.SocialActivity;
import com.adobe.cq.social.activitystreams.api.SocialActivityEventConstants;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.journal.client.api.Journal;
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
import java.util.List;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */

@Service
@Component
public class ActivityStreamsUserUgcFilter extends DefaultUserUgcFilter {

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

    public CommentOperations getCommentOperations() {
        return null;
    }

    @Override
    public UgcFilter getUgcFilter(String user) {
        return super.getUgcFilter(user);
    }



    public boolean deleteUserUgc(ResourceResolver resourceResolver, String userId) throws OperationException {

        SearchResults<Resource> searchResults = getUserUgc(resourceResolver, userId);
        final Session session = resourceResolver.adaptTo(Session.class);
        deleteResources(resourceResolver, searchResults, session);
        return true;
    }

    private void deleteResources(ResourceResolver resourceResolver, SearchResults<Resource> resources, Session session) throws OperationException {
        try {

            for (Resource resource : resources.getResults()) {
                final SocialResourceConfiguration storageConfig = socialResourceUtilities.getStorageConfig(resource); //socialUtils.getStorageConfig(resource);
                final SocialResourceProvider srp = socialResourceUtilities.getSocialResourceProvider(resource);
                srp.setConfig(storageConfig);
                boolean isUgcPresent = srp.getResource(resourceResolver, resource.getPath()) != null ? true : false;
                if(isUgcPresent){
                    srp.delete(resourceResolver, resource.getPath());
                    session.save();
                }
            }
        } catch (PersistenceException e) {
            throw new OperationException(e, 0);
        } catch (RepositoryException e) {
            throw  new OperationException(e, 0);
        }
    }

}
