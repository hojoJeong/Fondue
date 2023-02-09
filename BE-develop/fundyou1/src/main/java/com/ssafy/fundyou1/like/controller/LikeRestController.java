package com.ssafy.fundyou1.like.controller;


import com.ssafy.fundyou1.global.dto.BaseResponseBody;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.like.dto.LikeItemResponseDto;
import com.ssafy.fundyou1.like.entity.Like;
import com.ssafy.fundyou1.like.service.LikeService;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
@Api(tags = {"찜"})
public class LikeRestController {

    @Autowired
    LikeService likeService;


    // 회원 찜 목록에 아이템 아이디로 아이템 추가
    @PostMapping(value = "/like/{id}")
    @ApiOperation(value = "아이템 상세 1개 조회", notes = "아이템 상세를 아이템 id값으로 조회합니다")
    @ResponseBody
    public ResponseEntity addLikeItem(@PathVariable Long id ) {
        // 회원 아이디
        Long memberId = SecurityUtil.getCurrentMemberId();

        // 아이템 아이디랑, 회원아이디로 찾기
        Like like = likeService.findOneLikeItem(id,memberId);

        if(like != null) {
            List<LikeItemResponseDto> likeItemResponse = likeService.deleteByLikeItemId(id);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 취소", likeItemResponse ));
        } else {
            Long likeId = likeService.addLike(id, memberId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 추가", likeId ));
        }
    }

    // 회원 찜 리스트 전체 조회
    @GetMapping("/like/list")
    @ApiOperation(value = "찜 아이템 조회", notes = "회원의 장바구니 목록을 반환한다.")
    public ResponseEntity<List<LikeItemResponseDto>> getAllLikeItems() {

        List<LikeItemResponseDto> likeItemList = likeService.findLikeByMemberId(SecurityUtil.getCurrentMemberId());

        return ResponseEntity.status(HttpStatus.OK).body(likeItemList);
    }



}
