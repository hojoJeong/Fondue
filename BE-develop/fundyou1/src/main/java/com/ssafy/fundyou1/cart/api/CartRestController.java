package com.ssafy.fundyou1.cart.api;

import com.ssafy.fundyou1.cart.dto.CartItemResponseDto;
import com.ssafy.fundyou1.cart.dto.CartRequestDto;
import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.cart.repository.CartRepository;
import com.ssafy.fundyou1.cart.service.CartService;
import com.ssafy.fundyou1.global.dto.BaseResponseBody;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.entity.Member;
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
@Api(tags = {"장바구니"})
public class CartRestController {

    @Autowired
    CartService cartService;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CartRepository cartRepository;

    // 장바구니에 아이템을 추가
    @PostMapping(value = "/cart")
    public @ResponseBody
    ResponseEntity addCartItem(@RequestBody @Valid CartRequestDto cartRequestDto){

        Long itemId = cartRequestDto.getItemId();

        Cart cart = cartService.findOneCartItem(SecurityUtil.getCurrentMemberId(), itemId);
        // 장바구니에 동일한 아이템이 있으면 장바구니 아이템 개수만 업데이트
        if (cart != null) {
            // 요청이 양수일 때
            if(cartRequestDto.getCount() >0){
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "아이템 개수 업데이트",cartService.updateAddCartItem(cartRequestDto, SecurityUtil.getCurrentMemberId())));
            }
            // 요청이 0일 때
            else {
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "아이템 개수 변동이 없습니다",cartService.updateAddCartItem(cartRequestDto, SecurityUtil.getCurrentMemberId())));
            }
            // 동일한 아이템이 없으면 장바구니에 아이템 등록해주기
        } else {
            // 반환값 장바구니 아이디
            Long cartId = cartService.addCart(cartRequestDto);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "장바구니에 아이템 등록", cartId));
        }
    }

    // 회원의 장바구니 아이템 조회
    @GetMapping("/cart/list")
    @ApiOperation(value = "장바구니 아이템 조회", notes = "회원의 장바구니 목록을 반환한다.")
    public ResponseEntity<List<CartItemResponseDto>> getAllCartItems() {

        List<CartItemResponseDto> cartList = cartService.findCartItemsByMemberId(SecurityUtil.getCurrentMemberId());

        return ResponseEntity.status(HttpStatus.OK).body(cartList);
    }

    // 회원의 장바구니 아이템 삭제. itemId를 전달받는다.
    @DeleteMapping("/cartItem/{itemId}")
    @ApiOperation(value = "장바구니 아이템 삭제", notes = "<strong>장바구니 목록 id를 받아</strong> 장바구니 목록에서 아이템을 삭제한다.")
    public ResponseEntity deleteCartItem(@PathVariable Long itemId){
        try {
            // 장바구니 아이템 찾기
            Cart cart = cartService.findOneCartItem(SecurityUtil.getCurrentMemberId(), itemId);
            // 장바구니 아이템이 있을때 삭제한 상품 개수 반환 -> 1
            if (cart != null) {
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "장바구니 해당 아이템 삭제 완료", cartService.deleteByItemId(itemId)));
            }
            // 장바구니에 해당 아이템이 없을때 삭제한 상품 개수 반환 -> 0
            else {
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "장바구니에 해당 아이템이 없습니다", cartService.deleteByItemId(itemId)));
            }
        } catch (Exception e){
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "fail", null));
        }
    }

    // 장바구니 내부에서 아이템 개수 수정 (아이템 추가)
    @PutMapping("/cart")
    @ApiOperation(value = "장바구니 아이템 개수 추가", notes = "<strong>장바구니 목록 아이템 id를 받아</strong> 장바구니 목록에서 아이템을 개수 추가한다.")
    public ResponseEntity updateCartItem(@RequestBody CartRequestDto cartRequestDto) {

        try {
            // 장바구니 내부에서 아이템 개수 추가
            int itemCount = cartService.updateAddCartItem(cartRequestDto, SecurityUtil.getCurrentMemberId());
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "장바구니 아이템 개수 추가", itemCount ));
        } catch (Exception e){
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "fail", null));
        }

    }

}
