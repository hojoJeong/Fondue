package com.ssafy.fundyou1.like.controller;


import com.ssafy.fundyou1.cart.dto.CartItemResponseDto;
import com.ssafy.fundyou1.global.dto.BaseResponseBody;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.like.dto.LikeItemResponseDto;
import com.ssafy.fundyou1.like.dto.LikeRequestDto;
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
    private final ItemRepository itemRepository;

    // 회원 찜 목록에 아이템 아이디로 아이템 추가
    @PostMapping(value = "/like")
    @ResponseBody
    public ResponseEntity addLikeItem(@RequestBody @Valid LikeRequestDto likeRequestDto) {
        Long memberId = SecurityUtil.getCurrentMemberId();

        Long itemId = likeRequestDto.getItemId();

        Like like = likeService.findOneLikeItem(itemId,memberId);

        if(like != null) {
            List<LikeItemResponseDto> likeItemResponse = likeService.deleteByLikeItemId(itemId);
            likeService.updateIsFavorited(itemId, false, memberId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 취소", likeItemResponse ));
        } else {
            Long likeId = likeService.addLike(likeRequestDto, memberId);
            likeService.updateIsFavorited(itemId, true, memberId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 추가", likeId ));
        }
    }

    // 회원 찜 리스트 전체 조회
    @GetMapping("/like/list")
    @ApiOperation(value = "찜 아이템 조회", notes = "회원의 장바구니 목록을 반환한다.")
    public ResponseEntity<List<Item>> getAllLikeItems() {

        List<Item> iikeItemList = likeService.findAllItemLike(true);

        return ResponseEntity.status(HttpStatus.OK).body(iikeItemList);
    }



}
