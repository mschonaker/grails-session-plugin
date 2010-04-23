
package net.processone.grailssession

import groovy.lang.GroovyObject;

import java.lang.reflect.Method
import java.lang.reflect.Field

import net.processone.grailssession.event.SessionCreatedEvent
import net.processone.grailssession.event.SessionDestroyedEvent
import net.processone.grailssession.event.SessionDidActivateEvent
import net.processone.grailssession.event.SessionEvent
import net.processone.grailssession.event.SessionWillPassivateEvent

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener
import org.springframework.util.ReflectionUtils

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
		
		if (sessionListener instanceof GroovyObject)
			doFireGroovy(closureName, session)
		else
			doFireJava(closureName, session)
	}
	
	protected doFireGroovy(String closureName, session) {

		GroovyObject gob = (GroovyObject) sessionListener
		
		gob.invokeMethod(closureName, session)
	}
	
	protected doFireJava(String closureName, session) {
		
		Method method = ReflectionUtils.findMethod(sessionListener.class, closureName)
		if(method) {
			method.invoke(sessionListener, [] as Object[] )
			return
		}
		method = ReflectionUtils.findMethod(sessionListener.class, closureName, [ Object.class ] as Class[])
		if (method)
			method.invoke(sessionListener, [ session ] as Object[] )

	}
}
