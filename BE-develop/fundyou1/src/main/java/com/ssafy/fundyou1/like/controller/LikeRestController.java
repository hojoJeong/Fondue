package com.ssafy.fundyou1.like.controller;


import com.ssafy.fundyou1.global.dto.BaseResponseBody;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.like.dto.LikeItemResponseDto;
import com.ssafy.fundyou1.like.dto.LikeRequestDto;
import com.ssafy.fundyou1.like.entity.Like;
import com.ssafy.fundyou1.like.service.LikeService;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
@Api(tags = {"찜"})
public class LikeRestController {

    @Autowired
    LikeService likeService;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    // 찜 목록에 아이템 아이디로 아이템 추가
    @PostMapping(value = "/like")
    @ResponseBody
    public ResponseEntity addLikeItem(@RequestBody @Valid LikeRequestDto likeRequestDto) {
        Long memberId = SecurityUtil.getCurrentMemberId();

        Long itemId = likeRequestDto.getItemId();

        Like like = likeService.findOneLikeItem(itemId,memberId);

        if(like != null) {
            List<LikeItemResponseDto> likeItemResponse = likeService.deleteByLikeItemId(itemId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 취소", likeItemResponse ));
        } else {
            Long likeId = likeService.addLike(likeRequestDto, memberId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 추가", likeId ));
        }
    }


}
