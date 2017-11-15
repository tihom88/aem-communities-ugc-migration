package com.adobe.communities.ugc.management.servlets;

import com.adobe.communities.ugc.management.commons.ComponentEnum;
import com.adobe.communities.ugc.management.components.blog.BComponentUserUgc;
import com.adobe.communities.ugc.management.factory.UserUgcComponentFactory;
import com.adobe.communities.ugc.management.service.UserManagementService;
import com.adobe.cq.social.scf.OperationException;
import com.adobe.cq.social.srp.SocialResourceProvider;
import com.adobe.cq.social.srp.config.SocialResourceConfiguration;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.ugc.api.SearchResults;
import com.adobe.cq.social.ugc.api.UgcSearch;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
//import org.apache.sling.commons.json.JSONObject;
//import org.apache.sling.servlets.post.JSONResponse;


/**
 * Created by mokatari on 10/11/17.
 */

@Component
@Service
@Properties({@Property(name = "sling.servlet.paths", value = "/services/social/gdpr/deleteuserugc1")})
public class UserUgcDeleteService1 extends SlingSafeMethodsServlet {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    @Reference
    private UgcSearch ugcSearch;

    @Reference
    BComponentUserUgc bComponentUserUgc;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        String user = req.getParameter("user");
        resp.setContentType("text/plain");
        final ResourceResolver resourceResolver = req.getResourceResolver();
        final SocialResourceConfiguration storageConfig = socialResourceUtilities.getStorageConfig(resource); //socialUtils.getStorageConfig(resource);
        final SocialResourceProvider srp = socialResourceUtilities.getSocialResourceProvider(resource);
        srp.setConfig(storageConfig);

        resourceResolver.adaptTo(Session.class);
        List<ComponentEnum> componentEnumList = Arrays.asList(ComponentEnum.values());
        try {
            bComponentUserUgc.deleteUserUgc(resourceResolver, user);
        } catch (OperationException e) {
            throw new ServletException(e);
        }
        resp.getOutputStream().println("UGC for user"+user+ " deleted");
    }

    private String createJsonResponse(SearchResults<Resource> results) {
        StringBuilder response = new StringBuilder();
        response.append("[");
        for (Resource resultRes : results.getResults()) {
            response.append(new JSONObject(resultRes.getValueMap()) + ",");
        }
        if (results.getResults().size() != 0) {
            response.setLength(response.length() - 1);
        }
        response.append("]");
        return response.toString();
    }

}
