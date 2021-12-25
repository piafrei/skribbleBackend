package com.montagsmaler.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //config.enableSimpleBroker("/topic");
        config.enableSimpleBroker("/updates");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*registry.addEndpoint("/game/{fleetId}/driver/{driverId}").setAllowedOrigins("*");
        registry.addEndpoint("/game/{fleetId}/driver/{driverId}").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/chat");
        registry.addEndpoint("/chat").withSockJS();*/
        //registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("http://localhost:4200","http://localhost:8080").withSockJS();
        registry.addEndpoint("/game-websocket-connection").setAllowedOrigins("http://localhost:4200","http://localhost:8080").withSockJS();
    }
}
