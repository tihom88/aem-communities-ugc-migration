package com.adobe.communities.ugc.management.components.service.impl;

import com.adobe.communities.ugc.management.commons.ComponentUserUgc;
import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgcImpl;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.communities.ugc.management.commons.deleteoperation.DeleteOperation;
import com.adobe.communities.ugc.management.commons.deleteoperation.impl.CommentDeleteOperation;
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
import com.adobe.cq.social.qna.client.api.QnaPost;
import com.adobe.cq.social.qna.client.endpoints.QnaForumOperations;
import com.adobe.cq.social.srp.utilities.api.SocialResourceUtilities;
import com.adobe.cq.social.ugc.api.UgcSearch;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */
@Component
@Service
public class ComponentsServiceImpl  implements ComponentsService {

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

    private List<ComponentUserUgc> componentsServiceList;

    public List<ComponentUserUgc> getServicesList() {

        return componentsServiceList;
    }

    @Activate
    public void init() {
        this.componentsServiceList = new ArrayList<ComponentUserUgc>();
        componentsServiceList.add(blogCommentComponentUserUgc);
        componentsServiceList.add(blogEntryComponentUserUgc);
        componentsServiceList.add(calendarEventComponentUserUgc);
        componentsServiceList.add(calendarCommentComponentUserUgc);
        componentsServiceList.add(fileLibraryFolderComponentUserUgc);
        componentsServiceList.add(fileLibraryDocumentComponentUserUgc);
        componentsServiceList.add(forumEntryComponentUserUgc);
        componentsServiceList.add(forumCommentComponentUserUgc);
        componentsServiceList.add(ideationCommentComponentUserUgc);
        componentsServiceList.add(ideationIdeaComponentUserUgc);
        componentsServiceList.add(qnaPostComponentUserUgc);
        componentsServiceList.add(qnaTopicComponentUserUgc);
        componentsServiceList.add(notificationComponentUserUgc);
        componentsServiceList.add(messageComponentUserUgc);
        componentsServiceList.add(scoringComponentUserUgc);
        componentsServiceList.add(badgingComponentUserUgc);
        componentsServiceList.add(likingComponentUserUgc);
        componentsServiceList.add(ratingComponentUserUgc);
        componentsServiceList.add(votingComponentUserUgc);

        //todo: change api's so that events can be ignored for deletion
        /*
                Activity stream should be last as these are created even for deletion of data/nodes.
        */
        componentsServiceList.add(activityStreamsComponentUserUgc);
    }

}
