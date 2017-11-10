package com.adobe.communities.ugc.management.service;

import com.adobe.communities.ugc.management.commons.ComponentEnum;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.cq.social.scf.OperationException;
import com.adobe.cq.social.ugc.api.SearchResults;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

/**
 * Created by mokatari on 10/16/17.
 */
public interface UserManagementService {

    Map<ComponentEnum, SearchResults<Resource>> getUserUgc(ResourceResolver resourceResolver, List<ComponentEnum> componentEnumList, String userId);

    boolean deleteUserUgc(ResourceResolver resourceResolver, List<ComponentEnum> componentEnumList, String userId) throws OperationException;

    boolean deleteUserAccount(ResourceResolver resourceResolver, String userId) throws RepositoryException;

}
