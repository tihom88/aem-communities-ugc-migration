package com.adobe.communities.ugc.management.factory.impl;

import com.adobe.communities.ugc.management.commons.ComponentEnum;
import com.adobe.communities.ugc.management.commons.ComponentUserUgc;
import com.adobe.communities.ugc.management.components.activitystreams.ActivityStreamsComponentUserUgc;
import com.adobe.communities.ugc.management.components.calendar.CalendarCommentComponentUserUgc;
import com.adobe.communities.ugc.management.components.calendar.CalendarEventComponentUserUgc;
import com.adobe.communities.ugc.management.components.filelibrary.FileLibraryDocumentComponentUserUgc;
import com.adobe.communities.ugc.management.components.filelibrary.FileLibraryFolderComponentUserUgc;
import com.adobe.communities.ugc.management.components.ideation.IdeationCommentComponentUserUgc;
import com.adobe.communities.ugc.management.components.ideation.IdeationIdeaComponentUserUgc;
import com.adobe.communities.ugc.management.components.qna.QnaPostComponentUserUgc;
import com.adobe.communities.ugc.management.components.qna.QnaTopicComponentUserUgc;
import com.adobe.communities.ugc.management.components.tally.LikingComponentUserUgc;
import com.adobe.communities.ugc.management.components.tally.RatingComponentUserUgc;
import com.adobe.communities.ugc.management.components.tally.VotingComponentUserUgc;
import com.adobe.communities.ugc.management.factory.UserUgcComponentFactory;
import com.adobe.communities.ugc.management.components.blog.BlogCommentComponentUserUgc;
import com.adobe.communities.ugc.management.components.blog.BlogEntryComponentUserUgc;
import com.adobe.communities.ugc.management.components.forum.ForumCommentComponentUserUgc;
import com.adobe.communities.ugc.management.components.forum.ForumEntryComponentUserUgc;
import com.adobe.cq.social.calendar.client.endpoints.CalendarOperations;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.filelibrary.client.endpoints.FileLibraryOperations;
import com.adobe.cq.social.forum.client.endpoints.ForumOperations;
import com.adobe.cq.social.ideation.client.endpoints.IdeationOperations;
import com.adobe.cq.social.journal.client.endpoints.JournalOperations;
import com.adobe.cq.social.qna.client.endpoints.QnaForumOperations;
import com.adobe.cq.social.srp.SocialResourceProvider;
import com.adobe.cq.social.tally.client.endpoints.TallyOperationsService;
import com.adobe.cq.social.ugcbase.SocialUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;


/**
 * Created by mokatari on 10/12/17.
 */

@Component
@Service
public class UserUgcComponentFactoryImpl implements UserUgcComponentFactory {

    @Reference
    private ForumOperations forumOperations;

    @Reference
    private QnaForumOperations qnaForumOperations;

    @Reference
    private CommentOperations commentOperations;

    @Reference
    private TallyOperationsService tallyOperationsService;

    @Reference
    private CalendarOperations calendarOperations;

    @Reference
    private JournalOperations journalOperations;

    @Reference
    private FileLibraryOperations fileLibraryOperations;

    @Reference
    private IdeationOperations ideationOperations;

    @Reference
    private SocialUtils socialUtils;

    private SocialResourceProvider resProvider;

    private SlingHttpServletRequest request;


    public ComponentUserUgc getUserUgcFilter(ComponentEnum componentEnum){

        ComponentUserUgc componentUserUgc;


        switch(componentEnum){
            case BLOG_ENTRY:
                componentUserUgc = new BlogEntryComponentUserUgc();
                break;
            case BLOG_COMMENT:
                componentUserUgc = new BlogCommentComponentUserUgc();
                break;
            case FORUM_ENTRY:
                componentUserUgc = new ForumEntryComponentUserUgc();
                break;
            case FORUM_COMMENT:
                componentUserUgc = new ForumCommentComponentUserUgc();
                break;
            case CALENDAR_EVENT:
                componentUserUgc = new CalendarEventComponentUserUgc();
                break;
            case CALENDAR_COMMENT:
                componentUserUgc = new CalendarCommentComponentUserUgc();
                break;
            case QNA_POST:
                componentUserUgc = new QnaPostComponentUserUgc();
                break;
            case QNA_TOPIC:
                componentUserUgc = new QnaTopicComponentUserUgc();
                break;
            case FILE_LIBRARY_DOCUMENT:
                componentUserUgc = new FileLibraryDocumentComponentUserUgc();
                break;
            case FILE_LIBRARY_FOLDER:
                componentUserUgc = new FileLibraryFolderComponentUserUgc();
                break;
            case IDEATION_COMMENT:
                componentUserUgc = new IdeationCommentComponentUserUgc();
                break;
            case IDEATION_IDEA:
                componentUserUgc = new IdeationIdeaComponentUserUgc();
                break;
//            case TALLY_LIKING:
//                componentUserUgc = new LikingComponentUserUgc();
//                break;
//            case TALLY_RATING:
//                componentUserUgc = new RatingComponentUserUgc();
//                break;
//            case TALLY_VOTING:
//                componentUserUgc = new VotingComponentUserUgc();
//                break;
//            case ACTIVITY_STREAMS:
//                componentUserUgc = new ActivityStreamsComponentUserUgc();
//                break;
            default:
                throw new RuntimeException("ComponentEnum not defined for fetching userContent");
        }
        return componentUserUgc;
    }

    @Override
    public CommentOperations getOperation(ComponentEnum componentEnum) {
        CommentOperations commentOperations;

        switch (componentEnum){
            case BLOG_ENTRY:
            case BLOG_COMMENT:
                commentOperations = journalOperations;
                break;
            case FORUM_ENTRY:
            case FORUM_COMMENT:
                commentOperations = forumOperations;
                break;
            case CALENDAR_COMMENT:
            case CALENDAR_EVENT:
                commentOperations = calendarOperations;
                break;
            case QNA_POST:
            case QNA_TOPIC:
                commentOperations = qnaForumOperations;
                break;
            case FILE_LIBRARY_DOCUMENT:
            case FILE_LIBRARY_FOLDER:
                commentOperations = fileLibraryOperations;
                break;
            case IDEATION_IDEA:
            case IDEATION_COMMENT:
                commentOperations = ideationOperations;
                break;
//            case TALLY_VOTING:
//            case TALLY_RATING:
//            case TALLY_LIKING:
//                commentOperations = tallyOperationsService;
//                break;
            default:
                throw new RuntimeException("ComponentEnum not defined for operation");
        }
        return commentOperations;
    }


    @Override
    public ComponentUserUgc getUserUgcFilter(Class T) {
        return null;
    }
}
