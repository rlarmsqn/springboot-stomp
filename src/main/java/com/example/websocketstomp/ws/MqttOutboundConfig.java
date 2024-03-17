package com.example.websocketstomp.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@RequiredArgsConstructor
public class MqttOutboundConfig {
    private final MqttFactoryConfig mqttFactoryConfig;

    @Bean("AdapterOutboundChannel")
    public MessageChannel outboundChannel(){
        return new DirectChannel();
    }

    @Bean("AdapterOutboundHandler")
    @ServiceActivator(inputChannel = "AdapterOutboundChannel")
    public MessageHandler outbound(){
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("asfaadafwaefaeawa", mqttFactoryConfig.isFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultQos(0);

        return messageHandler;
    }
}
