package com.ssafy.fundyou1.cart.service;

import com.fasterxml.classmate.util.ClassStack;
import com.ssafy.fundyou1.cart.dto.*;
import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.cart.entity.CartItem;
import com.ssafy.fundyou1.cart.repository.CartItemRepository;
import com.ssafy.fundyou1.cart.repository.CartRepository;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
    @Autowired
    CartItemRepository cartItemRepository;


    // 장바구니에 상품을 담는 로직
    public Long addCart(CartRequestDto cartRequestDto, String username) {

        Item item = itemRepository.findById(cartRequestDto.getItemId()) //장바구니에 담을 상품 엔티티 조회
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByUsername(username); // 현재 로그인한 회원 엔티티 조회

        Cart cart = cartRepository.findByMemberId(member.getId()); // 현재 로그인한 회원의 장바구니 엔티티 조회

        if (cart == null) { // 회원에게 장바구니가 없으면, 만들어줌
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        // 상품이 장바구니에 들어가있는지 아닌지 조회
        CartItem savedCartItem = cartItemRepository.findByCartIdAndItem_ItemId(cart.getId(), item.getItemId());

        // 만약 상품이 이미 있으면은 개수를 +
        if (savedCartItem != null) {
            savedCartItem.addCount(cartRequestDto.getCount());
            return savedCartItem.getCartItemId();
        } else { // 아니면은 CartItem 에 상품 저장
            CartItem cartItem = CartItem.createCartItem(cart, item, cartRequestDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getCartItemId();
        }
    }


    //멤버 id를 이용하여 카트 리스트를 조회합니다.
    @Transactional(readOnly = true)
    public List<CartItemResponseDto> findCartItemsByCartId(Long memberId) {
        Optional<Cart> findCart = cartRepository.findByMember_Id(memberId);

        if(findCart.isPresent()){
            Cart cart = findCart.get();
            List<CartItem> cartItems = cartItemRepository.findAllByCart_Id(cart.getId());
            List<CartItemResponseDto> cartItemResponseDtos = new ArrayList<>();
            for(CartItem item : cartItems){
                cartItemResponseDtos.add(new CartItemResponseDto(item.getItem(), item.getCartItemId(), item.getCount()));
            }
            return cartItemResponseDtos;

        }
        return null;
    }

    // 사용자의 장바구니 정보 반환

    public Cart findCartByMember(Long memberId) {
        Optional<Cart> cart = cartRepository.findByMember_Id(memberId);
        if(cart.isPresent()) {
            return cart.get();
        }
        return null;
    }

    // 사용자의 장바구니 아이템 삭제
    public String deleteByCartItemId(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);

        return "sucess";
    }



};


