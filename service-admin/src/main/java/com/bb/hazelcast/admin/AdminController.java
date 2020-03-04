package com.bb.hazelcast.admin;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	ITopic<String> messageTopic;

	@Autowired
	public AdminController(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
		messageTopic = hazelcastInstance.getTopic("notification-topic");
	}

	@PostMapping("/post")
	public ResponseEntity notify(@RequestBody  String message) {
		messageTopic.publish(message);
		LOGGER.info("Published : "+ message);
		return ResponseEntity.ok("Message published");
	}
}
