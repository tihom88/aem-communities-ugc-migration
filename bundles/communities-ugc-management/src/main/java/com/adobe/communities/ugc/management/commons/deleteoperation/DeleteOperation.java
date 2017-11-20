package com.adobe.communities.ugc.management.commons.deleteoperation;

import com.adobe.communities.ugc.management.commons.ComponentUserUgc;
import com.adobe.cq.social.scf.OperationException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.Session;

public interface DeleteOperation {
    void delete(ResourceResolver resourceResolver, Resource resource, Session session, String authorizableId) throws OperationException;
}
