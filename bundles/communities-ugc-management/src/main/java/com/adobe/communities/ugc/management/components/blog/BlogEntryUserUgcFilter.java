package com.adobe.communities.ugc.management.components.blog;

import com.adobe.communities.ugc.management.commons.DefaultUserUgcFilter;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.journal.client.api.Journal;
import com.adobe.cq.social.journal.client.endpoints.JournalOperations;
import com.adobe.cq.social.ugc.api.UgcFilter;
import jdk.nashorn.internal.ir.annotations.Reference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */
public class BlogEntryUserUgcFilter extends DefaultUserUgcFilter {

    @Reference
    JournalOperations journalOperations;

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String> filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, Journal.RESOURCE_TYPE_ENTRY);
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.AUTHORIZABLE_ID;
    }

    public CommentOperations getCommentOperations() {
        return journalOperations;
    }

    @Override
    public UgcFilter getUgcFilter(String user) {
        return super.getUgcFilter(user);
    }
}
