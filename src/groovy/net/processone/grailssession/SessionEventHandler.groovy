
package net.processone.grailssession

import groovy.lang.GroovyObject;

import java.lang.reflect.Method
import java.lang.reflect.Field

import net.processone.grailssession.event.SessionAttributeEvent
import net.processone.grailssession.event.SessionAttributeAddedEvent
import net.processone.grailssession.event.SessionAttributeRemovedEvent
import net.processone.grailssession.event.SessionAttributeReplacedEvent
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
public class SessionEventHandler implements ApplicationListener {
	
	// Names like in Bootstrap.groovy
	public static final String CREATED_CLOSURE_NAME = "init"
	public static final String DESTROYED_CLOSURE_NAME = "destroy"
	public static final String DID_ACTIVATE_CLOSURE_NAME = "activate"
	public static final String WILL_PASSIVATE_CLOSURE_NAME = "passivate"
	public static final String ATTRIBUTE_ADDED_CLOSURE_NAME = "attributeAdded"
	public static final String ATTRIBUTE_REMOVED_CLOSURE_NAME = "attributeRemoved"
	public static final String ATTRIBUTE_REPLACED_CLOSURE_NAME = "attributeReplaced"
	
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
			
		} else if (event instanceof SessionAttributeEvent) {
			
			SessionAttributeEvent v = (SessionAttributeEvent) event
			
			if (event instanceof SessionAttributeAddedEvent) {
				
				doFireAtt(ATTRIBUTE_ADDED_CLOSURE_NAME, v.getSession(), v.getName(), v.getValue())
				
			} else if (event instanceof SessionAttributeRemovedEvent) {
				
				doFireAtt(ATTRIBUTE_REMOVED_CLOSURE_NAME, v.getSession(), v.getName(), v.getValue())
				
			} else if (event instanceof SessionAttributeReplacedEvent) {
				
				doFireAtt(ATTRIBUTE_REPLACED_CLOSURE_NAME, v.getSession(), v.getName(), v.getValue())
				
			}
		}
	}
	
	protected doFire(String closureName, session) {
		
		if (!(sessionListener instanceof GroovyObject))
			return
		
		GroovyObject gob = (GroovyObject) sessionListener
		
		try {
			gob.invokeMethod(closureName, session)
		}
		catch (MissingMethodException e) {
			// All of the methods are optional.
		}
	}
	
	protected doFireAtt(String closureName, session, name, value) {
		
		if (!(sessionListener instanceof GroovyObject))
			return
		
		GroovyObject gob = (GroovyObject) sessionListener
		
		try {
			gob.invokeMethod(closureName, [ session, name, value] )
		}
		catch (MissingMethodException e) {
			// All of the methods are optional.	
		}
	}
}
