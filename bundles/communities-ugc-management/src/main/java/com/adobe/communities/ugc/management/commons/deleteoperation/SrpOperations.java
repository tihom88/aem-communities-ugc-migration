package com.adobe.communities.ugc.management.commons.deleteoperation;

import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.Session;

public interface SrpOperations {
    public void delete(ResourceResolver resourceResolver, Resource resource, Session session) throws PersistenceException;
}
