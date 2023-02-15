package com.ssafy.fundyou1.firebase;

import com.ssafy.fundyou1.global.dto.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@Api(tags = {"FCM"})
public class FcmController {

    @Autowired
    FirebaseCloudMessageService firebaseCloudMessageService;

    // 파이어베이스 디바이스 토큰 저장
    @PostMapping("/fcm")
    @ApiOperation(value = "파이어베이스 토큰 저장", notes = "토큰 보내고 저장 성공하면 200 코드와 Success , id값 반환")
    public ResponseEntity setFirebaseToken(@RequestBody FcmCreateRequestDto fcmCreateRequestDto) throws IOException {
        // 토큰 저장
        Long memberId = firebaseCloudMessageService.saveFirebase(fcmCreateRequestDto.getTargetToken());
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success",memberId));
    }

    // 알림 상태값 수정
    @PutMapping("/fcm")
    @ApiOperation(value = "회원 알림 상태 변화", notes = "상태를 반환해 줍니다.")
    public ResponseEntity changeFirebaseStatus() throws IOException {
        // 토큰 저장
        Boolean status = firebaseCloudMessageService.changeFirebaseStatus();
        if (status != null){
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "알림 상태 변경", status));
        }
        else {
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "토큰 정보가 없습니다",  null));
        }
    }

    @GetMapping("/fcm")
    @ApiOperation(value = "회원 알림 상태 조회", notes = "상태를 반환해 줍니다.")
    public ResponseEntity getFirebaseStatus() throws IOException {
        // 알림 상태값 조회
        Boolean status = firebaseCloudMessageService.getFirebaseStatus();
        // 알림 상태값을 찾았으면
        if(status != null){
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원 알림 상태", status));
        }
        // 알림 상태값이 없다면
        else {
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "회원 알림 상태를 조회할 수 없습니다.",  null));
        }

    }

}
