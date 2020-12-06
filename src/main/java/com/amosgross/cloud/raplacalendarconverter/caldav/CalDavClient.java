package com.amosgross.cloud.raplacalendarconverter.caldav;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.component.VEvent;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.osaf.caldav4j.CalDAVCollection;
import org.osaf.caldav4j.CalDAVConstants;
import org.osaf.caldav4j.exceptions.CalDAV4JException;
import org.osaf.caldav4j.methods.CalDAV4JMethodFactory;
import org.osaf.caldav4j.methods.HttpClient;
import org.osaf.caldav4j.model.request.CalendarQuery;
import org.osaf.caldav4j.util.GenerateQuery;

import java.util.Iterator;
import java.util.List;

public class CalDavClient {
    public void connect() throws CalDAV4JException {
        HttpClient httpClient = new HttpClient();

        httpClient.getHostConfiguration().setHost(CalDavCredentials.serverHost, 443, "https");
        String username = CalDavCredentials.serverUserName;
        UsernamePasswordCredentials httpCredentials = new UsernamePasswordCredentials(username, CalDavCredentials.serverPassword);
        httpClient.getState().setCredentials(AuthScope.ANY, httpCredentials);
        httpClient.getParams().setAuthenticationPreemptive(true);


        CalDAVCollection collection = new CalDAVCollection(
                CalDavCredentials.serverWebDavRoot,
                (HostConfiguration) httpClient.getHostConfiguration().clone(),
                new CalDAV4JMethodFactory(),
                CalDAVConstants.PROC_ID_DEFAULT
        );

        GenerateQuery gq = new GenerateQuery();
        // TODO you might want to adjust the date
        gq.setFilter("VEVENT [20200101T000000Z;20201231T000000Z] : STATUS!=CANCELLED");

        // Get the raw caldav query
         System.out.println("Query: "+ gq.prettyPrint());

        CalendarQuery calendarQuery = gq.generate();
        List<Calendar> calendars = collection.queryCalendars(httpClient, calendarQuery);

        for (Calendar calendar : calendars) {
            ComponentList componentList = calendar.getComponents().getComponents(Component.VEVENT);
            Iterator<VEvent> eventIterator = componentList.iterator();
            while (eventIterator.hasNext()) {
                VEvent ve = eventIterator.next();
                System.out.println("Event: " + ve.toString());
                System.out.println("\n\n");
            }
        }
    }
}
