package com.adobe.communities.ugc.management.factory.impl;

import com.adobe.communities.ugc.management.commons.ComponentEnum;
import com.adobe.communities.ugc.management.commons.UserUgcFilter;
import com.adobe.communities.ugc.management.components.activitystreams.ActivityStreamsUserUgcFilter;
import com.adobe.communities.ugc.management.components.calendar.CalendarCommentUserUgcFilter;
import com.adobe.communities.ugc.management.components.calendar.CalendarEventUserUgcFilter;
import com.adobe.communities.ugc.management.components.filelibrary.FileLibraryDocumentUserUgcFilter;
import com.adobe.communities.ugc.management.components.filelibrary.FileLibraryFolderUserUgcFilter;
import com.adobe.communities.ugc.management.components.ideation.IdeationCommentUserUgcFilter;
import com.adobe.communities.ugc.management.components.ideation.IdeationIdeaUserUgcFilter;
import com.adobe.communities.ugc.management.components.qna.QnaPostUserUgcFilter;
import com.adobe.communities.ugc.management.components.qna.QnaTopicUserUgcFilter;
import com.adobe.communities.ugc.management.components.tally.LikingUserUgcFilter;
import com.adobe.communities.ugc.management.components.tally.RatingUserUgcFilter;
import com.adobe.communities.ugc.management.components.tally.VotingUserUgcFilter;
import com.adobe.communities.ugc.management.factory.UserUgcComponentFactory;
import com.adobe.communities.ugc.management.components.blog.BlogCommentUserUgcFilter;
import com.adobe.communities.ugc.management.components.blog.BlogEntryUserUgcFilter;
import com.adobe.communities.ugc.management.components.forum.ForumCommentUserUgcFilter;
import com.adobe.communities.ugc.management.components.forum.ForumEntryUserUgcFilter;
import com.adobe.cq.social.calendar.client.endpoints.CalendarOperations;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.filelibrary.client.endpoints.FileLibraryOperations;
import com.adobe.cq.social.forum.client.endpoints.ForumOperations;
import com.adobe.cq.social.ideation.client.endpoints.IdeationOperationExtension;
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


    public UserUgcFilter getUserUgcFilter(ComponentEnum componentEnum){

        UserUgcFilter userUgcFilter;


        switch(componentEnum){
            case BLOG_ENTRY:
                userUgcFilter = new BlogEntryUserUgcFilter();
                break;
            case BLOG_COMMENT:
                userUgcFilter = new BlogCommentUserUgcFilter();
                break;
            case FORUM_ENTRY:
                userUgcFilter = new ForumEntryUserUgcFilter();
                break;
            case FORUM_COMMENT:
                userUgcFilter = new ForumCommentUserUgcFilter();
                break;
            case CALENDAR_EVENT:
                userUgcFilter = new CalendarEventUserUgcFilter();
                break;
            case CALENDAR_COMMENT:
                userUgcFilter = new CalendarCommentUserUgcFilter();
                break;
            case QNA_POST:
                userUgcFilter = new QnaPostUserUgcFilter();
                break;
            case QNA_TOPIC:
                userUgcFilter = new QnaTopicUserUgcFilter();
                break;
            case FILE_LIBRARY_DOCUMENT:
                userUgcFilter = new FileLibraryDocumentUserUgcFilter();
                break;
            case FILE_LIBRARY_FOLDER:
                userUgcFilter = new FileLibraryFolderUserUgcFilter();
                break;
            case IDEATION_COMMENT:
                userUgcFilter = new IdeationCommentUserUgcFilter();
                break;
            case IDEATION_IDEA:
                userUgcFilter = new IdeationIdeaUserUgcFilter();
                break;
            case TALLY_LIKING:
                userUgcFilter = new LikingUserUgcFilter();
                break;
            case TALLY_RATING:
                userUgcFilter = new RatingUserUgcFilter();
                break;
            case TALLY_VOTING:
                userUgcFilter = new VotingUserUgcFilter();
                break;
            case ACTIVITY_STREAMS:
                userUgcFilter = new ActivityStreamsUserUgcFilter();
                break;
            default:
                throw new RuntimeException("ComponentEnum not defined for fetching userContent");
        }
        return userUgcFilter;
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
    public UserUgcFilter getUserUgcFilter(Class T) {
        return null;
    }
}
