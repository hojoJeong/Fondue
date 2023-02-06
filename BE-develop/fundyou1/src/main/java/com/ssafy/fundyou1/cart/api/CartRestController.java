package com.ssafy.fundyou1.cart.api;

import com.ssafy.fundyou1.cart.dto.CartItemResponseDto;
import com.ssafy.fundyou1.cart.dto.CartRequestDto;
import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.cart.entity.CartItem;
import com.ssafy.fundyou1.cart.repository.CartRepository;
import com.ssafy.fundyou1.cart.service.CartService;
import com.ssafy.fundyou1.global.dto.BaseResponseBody;
import com.ssafy.fundyou1.global.dto.ResponseDto;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.item.service.ItemService;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class CartRestController {

    private final CartService cartService;

    private final MemberService memberService;

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;


    @PostMapping(value = "/cart")
    public @ResponseBody
    ResponseEntity cart(@RequestBody @Valid CartRequestDto cartRequestDto){

        MemberResponseDto responseDto= memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        String username = responseDto.getUsername();

        Long cartItemId;

        try {
            cartItemId = cartService.addCart(cartRequestDto, username); //dto -> entity
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); // 장바구니에 잘 안담겼으면 404
        }

        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK); // 장바구니에 상품이 잘 담기면 200
    }

    @GetMapping("/cart/list")
    @ApiOperation(value = "장바구니 아이템 조회", notes = "회원의 장바구니 목록을 반환한다.")
    public ResponseEntity<List<CartItemResponseDto>> getAllCartItems() {

        MemberResponseDto meDto = memberService.getMyInfo();

        Member member = memberRepository.findByUsername(meDto.getUsername());

        Long memberId = member.getId();

        // cart 없으면 if문처리해주고싶은데 어떻게 할지 모르겠습니다..

        Cart cart = cartRepository.findByMemberId(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(cartService.findCartItemsByCartId(member.getId()));
    }

    @DeleteMapping("/cartItem/{cartItemId}")
    @ApiOperation(value = "장바구니 아이템 삭제", notes = "<strong>장바구니 목록 id를 받아</strong> 장바구니 목록에서 아이템을 삭제한다.")
    public ResponseEntity deleteList(Long cartItemId){

        MemberResponseDto meDto = memberService.getMyInfo();

        Member member = memberRepository.findByUsername(meDto.getUsername());

        Long memberId = member.getId();

        try{
            String messege = cartService.deleteByCartItemId(cartItemId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success",messege ));
        }catch (Exception e){
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "fail", null));
        }

    }



    //
//    @DeleteMapping(value = "/cartItem/{cartItemId}")
//    public @ResponseBody ResponseEntity deleteCartItem(@PathVariable("cartItemId") Long cartItemId, Principal principal){
//
//        if(!cartService.validateCartItem(cartItemId, principal.getName())){// cartService 에서 검증 로직 발동!
//            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
//        }
//
//        cartService.deleteCartItem(cartItemId); // 다 되면은 삭제
//        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK); // 응답 리턴
//    }
//
//

}
