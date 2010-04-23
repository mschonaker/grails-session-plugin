package net.processone.grailssession

import net.processone.grailssession.event.SessionCreatedEvent
import net.processone.grailssession.event.SessionDestroyedEvent
import net.processone.grailssession.event.SessionDidActivateEvent
import net.processone.grailssession.event.SessionEvent
import net.processone.grailssession.event.SessionWillPassivateEvent

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener

/**
 * This class listens for spring-propagated session events in the Groovy side.
 * Then performs a nice downcast of classes to fire the exact method in 
 * the sessionListener object attached.
 *  
 * @author mschonaker
 */
class SessionEventHandler implements ApplicationListener {
	
	// Names like in Bootstrap.groovy
	public static final String CREATED_CLOSURE_NAME = "init"
	public static final String DESTROYED_CLOSURE_NAME = "destroy"
	public static final String DID_ACTIVATE_CLOSURE_NAME = "activate"
	public static final String WILL_PASSIVATE_CLOSURE_NAME = "passivate"
	
	def sessionListener
	
	void onApplicationEvent(final ApplicationEvent event) {
		
		// Nothing attached, there should be something, however.
		if (!sessionListener)
			return
		
		// Listens only for session events. 
		if (!(event instanceof SessionEvent))
			return
		
		SessionEvent e = (SessionEvent) event
		
		// Downcast of events.
		if (event instanceof SessionCreatedEvent) {
			
			doFire(CREATED_CLOSURE_NAME, e.getSession())
			
		} else if (event instanceof SessionDestroyedEvent) {
			
			doFire(DESTROYED_CLOSURE_NAME, e.getSession())
			
		} else if (event instanceof SessionDidActivateEvent) {
			
			doFire(DID_ACTIVATE_CLOSURE_NAME, e.getSession())
			
		} else if (event instanceof SessionWillPassivateEvent) {
			
			doFire(WILL_PASSIVATE_CLOSURE_NAME, e.getSession())
			
		}
	}
	
	protected doFire(String closureName, session) {
		
		// TODO closure/method binding should be performed in the setSessionListener() method. 
		
		println '.--------------------------------------------------'
		println "$closureName($session) bound to $sessionListener"
		println '.--------------------------------------------------'
		
	}
}
