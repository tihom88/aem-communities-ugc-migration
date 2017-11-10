package com.adobe.communities.ugc.management.commons;

import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.journal.client.endpoints.JournalOperations;
import com.adobe.cq.social.scf.OperationException;
import com.adobe.cq.social.srp.SocialResourceProvider;
import com.adobe.cq.social.srp.config.SocialResourceConfiguration;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.ugc.api.*;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.Map;

/**
 * Created by mokatari on 10/12/17.
 */
public abstract class DefaultUserUgcFilter implements UserUgcFilter {

    @Reference
    UgcSearch ugcSearch;

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    public abstract Map<String, String> getComponentfilters();

    public abstract String getUserIdentifierKey();

    public abstract CommentOperations getCommentOperations();

    public UgcFilter getUgcFilter(String user) {

        UgcFilter ugcShowcaseFilter = new UgcFilter();
        ConstraintGroup resourceGroupConstraint = new ConstraintGroup(Operator.And);
        Map<String, String> resourceTypeList = getComponentfilters();
        for (Map.Entry<String, String> entry : resourceTypeList.entrySet()) {
            resourceGroupConstraint.addConstraint(new ValueConstraint<String>(entry.getKey(), entry.getValue(), ComparisonType.Equals,
                    Operator.Or));
        }
        ConstraintGroup userGroup = new ConstraintGroup(Operator.And);
        String userIdentifierKey = getUserIdentifierKey();
        userGroup.addConstraint(new ValueConstraint<String>(userIdentifierKey, user, ComparisonType.Equals,
                Operator.Or));
        ConstraintGroup andcons = new ConstraintGroup(Operator.Or); // doesn't matter
        andcons.addConstraint(resourceGroupConstraint);
        andcons.addConstraint(userGroup);
        ugcShowcaseFilter.and(andcons);
        return ugcShowcaseFilter;
    }

    public SearchResults<Resource> getUserUgc(ResourceResolver resourceResolver, String userId) {

        SearchResults<Resource> results;
        try {
            // Max value need to be checked (MAX_VALUE can't be used, throwing out of range error )
            results = ugcSearch.find(null, resourceResolver, getUgcFilter(userId), 0, 100000, false);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        return results;
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
                if (isUgcPresent) {
                    getCommentOperations().delete(resource, session);
                    session.save();
                }
            }
        } catch (RepositoryException e) {
            throw new OperationException(e, 0);
        }

    }


}

