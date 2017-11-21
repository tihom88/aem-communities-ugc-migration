package com.adobe.communities.ugc.management.components.tally.impl;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgcImpl;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.commons.srp.operations.SrpOperations;
import com.adobe.communities.ugc.management.commons.deleteoperation.impl.TallyDeleteOperation;
import com.adobe.communities.ugc.management.components.tally.VotingComponentUserUgc;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.tally.client.api.VotingSocialComponent;
import com.adobe.cq.social.tally.client.endpoints.TallyOperationsService;
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
@Service
@Component
public class VotingComponentUserUgcImplImpl extends DefaultComponentUserUgcImpl implements VotingComponentUserUgc{

    @Reference
    TallyOperationsService tallyOperationsService;

    @Reference
    private UgcSearch ugcSearch;

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    @Reference
    SrpOperations srpOperations;

    @Activate
    public void init() {
        setUgcSearch(ugcSearch);
        setSocialResourceUtilities(socialResourceUtilities);
    }

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String>  filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, VotingSocialComponent.VOTING_RESOURCE_TYPE);
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.USERIDENTIFIER;
    }

    public DeleteOperation getOperations() {
        return new TallyDeleteOperation(srpOperations, tallyOperationsService, TallyOperationsService.VOTING);
    }


}
