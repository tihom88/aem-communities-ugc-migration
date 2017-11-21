package com.adobe.communities.ugc.management.servlets;

import com.adobe.communities.ugc.management.commons.ComponentUserUgc;
import com.adobe.communities.ugc.management.components.activitystreams.ActivityStreamsComponentUserUgc;
import com.adobe.communities.ugc.management.components.badges.BadgingComponentUserUgc;
import com.adobe.communities.ugc.management.components.blog.BlogCommentComponentUserUgc;
import com.adobe.communities.ugc.management.components.blog.BlogEntryComponentUserUgc;
import com.adobe.communities.ugc.management.components.calendar.CalendarCommentComponentUserUgc;
import com.adobe.communities.ugc.management.components.calendar.CalendarEventComponentUserUgc;
import com.adobe.communities.ugc.management.components.filelibrary.FileLibraryDocumentComponentUserUgc;
import com.adobe.communities.ugc.management.components.filelibrary.FileLibraryFolderComponentUserUgc;
import com.adobe.communities.ugc.management.components.forum.ForumCommentComponentUserUgc;
import com.adobe.communities.ugc.management.components.forum.ForumEntryComponentUserUgc;
import com.adobe.communities.ugc.management.components.ideation.IdeationCommentComponentUserUgc;
import com.adobe.communities.ugc.management.components.ideation.IdeationIdeaComponentUserUgc;
import com.adobe.communities.ugc.management.components.message.MessageComponentUserUgc;
import com.adobe.communities.ugc.management.components.notification.NotificationComponentUserUgc;
import com.adobe.communities.ugc.management.components.qna.QnaPostComponentUserUgc;
import com.adobe.communities.ugc.management.components.qna.QnaTopicComponentUserUgc;
import com.adobe.communities.ugc.management.components.scoring.ScoringComponentUserUgc;
import com.adobe.communities.ugc.management.components.service.ComponentsService;
import com.adobe.communities.ugc.management.components.tally.LikingComponentUserUgc;
import com.adobe.communities.ugc.management.components.tally.RatingComponentUserUgc;
import com.adobe.communities.ugc.management.components.tally.VotingComponentUserUgc;
import com.adobe.communities.ugc.management.account.UserAccountDeletionService;
import com.adobe.cq.social.scf.OperationException;
import com.adobe.cq.social.srp.SocialResourceProvider;
import com.adobe.cq.social.srp.config.SocialResourceConfiguration;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.ugc.api.UgcSearch;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
//import org.apache.sling.commons.json.JSONObject;
//import org.apache.sling.servlets.post.JSONResponse;


/**
 * Created by mokatari on 10/11/17.
 */

@Component
@Service
@Properties({@Property(name = "sling.servlet.paths", value = "/services/social/deleteuseraccount")})
public class UserAccountDeleteService extends SlingSafeMethodsServlet {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    @Reference
    private UgcSearch ugcSearch;

    @Reference
    UserAccountDeletionService userAccountDeletionService;

    @Reference
    private ComponentsService componentsService;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        String user = req.getParameter("user");
        resp.setContentType("text/plain");
        final ResourceResolver resourceResolver = req.getResourceResolver();
        final SocialResourceConfiguration storageConfig = socialResourceUtilities.getStorageConfig(resource);
        final SocialResourceProvider srp = socialResourceUtilities.getSocialResourceProvider(resource);
        srp.setConfig(storageConfig);
        try {
            List<ComponentUserUgc> componentUserUgcList = componentsService.getServicesList();
            for (ComponentUserUgc componentUserUgc : componentUserUgcList) {
                componentUserUgc.deleteUserUgc(resourceResolver, user);
            }
            userAccountDeletionService.deleteUserAccount(resourceResolver, user);
        } catch (RepositoryException e) {
            throw new ServletException(e);
        } catch (OperationException e) {
            throw new ServletException(e);
        }
        resp.getOutputStream().println("UserAccount: " + user + " deleted");
    }
}
