package net.processone.grailssession;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.ServletContext;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.context.ApplicationContext;

public class ActivationListener implements HttpSessionActivationListener, Serializable {

	private static final long serialVersionUID = 824798789660803076L;
	
	public static final String APPLICATION_CONTEXT_PUBLISHER_BEAN = "net.processone.grailssession.publisher";
	
	public static final String APPLICATION_CONTEXT_EVENT_QUEUE = "net.processone.grailssession.eventqueue";

	public void sessionDidActivate(HttpSessionEvent se) {
		
		ServletContext context = se.getSession().getServletContext();
		
		//TODO 'queue' should be sinchronized out side of synchronized context block, maybe using ConcurrentLinkedQueue
		synchronized (context) {
			
			LinkedList<HttpSessionEvent> queue = (LinkedList<HttpSessionEvent>)context.getAttribute(APPLICATION_CONTEXT_EVENT_QUEUE);
			
			if (queue == null) { 
				queue = new LinkedList<HttpSessionEvent>();
				context.setAttribute(APPLICATION_CONTEXT_EVENT_QUEUE, queue);
			}
			queue.add(se);
		}
	}

	public void sessionWillPassivate(HttpSessionEvent se) {

	}
}
