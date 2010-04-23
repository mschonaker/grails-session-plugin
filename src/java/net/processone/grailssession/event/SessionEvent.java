package net.processone.grailssession.event;

import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationEvent;

/**
 * The root of the hierarchy of events published by the plugin.
 * 
 * @author mschonaker
 */
public abstract class SessionEvent extends ApplicationEvent {

	protected HttpSession session;

	public SessionEvent(Object source, HttpSession session) {
		super(source);
		this.session = session;
	}

	public HttpSession getSession() {
		return session;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + "[" + session.toString() + "]";
	}
}
