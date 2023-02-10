package com.ssafy.fundyou1.firebase;


import com.ssafy.fundyou1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class FireBaseMessageBridge {

    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final MemberRepository memberRepository;

    /**
     * 특정 유저 SEQ를 통하여 해당 유저에게 제목,메세지를 전달한다.
     */
    @Transactional
    public int sendMessageByFCM(Long memberId,String title,String content) {
        Optional<UserEntity> userEntity = userRepo.findById(userSeq);
        if(!userEntity.isPresent()) {
            return 0;
        }
        return sendMessageByFCM(userEntity.get(),title,content);
    }
    @Transactional
    public int sendMessageByFCM(Long memberId, String title,String contetent) {

        if(userEntity == null) {
            return 0;
        }

        String targetToken = userEntity.getFcmToken();
        if(targetToken == null) {
            return 0;
        }

        try {
            firebaseMessage.sendMessageTo(targetToken, title, contetent);
        } catch ( FCMTokenUnValidException e) {

            log.debug("전송 실패 , 서버 응답 : 404 | 400 -reason : {}",e.getMessage());
            log.debug(e.getErrorResponse());
            log.debug("User_SEQ : {} 의  토큰 필드를 초기화 합니다.",userEntity.getUserSeq());
            userEntity.setFcmToken(null); //여기 왔다는 것은 그 토큰은 애초에 못쓴다는 것이다.
        }catch(IOException e) {
            e.printStackTrace();
        }

        return 1;
    }
}
