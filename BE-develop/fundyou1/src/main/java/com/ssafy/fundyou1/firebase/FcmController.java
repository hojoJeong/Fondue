package com.ssafy.fundyou1.firebase;

import com.ssafy.fundyou1.global.dto.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(path = "/api")
@Api(tags = {"FCM"})
public class FcmController {

    @Autowired
    FirebaseCloudMessageService firebaseCloudMessageService;

    // 파이어베이스 디바이스 토큰 저장
    @GetMapping("/fcm")
    @ApiOperation(value = "파이어베이스 토큰 저장", notes = "토큰 보내고 저장 성공하면 200 코드와 Success , id값 반환")
    public ResponseEntity getFirebase(@RequestBody FcmCreateRequestDto fcmCreateRequestDto) throws IOException {
        // 토큰 저장
        Long memberId = firebaseCloudMessageService.saveFirebase(fcmCreateRequestDto.getTargetToken());
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success",memberId));
    }

}
