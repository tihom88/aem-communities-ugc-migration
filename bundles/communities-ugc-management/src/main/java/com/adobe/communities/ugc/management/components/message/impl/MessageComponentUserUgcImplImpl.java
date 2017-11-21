package com.adobe.communities.ugc.management.components.message.impl;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgcImpl;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.impl.SrpDeleteOperation;
import com.adobe.communities.ugc.management.commons.srp.operations.SrpOperations;
import com.adobe.communities.ugc.management.components.message.MessageComponentUserUgc;
import com.adobe.cq.social.messaging.client.api.MessageSocialComponent;
import com.adobe.cq.social.messaging.client.endpoints.MessagingOperations;
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
public class MessageComponentUserUgcImplImpl extends DefaultComponentUserUgcImpl implements MessageComponentUserUgc{

    @Reference
    MessagingOperations messagingOperations;

    @Reference
    private UgcSearch ugcSearch;

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    @Reference
    private SrpOperations srpOperations;

    @Activate
    public void init() {
        setUgcSearch(ugcSearch);
        setSocialResourceUtilities(socialResourceUtilities);
    }

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String> filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, MessageSocialComponent.MESSAGE_RESOURCE_TYPE);
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.MESSAGE_SENDER;
    }

    public DeleteOperation getOperations() {
        return new SrpDeleteOperation(srpOperations);
    }



}
