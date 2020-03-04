package com.bb.hazelcast.notification;

import com.hazelcast.core.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationMessageListener implements com.hazelcast.core.MessageListener<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationMessageListener.class);

	@Autowired
	private WebSocketHandler socketHandler;

	public void onMessage(Message<String> m) {
		LOGGER.info("Received: " + m.getMessageObject());

		socketHandler.sendMessage(m.getMessageObject());
	}
}
