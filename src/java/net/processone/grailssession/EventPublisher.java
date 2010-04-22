package net.processone.grailssession;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Bridges HTTP session activation listener with Spring Framework events. In
 * order to be able to publish events, we need a bean (loaded by Spring) capable
 * of publishing events. i.e. aware of the context event publisher.
 * <p>
 * Also this class not only have access to the Spring event publisher, but
 * concentrates events from the standard session listener (init/destroy) and the
 * session activation listener (activate, passivate).
 * 
 * @author mschonaker
 */
public class EventPublisher implements ApplicationEventPublisherAware {

	protected ApplicationEventPublisher eventPublisher;

	public void setApplicationEventPublisher(
			ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void publishSessionCreated(HttpSession session) {

	}

	public void publishSessionDestroyed(HttpSession session) {

	}

	public void publishSessionDidActivate(HttpSession session) {

	}

	public void publishSessionWillPassivate(HttpSession session) {

	}
}
