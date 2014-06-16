package com.inmindd.dcu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;

public class EventBus {
	private EventBus() {
	}

	private static final SimpleEventBus INSTANCE = GWT
			.create(SimpleEventBus.class);
	
	private static final SimpleEventBus REQUEST_INSTANCE = GWT
			.create(SimpleEventBus.class);

	public static SimpleEventBus get() {
		return INSTANCE;
	}
	
	public static SimpleEventBus getRequestInstance() {
		return REQUEST_INSTANCE;
	}
}