package com.adobe.communities.ugc.management.components.filelibrary.impl;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgc;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.deleteoperation.CommentDeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.components.filelibrary.FileLibraryFolderComponentUserUgc;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.filelibrary.client.api.FileLibrary;
import com.adobe.cq.social.filelibrary.client.endpoints.FileLibraryOperations;
import com.adobe.cq.social.ugc.api.UgcFilter;
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
public class FileLibraryFolderComponentUserUgcImpl extends DefaultComponentUserUgc implements FileLibraryFolderComponentUserUgc {

    @Reference
    FileLibraryOperations fileLibraryOperations;

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String>  filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, FileLibrary.RESOURCE_TYPE_FOLDER);
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.AUTHORIZABLE_ID;
    }

    public DeleteOperation getOperations() {
        return new CommentDeleteOperation(fileLibraryOperations);
    }
    @Override
    public UgcFilter getUgcFilter(String user) {
        return super.getUgcFilter(user);
    }
}
