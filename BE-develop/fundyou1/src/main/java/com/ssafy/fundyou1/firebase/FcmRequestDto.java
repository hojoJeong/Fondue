package com.ssafy.fundyou1.firebase;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FcmRequestDto {
    private String title;

    private String body;

    private String targetToken;

    private String topicType;

    private String contentNo;




}
