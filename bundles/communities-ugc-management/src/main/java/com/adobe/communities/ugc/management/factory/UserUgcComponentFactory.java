package com.adobe.communities.ugc.management.factory;

import com.adobe.communities.ugc.management.commons.ComponentEnum;
import com.adobe.communities.ugc.management.commons.ComponentUserUgc;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;

/**
 * Created by mokatari on 10/13/17.
 */
public interface UserUgcComponentFactory<T> {
    public ComponentUserUgc getUserUgcFilter(Class T);
    public ComponentUserUgc getUserUgcFilter(ComponentEnum componentEnum);

    public CommentOperations getOperation(ComponentEnum componentEnum);
}
