package com.inmindd.dcu.client;

import com.google.web.bindery.event.shared.Event;
import com.inmindd.dcu.shared.User;

public class EventBusEvent extends Event<EventBusHandler> {
	public static final Type<EventBusHandler> TYPE = new Type<EventBusHandler>();
	private User user;

	public EventBusEvent(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Type<EventBusHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EventBusHandler handler) {
		handler.onUserChanged(this);
	}

}