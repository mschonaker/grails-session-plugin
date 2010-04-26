package net.processone.grailssession;

import javax.servlet.http.HttpSession;

import net.processone.grailssession.event.SessionAttributeAddedEvent;
import net.processone.grailssession.event.SessionAttributeRemovedEvent;
import net.processone.grailssession.event.SessionAttributeReplacedEvent;
import net.processone.grailssession.event.SessionCreatedEvent;
import net.processone.grailssession.event.SessionDestroyedEvent;
import net.processone.grailssession.event.SessionDidActivateEvent;
import net.processone.grailssession.event.SessionWillPassivateEvent;

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

		eventPublisher.publishEvent(new SessionCreatedEvent(this, session));

	}

	public void publishSessionDestroyed(HttpSession session) {

		eventPublisher.publishEvent(new SessionDestroyedEvent(this, session));

	}

	public void publishSessionDidActivate(HttpSession session) {

		eventPublisher.publishEvent(new SessionDidActivateEvent(this, session));

	}

	public void publishSessionWillPassivate(HttpSession session) {

		eventPublisher
				.publishEvent(new SessionWillPassivateEvent(this, session));

	}
	
	public void publishAttributeAdded(HttpSession session, String name, Object value) {
		
		eventPublisher
		.publishEvent(new SessionAttributeAddedEvent(this, session, name, value));
		
	}
	
	public void publishAttributeRemoved(HttpSession session, String name, Object value) {
		
		eventPublisher
		.publishEvent(new SessionAttributeRemovedEvent(this, session, name, value));
		
	}
	
	public void publishAttributeReplaced(HttpSession session, String name, Object value) {
		
		eventPublisher
		.publishEvent(new SessionAttributeReplacedEvent(this, session, name, value));
		
	}
}
