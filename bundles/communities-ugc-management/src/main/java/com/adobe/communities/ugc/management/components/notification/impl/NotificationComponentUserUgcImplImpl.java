package com.adobe.communities.ugc.management.components.notification.impl;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgcImpl;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.impl.SrpDeleteOperation;
import com.adobe.communities.ugc.management.commons.srp.operations.SrpOperations;
import com.adobe.communities.ugc.management.components.notification.NotificationComponentUserUgc;
import com.adobe.cq.social.notifications.client.api.SocialNotification;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
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
public class NotificationComponentUserUgcImplImpl extends DefaultComponentUserUgcImpl implements NotificationComponentUserUgc {

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


}
