package com.ssafy.fundyou1.cart.service;

import com.ssafy.fundyou1.cart.dto.*;
import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.cart.repository.CartRepository;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Autowired
    CartRepository cartRepository;


    // 장바구니에 상품을 담는 로직
    public Long addCart(CartRequestDto cartRequestDto) {

        Optional<Item> item = itemRepository.findById(cartRequestDto.getItemId()); //장바구니에 담을 상품 엔티티 조회

        Optional<Member> member = memberRepository.findById(SecurityUtil.getCurrentMemberId()); // 현재 로그인한 회원 엔티티 조회

      // 아니면은 CartItem 에 상품 저장
        Cart createCart = Cart.createCart( member.get(), item.get(), cartRequestDto.getCount());
        cartRepository.save(createCart);
        return createCart.getId();
    }


    //회원 id를 이용하여 카트 리스트를 조회하는 로직
    @Transactional(readOnly = true)
    public List<CartItemResponseDto> findCartItemsByCartId(Long memberId) {
        List<Cart> findCartItems = cartRepository.findAllByMember_Id(memberId);

        if ( findCartItems.size() != 0) {
            List<CartItemResponseDto> cartItemResponse = new ArrayList<>();
            for (Cart cart : findCartItems) {
                cartItemResponse.add(new CartItemResponseDto(cart.getItem(), cart.getMember(), cart.getCount()));
            }
            return cartItemResponse;
        }
        return null;

    }


    // 회원의 장바구니 아이템 삭제하는 로직
    @Transactional
    public List<CartItemResponseDto> deleteByCartItemId(Long id) {
        MemberResponseDto memberDto = memberService.getMyInfo();
        Long memberId = memberDto.getId();
        cartRepository.deleteCartItem(memberId, id);
        List<CartItemResponseDto> cartItemResponseDtos= findCartItemsByCartId(memberId);
        return cartItemResponseDtos;
    }

    // 회원의 장바구니 아이템 추가하는 로직
    @Transactional
    public int updateAddCartItem(CartRequestDto cartRequestDto, Long memberId) {
        Long id = cartRequestDto.getItemId();
        int count = cartRequestDto.getCount();

        cartRepository.updateAddCartItem(count,id, memberId);

        return count;
    }

    // 장바구니 물건 1개 찾는 로직 (회원아이디와 아이템 아이디)
    @Transactional
    public Cart findOneCartItem(Long memberId, Long Id){
        Cart cart = cartRepository.findCartItem(Id, memberId);
        return cart;
    }


}


