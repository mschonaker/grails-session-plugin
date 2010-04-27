package net.processone.grailssession;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.context.ApplicationContext;

public class ActivationListener implements HttpSessionActivationListener, Serializable {

	private static final long serialVersionUID = 824798789660803076L
	;
	public static final String APPLICATION_CONTEXT_PUBLISHER_BEAN = "net.processone.grailssession.publisher";

	public void sessionDidActivate(HttpSessionEvent se) {

		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());
		Object bean = context.getBean(APPLICATION_CONTEXT_PUBLISHER_BEAN);

		if (bean != null) {
			EventPublisher publisher = (EventPublisher) bean;
			publisher.publishSessionDidActivate(se.getSession());
		}
	}

	public void sessionWillPassivate(HttpSessionEvent se) {

		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());
		Object bean = context.getBean(APPLICATION_CONTEXT_PUBLISHER_BEAN);

		if (bean != null) {
			EventPublisher publisher = (EventPublisher) bean;
			publisher.publishSessionWillPassivate(se.getSession());
		}
	}
}
