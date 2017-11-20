package com.adobe.communities.ugc.management.components.badges.impl;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgc;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.deleteoperation.BadgeDeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.SrpOperations;
import com.adobe.communities.ugc.management.components.badges.BadgingComponentUserUgc;
import com.adobe.cq.social.badging.api.BadgingService;
import com.adobe.cq.social.scoring.api.ScoringService;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.ugc.api.*;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.RepositoryException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */

@Service
@Component
public class BadgingComponentUserUgcImpl extends DefaultComponentUserUgc implements BadgingComponentUserUgc {

    @Reference
    private UgcSearch ugcSearch;

    @Reference
    private SrpOperations srpOperations;

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    @Reference
    private BadgingService badgingService;

    @Activate
    public void init() {
        setUgcSearch(ugcSearch);
        setSocialResourceUtilities(socialResourceUtilities);
    }

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String> filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, BadgingService.RESOURCE_TYPE_BADGE);
        return filters;
    }

    @Override
    public UgcFilter getUgcFilter(String user) {
        UgcFilter ugcShowcaseFilter = new UgcFilter();
        ConstraintGroup resourceGroupConstraint = new ConstraintGroup(Operator.And);
        Map<String, String> resourceTypeList = getComponentfilters();
        for (Map.Entry<String, String> entry : resourceTypeList.entrySet()) {
            resourceGroupConstraint.addConstraint(new ValueConstraint<String>(entry.getKey(), entry.getValue(), ComparisonType.Equals,
                    Operator.Or));
        }
        ConstraintGroup userGroup = new ConstraintGroup(Operator.And);



//        ugcShowcaseFilter.addConstraint(new PathConstraint(resourceBasePath,
//                PathConstraintType.IsDescendantNode));

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



    @Override
    public String getUserIdentifierKey() {
        return null;
    }

    public DeleteOperation getOperations() {
        return new BadgeDeleteOperation(badgingService);
    }

}
