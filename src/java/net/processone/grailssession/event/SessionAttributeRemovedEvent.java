package net.processone.grailssession.event;

import javax.servlet.http.HttpSession;

public class SessionAttributeRemovedEvent extends SessionAttributeEvent {

	public SessionAttributeRemovedEvent(Object source, HttpSession session,
			String name, Object value) {
		super(source, session, name, value);
	}

}
