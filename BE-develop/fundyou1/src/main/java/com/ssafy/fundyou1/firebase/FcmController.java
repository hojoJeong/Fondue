package com.ssafy.fundyou1.firebase;

import com.ssafy.fundyou1.global.dto.BaseResponseBody;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(path = "/api")
@Api(tags = {"fcm"})
public class FcmController {

    @Autowired
    FirebaseCloudMessageService firebaseCloudMessageService;

    // 파이어베이스 디바이스 토큰 저장
    @GetMapping("/fcm")
    public ResponseEntity getFirebase() throws IOException {
        // 1. 파이어베이스 토큰 얻기
        String targetToken = firebaseCloudMessageService.getAccessToken();
        // 2. 토큰 저장
        firebaseCloudMessageService.saveFirebase(targetToken);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success",targetToken));
    }

}
