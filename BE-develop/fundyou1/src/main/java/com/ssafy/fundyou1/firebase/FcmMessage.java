package com.ssafy.fundyou1.firebase;



import lombok.*;

@Builder
@AllArgsConstructor
@Getter
public class FcmMessage {
    private boolean validateOnly;
    private Message message;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message {
        private Data data;
        private String token;
    }

    @Builder
    @AllArgsConstructor
    @Getter @Setter
    public static class Data {
        private boolean isHost;
        private String title;
        private String body;
    }
}