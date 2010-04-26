package net.processone.grailssession.event;

import javax.servlet.http.HttpSession;

public class SessionAttributeReplacedEvent extends SessionAttributeEvent {

	public SessionAttributeReplacedEvent(Object source, HttpSession session,
			String name, Object value) {
		super(source, session, name, value);
	}

}
