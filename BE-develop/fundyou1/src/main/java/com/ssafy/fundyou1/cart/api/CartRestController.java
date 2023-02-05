package com.ssafy.fundyou1.cart.api;

import com.ssafy.fundyou1.cart.dto.CartItemAddRequestDto;
import com.ssafy.fundyou1.cart.dto.CartItemAddResponseDto;
import com.ssafy.fundyou1.cart.dto.CartItemDto;
import com.ssafy.fundyou1.cart.service.CartService;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.item.entity.Item;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class CartRestController {

    private final CartService cartService;

    private final MemberService memberService;
    private final ItemRepository itemRepository;

    private final ItemService itemService;
    private final MemberRepository memberRepository;


    @PostMapping(value = "/cart")
    public @ResponseBody
    ResponseEntity order(@RequestBody @Valid CartItemDto cartItemDto){

        MemberResponseDto responseDto= memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));


        String username = responseDto.getUsername();
        Long cartItemId;

        try {
            cartItemId = cartService.addCart(cartItemDto, username); //dto -> entity
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); // 장바구니에 잘 안담겼으면 404
        }

        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK); // 장바구니에 상품이 잘 담기면 200
    }
}
