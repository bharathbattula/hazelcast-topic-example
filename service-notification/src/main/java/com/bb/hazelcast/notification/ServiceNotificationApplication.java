package com.bb.hazelcast.notification;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ServiceNotificationApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceNotificationApplication.class);

	@Qualifier("hazelcastInstance")
	@Autowired
	HazelcastInstance hazelcastInstance;

	public static void main(String[] args) {
		SpringApplication.run(ServiceNotificationApplication.class, args);
	}

	@PostConstruct
	private void init() {
		ITopic<String> messageTopic =  hazelcastInstance.getTopic("notification-topic");
		messageTopic.addMessageListener(new CustomMessageListener());
		LOGGER.info("Subscribed");
	}

	private static class CustomMessageListener implements MessageListener<String> {
		public void onMessage(Message<String> m) {
			LOGGER.info("Received: " + m.getMessageObject());
		}
	}

}
