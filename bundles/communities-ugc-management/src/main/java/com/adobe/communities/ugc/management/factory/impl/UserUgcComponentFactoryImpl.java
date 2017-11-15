package com.adobe.communities.ugc.management.factory.impl;

import com.adobe.communities.ugc.management.commons.ComponentEnum;
import com.adobe.communities.ugc.management.commons.ComponentUserUgc;
import com.adobe.communities.ugc.management.components.calendar.CalendarCommentComponentUserUgcImpl;
import com.adobe.communities.ugc.management.components.calendar.CalendarEventComponentUserUgcImpl;
import com.adobe.communities.ugc.management.components.filelibrary.FileLibraryDocumentComponentUserUgcImpl;
import com.adobe.communities.ugc.management.components.filelibrary.FileLibraryFolderComponentUserUgcImpl;
import com.adobe.communities.ugc.management.components.ideation.IdeationCommentComponentUserUgcImpl;
import com.adobe.communities.ugc.management.components.ideation.IdeationIdeaComponentUserUgcImpl;
import com.adobe.communities.ugc.management.components.qna.QnaPostComponentUserUgcImpl;
import com.adobe.communities.ugc.management.components.qna.QnaTopicComponentUserUgcImpl;
import com.adobe.communities.ugc.management.factory.UserUgcComponentFactory;
import com.adobe.communities.ugc.management.components.blog.impl.BlogCommentComponentUserUgcImpl;
import com.adobe.communities.ugc.management.components.blog.impl.BlogEntryComponentUserUgcImpl;
import com.adobe.communities.ugc.management.components.forum.ForumCommentComponentUserUgcImpl;
import com.adobe.communities.ugc.management.components.forum.ForumEntryComponentUserUgcImpl;
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
                componentUserUgc = new BlogEntryComponentUserUgcImpl();
                break;
            case BLOG_COMMENT:
                componentUserUgc = new BlogCommentComponentUserUgcImpl();
                break;
            case FORUM_ENTRY:
                componentUserUgc = new ForumEntryComponentUserUgcImpl();
                break;
            case FORUM_COMMENT:
                componentUserUgc = new ForumCommentComponentUserUgcImpl();
                break;
            case CALENDAR_EVENT:
                componentUserUgc = new CalendarEventComponentUserUgcImpl();
                break;
            case CALENDAR_COMMENT:
                componentUserUgc = new CalendarCommentComponentUserUgcImpl();
                break;
            case QNA_POST:
                componentUserUgc = new QnaPostComponentUserUgcImpl();
                break;
            case QNA_TOPIC:
                componentUserUgc = new QnaTopicComponentUserUgcImpl();
                break;
            case FILE_LIBRARY_DOCUMENT:
                componentUserUgc = new FileLibraryDocumentComponentUserUgcImpl();
                break;
            case FILE_LIBRARY_FOLDER:
                componentUserUgc = new FileLibraryFolderComponentUserUgcImpl();
                break;
            case IDEATION_COMMENT:
                componentUserUgc = new IdeationCommentComponentUserUgcImpl();
                break;
            case IDEATION_IDEA:
                componentUserUgc = new IdeationIdeaComponentUserUgcImpl();
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
