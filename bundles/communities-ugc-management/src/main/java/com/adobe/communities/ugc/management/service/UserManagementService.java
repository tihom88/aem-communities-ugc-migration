package com.adobe.communities.ugc.management.service;

import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.RepositoryException;

/**
 * Created by mokatari on 10/16/17.
 */
public interface UserManagementService {

    boolean deleteUserAccount(ResourceResolver resourceResolver, String userId) throws RepositoryException;

}
