package com.adobe.communities.ugc.management.servlets;

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
import com.adobe.communities.ugc.management.components.tally.LikingComponentUserUgc;
import com.adobe.communities.ugc.management.components.tally.RatingComponentUserUgc;
import com.adobe.communities.ugc.management.components.tally.VotingComponentUserUgc;
import com.adobe.communities.ugc.management.util.ZipCreator;
import com.adobe.cq.social.srp.SocialResourceProvider;
import com.adobe.cq.social.srp.config.SocialResourceConfiguration;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.ugc.api.SearchResults;
import com.adobe.cq.social.ugc.api.UgcSearch;
import org.apache.commons.io.IOUtils;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.jcr.JsonItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.ServletException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipOutputStream;
//import org.apache.sling.commons.json.JSONObject;
//import org.apache.sling.servlets.post.JSONResponse;


/**
 * Created by mokatari on 10/11/17.
 */





@Component
@Service
@Properties({@Property(name = "sling.servlet.paths", value = "/services/social/gdpr/getuserugc1")})
public class UserUgcFetchService1 extends SlingSafeMethodsServlet {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    private SocialResourceUtilities socialResourceUtilities;

    @Reference
    private UgcSearch ugcSearch;

    @Reference
    ActivityStreamsComponentUserUgc activityStreamsComponentUserUgc;
    @Reference
    BlogCommentComponentUserUgc blogCommentComponentUserUgc;
    @Reference
    BlogEntryComponentUserUgc blogEntryComponentUserUgc;
    @Reference
    CalendarEventComponentUserUgc calendarEventComponentUserUgc;
    @Reference
    CalendarCommentComponentUserUgc calendarCommentComponentUserUgc;
    @Reference
    FileLibraryFolderComponentUserUgc fileLibraryFolderComponentUserUgc;
    @Reference
    FileLibraryDocumentComponentUserUgc fileLibraryDocumentComponentUserUgc;
    @Reference
    ForumEntryComponentUserUgc forumEntryComponentUserUgc;
    @Reference
    ForumCommentComponentUserUgc forumCommentComponentUserUgc;
    @Reference
    IdeationCommentComponentUserUgc ideationCommentComponentUserUgc;
    @Reference
    IdeationIdeaComponentUserUgc ideationIdeaComponentUserUgc;
    @Reference
    QnaPostComponentUserUgc qnaPostComponentUserUgc;
    @Reference
    QnaTopicComponentUserUgc qnaTopicComponentUserUgc;
    @Reference
    LikingComponentUserUgc likingComponentUserUgc;
    @Reference
    RatingComponentUserUgc ratingComponentUserUgc;
    @Reference
    VotingComponentUserUgc votingComponentUserUgc;
    @Reference
    NotificationComponentUserUgc notificationComponentUserUgc;
    @Reference
    MessageComponentUserUgc messageComponentUserUgc;
    @Reference
    ScoringComponentUserUgc scoringComponentUserUgc;
    @Reference
    BadgingComponentUserUgc badgingComponentUserUgc;



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


        List<SearchResults<Resource>> resultsList = new ArrayList<SearchResults<Resource>>();

        resultsList.add(blogEntryComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(blogCommentComponentUserUgc.getUserUgc(resourceResolver,user));
        resultsList.add(activityStreamsComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(calendarEventComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(calendarCommentComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(fileLibraryFolderComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(fileLibraryDocumentComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(forumEntryComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(forumCommentComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(ideationIdeaComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(ideationCommentComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(qnaTopicComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(qnaPostComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(likingComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(ratingComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(votingComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(notificationComponentUserUgc.getUserUgc(resourceResolver, user));

        resultsList.add(messageComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(scoringComponentUserUgc.getUserUgc(resourceResolver, user));
        resultsList.add(badgingComponentUserUgc.getUserUgc(resourceResolver, user));



//        List<ComponentEnum> componentEnumList = Arrays.asList(ComponentEnum.values());
//        Map<ComponentEnum, SearchResults<Resource>> resultsList = userManagementService.getUserUgc(resourceResolver, componentEnumList, user);
//        List<String> attachmentPaths = new ArrayList<String>();
//        for (Map.Entry<ComponentEnum, SearchResults<Resource>> entry : resultsList.entrySet()) {
//            for (Resource resourceNode : entry.getValue().getResults()) {
//                String[] images = resourceNode.getValueMap().get("social:attachments") == null? new String[0] : (String[]) resourceNode.getValueMap().get("social:attachments") ;
//                for (String imagePath : images) {
//                    attachmentPaths.add(imagePath);
//                }
//            }
//        }

//        resp.setContentType("application/octet-stream");
//        final String headerKey = "Content-Disposition";
//        final String headerValue = "attachment; filename=\""+user+"-UgcData.zip" +"\"";
//        resp.setHeader(headerKey, headerValue);

        String userUgcJson = null;
        userUgcJson = createJsonResponse(resultsList);
//        createZip(req, resp, userUgcJson, attachmentPaths);
        resp.getOutputStream().println(userUgcJson.toString());
    }

    private void createZip(final SlingHttpServletRequest req,
                           final SlingHttpServletResponse resp, String ugcTextData, List<String> attachmentPaths) throws IOException, ServletException {

        final Resource resource = req.getResource();
        final ResourceResolver resourceResolver = req.getResourceResolver();
        final SocialResourceConfiguration storageConfig = socialResourceUtilities.getStorageConfig(resource); //socialUtils.getStorageConfig(resource);
        final SocialResourceProvider srp = socialResourceUtilities.getSocialResourceProvider(resource);
        srp.setConfig(storageConfig);


        File outFile = File.createTempFile(UUID.randomUUID().toString(), ".zip");
        FileOutputStream fos = new FileOutputStream(outFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ZipOutputStream zip = new ZipOutputStream(bos);


        ZipCreator.addTextToZip("ugcTextData.json", zip, ugcTextData);
        ZipCreator.addAttachmentsToZip(attachmentPaths, zip, srp, resourceResolver);

        OutputStream outStream = null;
        InputStream inStream = null;
        try {
            IOUtils.closeQuietly(zip);
            IOUtils.closeQuietly(bos);
            IOUtils.closeQuietly(fos);
            // obtains response's output stream
            outStream = resp.getOutputStream();
            inStream = new FileInputStream(outFile);
            // copy from file to output
            IOUtils.copy(inStream, outStream);
        } catch (final IOException e) {
            throw new ServletException(e);
        } catch (final Exception e) {
            throw new ServletException(e);
        } finally {
            IOUtils.closeQuietly(zip);
            IOUtils.closeQuietly(bos);
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(inStream);
            IOUtils.closeQuietly(outStream);
            if (outFile != null) {
                outFile.delete();
            }
        }

    }

    private String createJsonResponse(SearchResults<Resource> results) throws ServletException {
        StringBuilder response = new StringBuilder();

        boolean isEmpty = true;
        response.append("[");
        final JsonItemWriter jsonWriter = new JsonItemWriter(null);
        try {

                for (Resource resultRes : results.getResults()) {
                    Node node = null;
                    final StringWriter stringWriter = new StringWriter();

                    node = resultRes.getResourceResolver().adaptTo(Session.class).getNode(resultRes.getPath());

                    // max recursion level set to 0 to get info of current node itself
                    jsonWriter.dump(node, stringWriter, 0);
                    response.append(stringWriter.toString() + ",");
                    isEmpty = false;
                }

        } catch (RepositoryException e) {
            throw new ServletException(e);
        } catch (JSONException e) {
            throw new ServletException(e);
        }
        if (!isEmpty) {
            response.setLength(response.length() - 1);
        }
        response.append("]");
        return response.toString();
    }

    private String createJsonResponse(List<SearchResults<Resource>> resultsList) throws ServletException {
        StringBuilder response = new StringBuilder();

        boolean isEmpty = true;
        response.append("[");
        final JsonItemWriter jsonWriter = new JsonItemWriter(null);
        try {
            for (SearchResults<Resource> results : resultsList) {
                for (Resource result : results.getResults()) {
                    Node node = null;
                    final StringWriter stringWriter = new StringWriter();

                    node = result.getResourceResolver().adaptTo(Session.class).getNode(result.getPath());

                    // max recursion level set to 0 to get info of current node itself
                    jsonWriter.dump(node, stringWriter, 0);
                    response.append(stringWriter.toString() + ",");
                    isEmpty = false;
                }
            }
        } catch (RepositoryException e) {
            throw new ServletException(e);
        } catch (JSONException e) {
            throw new ServletException(e);
        }
        if (!isEmpty) {
            response.setLength(response.length() - 1);
        }
        response.append("]");
        return response.toString();
    }
}
