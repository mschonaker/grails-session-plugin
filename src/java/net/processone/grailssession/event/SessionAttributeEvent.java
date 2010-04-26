package net.processone.grailssession.event;

import javax.servlet.http.HttpSession;

/**
 * Sub hierarchy for attributes events.
 * 
 * @author mschonaker
 */
public abstract class SessionAttributeEvent extends SessionEvent {

	protected String name;

	protected Object value;

	public SessionAttributeEvent(Object source, HttpSession session,
			String name, Object value) {
		super(source, session);
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[" + session.toString() + ", name="
				+ name + ", value=" + value.toString() + "]";
	}
}
