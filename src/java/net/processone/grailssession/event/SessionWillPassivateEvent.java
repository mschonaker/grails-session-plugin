package net.processone.grailssession.event;

import javax.servlet.http.HttpSession;

public class SessionWillPassivateEvent extends SessionEvent {

	public SessionWillPassivateEvent(Object source, HttpSession session) {
		super(source, session);
	}

}
