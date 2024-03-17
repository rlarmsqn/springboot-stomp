package com.example.websocketstomp.ws;

import org.springframework.context.annotation.DependsOn;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@MessagingGateway(defaultRequestChannel = "AdapterOutboundChannel")
@DependsOn(value = {"AdapterOutboundChannel"})
public interface GatewayConfig {
    void publish(byte[] message, @Header(MqttHeaders.TOPIC) String topic);
}
