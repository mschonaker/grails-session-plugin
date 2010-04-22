package net.processone.grailssession.event;

import javax.servlet.http.HttpSession;

public class SessionDestroyedEvent extends SessionEvent {

	public SessionDestroyedEvent(Object source, HttpSession session) {
		super(source, session);
	}

}
