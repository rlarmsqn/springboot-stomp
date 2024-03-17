package com.example.websocketstomp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApplicationInitializer {
    @Qualifier("AdapterInboundSetting")
    private final MessageProducer producer;

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent() {
        if(producer instanceof MqttPahoMessageDrivenChannelAdapter adapter) {
            adapter.addTopic("thing/product/#");
        }
    }
}
