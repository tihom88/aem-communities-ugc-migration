package com.adobe.communities.ugc.management.components.notification.impl;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgc;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.deleteoperation.CommentDeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.SrpDeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.SrpOperations;
import com.adobe.communities.ugc.management.components.blog.BlogEntryComponentUserUgc;
import com.adobe.communities.ugc.management.components.notification.NotificationComponentUserUgc;
import com.adobe.cq.social.journal.client.api.Journal;
import com.adobe.cq.social.journal.client.endpoints.JournalOperations;
import com.adobe.cq.social.notifications.api.Notification;
import com.adobe.cq.social.notifications.client.api.SocialNotification;
import com.adobe.cq.social.notifications.endpoint.NotificationOperationsExtension;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.ugc.api.UgcFilter;
import com.adobe.cq.social.ugc.api.UgcSearch;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */
@Component
@Service
public class NotificationComponentUserUgcImpl extends DefaultComponentUserUgc implements NotificationComponentUserUgc {

    @Reference
    private SrpOperations srpOperations;

    @Reference
    private UgcSearch ugcSearch;

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    @Activate
    public void init() {
        setUgcSearch(ugcSearch);
        setSocialResourceUtilities(socialResourceUtilities);
    }

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String> filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, SocialNotification.RESOURCE_TYPE);
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.ACTOR_ID;
    }

    public DeleteOperation getOperations() {
        return new SrpDeleteOperation(srpOperations);
    }

    @Override
    public UgcFilter getUgcFilter(String user) {
        return super.getUgcFilter(user);
    }

}
