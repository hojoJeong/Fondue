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
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class CartRestController {

    @Autowired
    CartService cartService;

    @Autowired
    private final MemberService memberService;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CartRepository cartRepository;


    @PostMapping(value = "/cart")
    public @ResponseBody
    ResponseEntity cart(@RequestBody @Valid CartRequestDto cartRequestDto){

        MemberResponseDto responseDto= memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Long memberId = responseDto.getId();

        String username = responseDto.getUsername();

        Long itemId = cartRequestDto.getItemId();

        Cart cart = cartService.findOneCartItem(memberId, itemId);

        if (cart != null) {
            int addcount = cartService.updateAddCartItem(cartRequestDto, memberId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success", addcount ));
        } else {
            Long cartId = cartService.addCart(cartRequestDto, username);
            return new ResponseEntity<Long>(cartId, HttpStatus.OK); // 장바구니에 상품이 잘 담기면 200

        }

    }

    @GetMapping("/cart/list")
    @ApiOperation(value = "장바구니 아이템 조회", notes = "회원의 장바구니 목록을 반환한다.")
    public ResponseEntity<List<Cart>> getAllCartItems() {
//        Logger logger = LoggerFactory.getLogger(getClass());

        MemberResponseDto responseDto= memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Long memberId = responseDto.getId();

        List<Cart> cartList = cartService.findMemberCartAll(memberId);
//        logger.error("null test: " + cartList);



        return ResponseEntity.status(HttpStatus.OK).body(cartList);
    }

    @DeleteMapping("/cartItem/{itemid}")
    @ApiOperation(value = "장바구니 아이템 삭제", notes = "<strong>장바구니 목록 id를 받아</strong> 장바구니 목록에서 아이템을 삭제한다.")
    public ResponseEntity deleteList(@PathVariable Long itemid){

        MemberResponseDto meDto = memberService.getMyInfo();

        Member member = memberRepository.findByUsername(meDto.getUsername());

        Long memberId = member.getId();

        try {
            List<CartItemResponseDto> cartItemResponseDtos = cartService.deleteByCartItemId(itemid);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success", cartItemResponseDtos ));
        } catch (Exception e){
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "fail", null));
        }

    }

    @PutMapping("/cart")
    @ApiOperation(value = "장바구니 아이템 개수 추가", notes = "<strong>장바구니 목록 아이템 id를 받아</strong> 장바구니 목록에서 아이템을 개수 추가한다.")
    public ResponseEntity updateItem(@RequestBody CartRequestDto cartRequestDto) {

        MemberResponseDto meDto = memberService.getMyInfo();

        Member member = memberRepository.findByUsername(meDto.getUsername());

        Long memberId = member.getId();

        try {
            int addcount = cartService.updateAddCartItem(cartRequestDto, memberId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success", addcount ));
        } catch (Exception e){
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "fail", null));
        }

    }


}
