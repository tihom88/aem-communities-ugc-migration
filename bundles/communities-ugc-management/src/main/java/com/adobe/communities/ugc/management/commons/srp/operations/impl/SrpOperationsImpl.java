package com.adobe.communities.ugc.management.commons.srp.operations.impl;

import com.adobe.communities.ugc.management.commons.srp.operations.SrpOperations;
import com.adobe.cq.social.srp.SocialResourceProvider;
import com.adobe.cq.social.srp.config.SocialResourceConfiguration;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.Session;

@Service
@Component
public class SrpOperationsImpl implements SrpOperations {

    @Reference
    SocialResourceUtilities socialResourceUtilities;

    public void delete(ResourceResolver resourceResolver, Resource resource, Session session) throws PersistenceException {
        final SocialResourceConfiguration storageConfig = socialResourceUtilities.getStorageConfig(resource); //socialUtils.getStorageConfig(resource);
        final SocialResourceProvider srp = socialResourceUtilities.getSocialResourceProvider(resource);
        srp.setConfig(storageConfig);
        srp.delete(resourceResolver, resource.getPath());
    }
}
