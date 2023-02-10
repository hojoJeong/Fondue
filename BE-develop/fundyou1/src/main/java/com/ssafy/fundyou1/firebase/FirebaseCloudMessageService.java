package com.ssafy.fundyou1.firebase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.net.HttpHeaders;
import com.google.gson.JsonParseException;
import com.ssafy.fundyou1.firebase.Repository.FirebaseRepository;
import com.ssafy.fundyou1.firebase.entity.Firebase;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class FirebaseCloudMessageService {

    private final String API_URL = "https://fcm.googleapis.com/v1/projects/fundyou-1674632553418/messages:send";

    private final ObjectMapper objectMapper;

    @Autowired
    FirebaseRepository firebaseRepository;

    @Value("${firebase.firebase_config_path}")
    private String googleApplicationCredentials;

    // 메세지 보내는 로직, 타겟토큰, 타이틀, 바디
    public void sendMessageTo(String targetToken, String title, String body) throws IOException {
        String message = makeMessage(targetToken, title, body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message,
                MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }


    // 알림 메세지 만드는 로직
    private String makeMessage(String targetToken, String title, String body) throws JsonParseException, JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                        .token(targetToken)
                        .notification(FcmMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build()
                        ).build()).validateOnly(false).build();

        return objectMapper.writeValueAsString(fcmMessage);
    }

    // 파이어 베이스 토큰 얻는곳
    private String getAccessToken() throws IOException {

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(googleApplicationCredentials).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    // 회원 파이어 베이스 토큰 저장, 토큰 갱신
    @Transactional
    private Long saveFirebase(String targetToken) {
        //멤버 아이디로 파이어베이스 정보 찾기
        Optional<Firebase> firebase = firebaseRepository.findByMemberId(SecurityUtil.getCurrentMemberId());
        // 만약에 디바이스 토큰을 등록한 회원이라면 새로 갱신
        if (firebase.isPresent()) {
            firebaseRepository.updateFirebase(firebase.get().getMemberId(), targetToken);
        } else {
            // 디바이스 토큰이 없는 회원 이라면 새로 등록 저장 해주기
            Firebase newFirebase = Firebase.builder()
                    .memberId(SecurityUtil.getCurrentMemberId())
                    .targetToken(targetToken)
                    .build();
            firebaseRepository.save(newFirebase);
        }
        return SecurityUtil.getCurrentMemberId();
    }



}