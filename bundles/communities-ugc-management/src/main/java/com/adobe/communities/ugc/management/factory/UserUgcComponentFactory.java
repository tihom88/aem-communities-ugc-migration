package com.adobe.communities.ugc.management.factory;

import com.adobe.communities.ugc.management.commons.ComponentEnum;
import com.adobe.communities.ugc.management.commons.UserUgcFilter;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;

/**
 * Created by mokatari on 10/13/17.
 */
public interface UserUgcComponentFactory<T> {
    public UserUgcFilter getUserUgcFilter(Class T);
    public UserUgcFilter getUserUgcFilter(ComponentEnum componentEnum);

    public CommentOperations getOperation(ComponentEnum componentEnum);
}
