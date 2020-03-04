package com.bb.hazelcast.notification;

		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.context.annotation.Configuration;
		import org.springframework.web.socket.config.annotation.EnableWebSocket;
		import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
		import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private WebSocketHandler webSocketHandler;

	@Override
	public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
		registry
				.addHandler(this.webSocketHandler, "/notify")
				.setAllowedOrigins("*");
	}
}