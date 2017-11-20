package com.adobe.communities.ugc.management.account.impl;

import com.adobe.communities.ugc.management.account.UserAccountDeletionService;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.ugc.api.UgcSearch;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Created by mokatari on 10/16/17.
 */
@Component
@Service
public class UserAccountDeletionServiceImpl implements UserAccountDeletionService {
    private static final Logger log = LoggerFactory.getLogger(UserAccountDeletionServiceImpl.class);

    @Reference
    UgcSearch ugcSearch;
    
    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    public boolean deleteUserAccount(ResourceResolver resourceResolver, String userId) throws RepositoryException {
        Session session = resourceResolver.adaptTo(Session.class);

        UserManager userManager = resourceResolver.adaptTo(UserManager.class);
        boolean revertToAutoSave = false;
        try {
            Authorizable authorizable = userManager.getAuthorizable(userId);
            if (userManager.isAutoSave()) {
                userManager.autoSave(false);
                revertToAutoSave = true;
            }
            authorizable.remove();
            try {
                resourceResolver.commit();
            } catch (PersistenceException e) {
                throw new RepositoryException(e);
            }
            session.save();
        } catch (RepositoryException e) {
            throw new RepositoryException(e);
        } finally {
            if (revertToAutoSave) {
                try {
                    userManager.autoSave(true);
                } catch (RepositoryException e) {
                    log.warn("Cannot revert autosave mode of user manager", e.getMessage());
                }
            }
        }
        return false;
    }

}
