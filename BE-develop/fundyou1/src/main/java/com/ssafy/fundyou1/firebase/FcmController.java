package com.ssafy.fundyou1.firebase;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping(path = "/api")
@Api(tags = {"fcm"})
public class FcmController {

    @Autowired
    private FcmService fcmService;

    @PostMapping(value = "/fcm")
    public ResponseEntity<?> pushMessage(FcmRequestDto requestDto) throws IOException {
        fcmService.sendMessageTo(
                requestDto.getTargetToken(),
                requestDto.getTitle(),
                requestDto.getBody(),
                requestDto.getTopicType(),
                requestDto.getContentNo()

        );
        return  ResponseEntity.ok().build();
    }
}
