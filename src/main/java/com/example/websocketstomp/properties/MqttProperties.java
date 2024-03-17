package com.example.websocketstomp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.mqtt")
@Data
public class MqttProperties {
    private int port = 1883;
    private String host = "localhost";
}
