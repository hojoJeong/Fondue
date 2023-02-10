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

    @PostMapping("/fcm")
    public ResponseEntity getFirebase(@RequestBody FcmCreateRequestDto requestDTO) throws IOException {
        

        return ResponseEntity.ok().build();
    }


//
//      firebaseCloudMessageService.sendMessageTo(
//              requestDTO.getTargetToken(),
//              requestDTO.getTitle(),
//              requestDTO.getBody());

}
