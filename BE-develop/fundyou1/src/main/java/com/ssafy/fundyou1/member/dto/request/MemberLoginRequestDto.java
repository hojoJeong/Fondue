package com.ssafy.fundyou1.member.dto.request;

import com.ssafy.fundyou1.auth.domain.Authority;
import com.ssafy.fundyou1.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginRequestDto {
    @ApiModelProperty(position = 1, notes = "유저네임아이디", example = "eeee")
    private String loginId;


    @ApiModelProperty(position = 2, notes = "비밀번호", example = "Zjsxlsb123!!")
    private String password;



    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(loginId, password);
    }
}
