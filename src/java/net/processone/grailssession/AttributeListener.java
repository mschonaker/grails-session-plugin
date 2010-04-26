package net.processone.grailssession;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.context.ApplicationContext;

public class AttributeListener implements HttpSessionAttributeListener {
	
	public static final String APPLICATION_CONTEXT_PUBLISHER_BEAN = "net.processone.grailssession.publisher";

	public void attributeAdded(HttpSessionBindingEvent se) {

		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());
		Object bean = context.getBean(APPLICATION_CONTEXT_PUBLISHER_BEAN);

		if (bean != null) {
			EventPublisher publisher = (EventPublisher) bean;
			publisher.publishAttributeAdded(se.getSession(), se.getName(), se.getValue());
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {

		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());
		Object bean = context.getBean(APPLICATION_CONTEXT_PUBLISHER_BEAN);

		if (bean != null) {
			EventPublisher publisher = (EventPublisher) bean;
			publisher.publishAttributeRemoved(se.getSession(), se.getName(), se.getValue());
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {

		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());
		Object bean = context.getBean(APPLICATION_CONTEXT_PUBLISHER_BEAN);

		if (bean != null) {
			EventPublisher publisher = (EventPublisher) bean;
			publisher.publishAttributeReplaced(se.getSession(), se.getName(), se.getValue());
		}
	}
}
