package com.example.websocketstomp.ws;

import com.example.websocketstomp.SendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.UUID;

@Configuration("AdapterInbound")
@RequiredArgsConstructor
@Slf4j
public class MqttInboundConfig {
    private final MqttFactoryConfig mqttFactoryConfig;

    private final SendService sendService;

    @Bean("AdapterInboundChannel")
    public MessageChannel inboundChannel() { return new DirectChannel(); }

    @Bean("AdapterInboundSetting")
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                UUID.randomUUID().toString(),
                mqttFactoryConfig.isFactory());
        adapter.setCompletionTimeout(5000);
        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        converter.setPayloadAsBytes(true);
        adapter.setConverter(converter);
        adapter.setQos(0);

        adapter.setOutputChannel(inboundChannel());
        return adapter;
    }

    @Bean("AdapterInboundHandler")
    @ServiceActivator(inputChannel = "AdapterInboundChannel")
    public MessageHandler inboundHandler() {
        return message -> {
            System.out.println("?? => " + message);
            String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
            try {
                String msg = new String((byte[]) message.getPayload());
                sendService.sendDeviceData(msg);
                log.debug("Receive topic : {}", topic);
            } catch (Exception e) {
                log.error("error => {}", e);
            }
        };
    }

}
