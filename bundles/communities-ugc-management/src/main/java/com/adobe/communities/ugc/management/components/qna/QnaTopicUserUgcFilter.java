package com.adobe.communities.ugc.management.components.qna;

import com.adobe.communities.ugc.management.commons.DefaultUserUgcFilter;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.cq.social.calendar.client.api.Calendar;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.qna.client.api.QnaPost;
import com.adobe.cq.social.qna.client.endpoints.QnaForumOperations;
import com.adobe.cq.social.ugc.api.UgcFilter;
import jdk.nashorn.internal.ir.annotations.Reference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */
public class QnaTopicUserUgcFilter extends DefaultUserUgcFilter {

    @Reference
    QnaForumOperations qnaForumOperations;

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String>  filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, QnaPost.RESOURCE_TYPE_TOPIC);
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.AUTHORIZABLE_ID;
    }

    public CommentOperations getCommentOperations() {
        return qnaForumOperations;
    }

    @Override
    public UgcFilter getUgcFilter(String user) {
        return super.getUgcFilter(user);
    }
}
