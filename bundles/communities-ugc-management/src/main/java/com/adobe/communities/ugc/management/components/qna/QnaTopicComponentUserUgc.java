package com.adobe.communities.ugc.management.components.qna;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgc;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.qna.client.api.QnaPost;
import com.adobe.cq.social.qna.client.endpoints.QnaForumOperations;
import com.adobe.cq.social.ugc.api.UgcFilter;
import org.apache.felix.scr.annotations.Reference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */
public class QnaTopicComponentUserUgc extends DefaultComponentUserUgc {

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
