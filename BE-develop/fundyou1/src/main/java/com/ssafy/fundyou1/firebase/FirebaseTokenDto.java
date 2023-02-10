package com.ssafy.fundyou1.firebase;

import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class FirebaseTokenDto {
    private String targetToken;
    private String title;
    private String body;
}