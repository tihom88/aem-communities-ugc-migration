package com.adobe.communities.ugc.management.commons.deleteoperation.impl;

import com.adobe.communities.ugc.management.commons.ComponentUserUgc;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.scf.OperationException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.Session;

public class CommentDeleteOperation implements DeleteOperation {

    CommentOperations commentOperations;
    public CommentDeleteOperation(CommentOperations commentOperations){
        this.commentOperations = commentOperations;
    }
    public void delete(ResourceResolver resourceResolver, Resource resource, Session session, String authorizableId) throws OperationException {
        commentOperations.delete(resource, session);
    }
}
