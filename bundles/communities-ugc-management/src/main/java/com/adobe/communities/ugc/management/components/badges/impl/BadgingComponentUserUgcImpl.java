package com.adobe.communities.ugc.management.components.badges.impl;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgc;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.SrpDeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.SrpOperations;
import com.adobe.communities.ugc.management.components.badges.BadgingComponentUserUgc;
import com.adobe.cq.social.SocialException;
import com.adobe.cq.social.badging.api.BadgingService;
import com.adobe.cq.social.serviceusers.internal.ServiceUserWrapper;
import com.adobe.cq.social.srp.SocialResourceProvider;
import com.adobe.cq.social.srp.config.SocialResourceConfiguration;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.ugc.api.*;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */

@Service
@Component
public class BadgingComponentUserUgcImpl extends DefaultComponentUserUgc implements BadgingComponentUserUgc {

    private static final Logger LOG = LoggerFactory.getLogger(BadgingComponentUserUgcImpl.class);

    @Reference
    private UgcSearch ugcSearch;

    @Reference
    private SrpOperations srpOperations;

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    @Reference
    private BadgingService badgingService;

    @Reference
    private ServiceUserWrapper serviceUserWrapper;

    @Reference
    private ResourceResolverFactory rrf;

    private static final String USER_PROFILE_READER = "communities-user-admin";
    private static final String DEFAULT_USER_ROOT = "/home/users/";
    private static final String PROFILE_BADGES_PATH = "profile/badges";

//    @Reference
//    SocialUtils socialUtils;

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

    private SocialResourceProvider getSRP(@Nonnull final ResourceResolver resolver) {

        String ASI_UGC_PREFIX = "/content/usergenerated/asi";
        final Resource asiRoot = resolver.getResource(ASI_UGC_PREFIX);


        final SocialResourceConfiguration storageConfig = socialResourceUtilities.getStorageConfig(asiRoot); //socialUtils.getStorageConfig(resource);
        final SocialResourceProvider srp = socialResourceUtilities.getSocialResourceProvider(asiRoot);


//        SocialResourceProvider srp = socialUtils.getConfiguredProvider(asiRoot);
        if (srp == null) {
            // asi path should be readable by everybody, so some sort of weird runtime error here
            throw new SocialException("Can not obtain Social Resource Provider");
        }

        // initialize the configured SRP
//        srp.setConfig(socialUtils.getStorageConfig(asiRoot));
        srp.setConfig(storageConfig);

        return srp;
    }







    // something like asipath/user/home/userid/profile/badge/
    private String getBadgeUserRootPath(ResourceResolver resourceResolver, @Nonnull final SocialResourceProvider srp, @Nonnull final String userId)
            throws RepositoryException {

        Session session = resourceResolver.adaptTo(Session.class);

        UserManager userManager = resourceResolver.adaptTo(UserManager.class);
        boolean revertToAutoSave = false;
        String userProfilePath;
        try {
            Authorizable authorizable = userManager.getAuthorizable(userId);
            userProfilePath = authorizable.getPath();
        } catch (RepositoryException e) {
            throw new RepositoryException(e);
        }

        if (userProfilePath == null) {
//            userProfilePath = srp.getASIPath() + DEFAULT_USER_ROOT + userId + "/";
            throw new RuntimeException("Unable to find user: "+userId);
        }
        userProfilePath = srp.getASIPath() + userProfilePath + "/"+ PROFILE_BADGES_PATH;

        return userProfilePath;
    }

    @Override
    public UgcFilter getUgcFilter(ResourceResolver resourceResolver, String userId) {




        final SocialResourceProvider srp = getSRP(resourceResolver);
        final String badgePath;
        try {
            badgePath = getBadgeUserRootPath(resourceResolver,srp, userId);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        UgcFilter ugcShowcaseFilter = new UgcFilter();
        ConstraintGroup resourceGroupConstraint = new ConstraintGroup(Operator.And);
        Map<String, String> resourceTypeList = getComponentfilters();
        for (Map.Entry<String, String> entry : resourceTypeList.entrySet()) {
            resourceGroupConstraint.addConstraint(new ValueConstraint<String>(entry.getKey(), entry.getValue(), ComparisonType.Equals,
                    Operator.Or));
        }



        ConstraintGroup pathConstaint = new ConstraintGroup(Operator.And);
        pathConstaint.addConstraint(new PathConstraint(badgePath, PathConstraintType.IsDescendantNode));


//        ConstraintGroup userGroup = new ConstraintGroup(Operator.And);
//        String userIdentifierKey = getUserIdentifierKey();
//        userGroup.addConstraint(new ValueConstraint<String>(userIdentifierKey, userId, ComparisonType.Equals,
//                Operator.Or));
        ConstraintGroup andcons = new ConstraintGroup(Operator.And); // doesn't matter
        andcons.addConstraint(resourceGroupConstraint);
      //  andcons.addConstraint(userGroup);
        ugcShowcaseFilter.and(andcons);
        ugcShowcaseFilter.and(pathConstaint);
        return ugcShowcaseFilter;
    }

    public SearchResults<Resource> getUserUgc(ResourceResolver resourceResolver, String userId) {

        SearchResults<Resource> results;
        try {
            // Max value need to be checked (MAX_VALUE can't be used, throwing out of range error )
            results = ugcSearch.find(null, resourceResolver, getUgcFilter(resourceResolver, userId), 0, 100000, false);


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
        return new SrpDeleteOperation(srpOperations);
    }

}
