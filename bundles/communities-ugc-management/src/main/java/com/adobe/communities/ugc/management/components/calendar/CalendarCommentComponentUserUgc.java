package com.adobe.communities.ugc.management.components.calendar;

import com.adobe.communities.ugc.management.commons.DefaultComponentUserUgc;
import com.adobe.communities.ugc.management.commons.Identifiers;
import com.adobe.cq.social.calendar.client.api.Calendar;
import com.adobe.cq.social.calendar.client.endpoints.CalendarOperations;
import com.adobe.cq.social.commons.comments.endpoints.CommentOperations;
import com.adobe.cq.social.ugc.api.UgcFilter;
import org.apache.felix.scr.annotations.Reference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mokatari on 10/13/17.
 */
public class CalendarCommentComponentUserUgc extends DefaultComponentUserUgc {

    @Reference
    CalendarOperations calendarOperations;

    @Override
    public Map<String, String> getComponentfilters() {
        final Map<String, String>  filters = new HashMap<String, String>();
        filters.put(Identifiers.SLING_RESOURCE_TYPE, Calendar.RESOURCE_TYPE_EVENT_REPLY);
        return filters;
    }

    @Override
    public String getUserIdentifierKey() {
        return Identifiers.AUTHORIZABLE_ID;
    }

    public CommentOperations getCommentOperations() {
        return calendarOperations;
    }

    @Override
    public UgcFilter getUgcFilter(String user) {
        return super.getUgcFilter(user);
    }
}
