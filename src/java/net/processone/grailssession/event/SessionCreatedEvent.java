package net.processone.grailssession.event;

import javax.servlet.http.HttpSession;

public class SessionCreatedEvent extends SessionEvent {

	public SessionCreatedEvent(Object source, HttpSession session) {
		super(source, session);
	}

}
