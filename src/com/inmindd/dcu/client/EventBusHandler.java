package com.inmindd.dcu.client;

import com.google.gwt.event.shared.EventHandler;

public interface EventBusHandler extends EventHandler {
	public void onUserChanged(EventBusEvent userEvent);
}