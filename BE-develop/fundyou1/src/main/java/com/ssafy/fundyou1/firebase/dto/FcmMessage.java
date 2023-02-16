package com.ssafy.fundyou1.firebase.dto;



import com.google.firebase.messaging.Notification;
import lombok.*;

import java.util.Map;

@Builder
@AllArgsConstructor
@Getter
public class FcmMessage {
    private boolean validateOnly;
    private Message message;

    @Builder
    @AllArgsConstructor
    @Getter @Setter
    public static class Message {
        private String token;
        private Notification notification;
        private Map<String, String> data;

    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Notification {
        private String title;
        private String body;
    }


}