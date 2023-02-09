package com.ssafy.fundyou1.firebase;

import lombok.*;

@Getter
@NoArgsConstructor
public class FcmMessage {
    private boolean validate_only;
    private Message message;

    @Builder
    public FcmMessage(boolean validate_only, Message message) {
        this.validate_only = validate_only;
        this.message = message;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message {
        private Notification notification;

        private String token;

        private Data data;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Notification {
        private String title;

        private String body;

        private String image;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Data {
        private String topicType;

        private String contentNo;


    }

}