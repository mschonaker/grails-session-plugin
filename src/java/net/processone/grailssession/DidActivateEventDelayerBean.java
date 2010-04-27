package net.processone.grailssession;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.LinkedList;

/**
 * @author Martín Schonaker
 * @author Santiago Martínez
 */
public class DidActivateEventDelayerBean implements ApplicationListener {

	public void onApplicationEvent(final ApplicationEvent event) {
		
		if (!(event instanceof ContextRefreshedEvent))
			return;
		 
		ApplicationContext context = ((ContextRefreshedEvent)event).getApplicationContext();

		if (!(context instanceof WebApplicationContext)) 
			return;
		
		ServletContext servletContext = ((WebApplicationContext)context).getServletContext();
		
		LinkedList<HttpSessionEvent> queue = null;
		
		synchronized(servletContext) {
				Object queueAttribute = servletContext.getAttribute(ActivationListener.APPLICATION_CONTEXT_EVENT_QUEUE);

				if (queueAttribute == null)
					return;
				
				queue = ((LinkedList<HttpSessionEvent>) queueAttribute);
				
				if (queue.size() == 0)
					return;
				
				servletContext.removeAttribute(ActivationListener.APPLICATION_CONTEXT_EVENT_QUEUE);
		}
			
		EventPublisher publisher = (EventPublisher) context.getBean(ActivationListener.APPLICATION_CONTEXT_PUBLISHER_BEAN);
		
		if (publisher == null)
			throw new IllegalStateException("Publisher not found in spring context"); 
		
		for (HttpSessionEvent e : queue) {
            publisher.publishSessionDidActivate(e.getSession());
		}
	}
}
