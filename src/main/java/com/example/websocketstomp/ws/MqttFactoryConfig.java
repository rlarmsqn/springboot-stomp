package com.example.websocketstomp.ws;

import com.example.websocketstomp.properties.MqttProperties;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;

@Configuration
@RequiredArgsConstructor
public class MqttFactoryConfig {

    private final MqttProperties mqttProperties;

    private MqttConnectOptions isOptions() {
        String url = "tcp://" + mqttProperties.getHost() + ":" + mqttProperties.getPort();

        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{url});
        options.setCleanSession(true);
        return options;
    }

    @Bean("AdapterMqttFactorySetting")
    public DefaultMqttPahoClientFactory isFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(isOptions());
        return factory;
    }
}
