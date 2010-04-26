package net.processone.grailssession.event;

import javax.servlet.http.HttpSession;

public class SessionAttributeAddedEvent extends SessionAttributeEvent {

	public SessionAttributeAddedEvent(Object source, HttpSession session,
			String name, Object value) {
		super(source, session, name, value);
	}
}
