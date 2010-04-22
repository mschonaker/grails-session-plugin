package net.processone.grailssession.event;

import javax.servlet.http.HttpSession;

public class SessionDidActivateEvent extends SessionEvent {

	public SessionDidActivateEvent(Object source, HttpSession session) {
		super(source, session);
	}

}
