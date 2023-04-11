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
    CartRepository cartRepository;


    // 장바구니에 아이템을 담는 로직 -> 동일한 아이템이 없을 때
    public Long addCart(CartRequestDto cartRequestDto) {

        Optional<Item> item = itemRepository.findById(cartRequestDto.getItemId()); //장바구니에 담을 아이템 조회

        Optional<Member> member = memberRepository.findById(SecurityUtil.getCurrentMemberId()); // 현재 로그인한 회원 조회

      // 동일한 아이템이 없으면 장바구니에 아이템 추가해주기
        Cart createCart = Cart.createCart(member.get(), item.get(), cartRequestDto.getCount());
        cartRepository.save(createCart);
        return createCart.getId();
    }


    //회원 id를 이용하여 장바구니 리스트를 조회하는 로직
    @Transactional(readOnly = true)
    public List<CartItemResponseDto> findCartItemsByMemberId(Long memberId) {
        List<Cart> findCartItems = cartRepository.findAllByMember_Id(memberId);

        if ( findCartItems.size() != 0) {
            List<CartItemResponseDto> cartItemResponse = new ArrayList<>();
            for (Cart cart : findCartItems) {
                cartItemResponse.add(new CartItemResponseDto(cart.getItem(), cart.getMember(), cart.getCount()));
            }
            return cartItemResponse;
        }
        return new ArrayList();
    }


    // 회원의 장바구니에서 아이템 삭제하는 로직
    @Transactional
    public Integer deleteByItemId(Long itemId) {
        return cartRepository.deleteCartItem(SecurityUtil.getCurrentMemberId(), itemId);

    }

    // 회원의 장바구니 아이템 개수를 추가하는 로직 -> 동일한 아이템이 있을 때 / 반환값: 업데이트한 값 반환
    @Transactional
    public Integer updateAddCartItem(CartRequestDto cartRequestDto, Long memberId) {
        Long itemId = cartRequestDto.getItemId();
        int itemCount = cartRequestDto.getCount();
        return  cartRepository.updateAddCartItem(itemCount,itemId, memberId);
    }

    // 장바구니 아이템 1개 찾는 로직 (회원아이디와 아이템 아이디)
    @Transactional
    public Cart findOneCartItem(Long memberId, Long itemId){
        Cart cart = cartRepository.findCartItem(itemId, memberId);
        return cart;
    }


}


