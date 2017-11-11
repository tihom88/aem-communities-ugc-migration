package com.adobe.communities.ugc.management.components.forum;

import com.adobe.communities.ugc.management.commons.DefaultUserUgcFilter;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.forum.client.api.Forum;
import com.adobe.cq.social.forum.client.endpoints.ForumOperationExtension;
import com.adobe.cq.social.forum.client.endpoints.ForumOperations;
import com.adobe.cq.social.qna.client.endpoints.QnaForumOperations;
import com.adobe.cq.social.ugc.api.UgcFilter;
import jdk.nashorn.internal.ir.annotations.Reference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */
public class ForumCommentUserUgcFilter extends DefaultUserUgcFilter {

    @Reference
    ForumOperations forumOperations;

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String>  filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, Forum.RESOURCE_TYPE_POST);
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.AUTHORIZABLE_ID;
    }

    public CommentOperations getCommentOperations() {
        return forumOperations;
    }

    @Override
    public UgcFilter getUgcFilter(String user) {
        return super.getUgcFilter(user);
    }
}
