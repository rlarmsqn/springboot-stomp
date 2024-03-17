package com.example.websocketstomp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendService {
    private final SimpMessagingTemplate message;
    public void sendDeviceData(String data) {
        try {
            log.info("전송 데이터 => {}", data);
            message.convertAndSend("thing/product/hi", data);
        } catch (Exception e) {
            log.error("error => {}", e);
        }
    }
}
