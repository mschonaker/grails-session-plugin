package net.processone.grailssession;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.context.ApplicationContext;

public class SessionListener implements HttpSessionListener {

	public static final String APPLICATION_CONTEXT_PUBLISHER_BEAN = "net.processone.grailssession.publisher";

	public void sessionCreated(HttpSessionEvent se) {

		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());
		Object bean = context.getBean(APPLICATION_CONTEXT_PUBLISHER_BEAN);

		if (bean != null) {
			EventPublisher publisher = (EventPublisher) bean;
			publisher.publishSessionCreated(se.getSession());
		}

	}

	public void sessionDestroyed(HttpSessionEvent se) {

		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());
		Object bean = context.getBean(APPLICATION_CONTEXT_PUBLISHER_BEAN);

		if (bean != null) {
			EventPublisher publisher = (EventPublisher) bean;
			publisher.publishSessionDestroyed(se.getSession());
		}

	}
}
