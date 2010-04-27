package net.processone.grailssession;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.context.ApplicationContext;

public class SessionListener implements HttpSessionListener {

	public static final String APPLICATION_CONTEXT_PUBLISHER_BEAN = "net.processone.grailssession.publisher";
	public static final String APPLICATION_CONTEXT_ACTIVATION_LISTENER = "net.processone.grailssession.activation";

	public void sessionCreated(HttpSessionEvent se) {

		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());
		Object bean = context.getBean(APPLICATION_CONTEXT_PUBLISHER_BEAN);

		if (bean != null) {
			EventPublisher publisher = (EventPublisher) bean;
			publisher.publishSessionCreated(se.getSession());
		}
		
		se.getSession().setAttribute(APPLICATION_CONTEXT_ACTIVATION_LISTENER, new ActivationListener());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		
		// se.getSession().removeAttribute(APPLICATION_CONTEXT_ACTIVATION_LISTENER);

		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());
		Object bean = context.getBean(APPLICATION_CONTEXT_PUBLISHER_BEAN);

		if (bean != null) {
			EventPublisher publisher = (EventPublisher) bean;
			publisher.publishSessionDestroyed(se.getSession());
		}

	}
}
