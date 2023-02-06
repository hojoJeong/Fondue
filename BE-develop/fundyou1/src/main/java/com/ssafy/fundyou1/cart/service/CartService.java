package com.ssafy.fundyou1.cart.service;

import com.fasterxml.classmate.util.ClassStack;
import com.ssafy.fundyou1.cart.dto.CartDetailDto;
import com.ssafy.fundyou1.cart.dto.CartItemAddRequestDto;
import com.ssafy.fundyou1.cart.dto.CartItemAddResponseDto;
import com.ssafy.fundyou1.cart.dto.CartItemDto;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;

    private final MemberService memberService;
    //장바구니 생성


    // 장바구니 아이템 추가

    // 카트에 물품 추가
    // 장바구니에 상품을 담는 로직
    public Long addCart(CartItemDto cartItemDto, String username){

        Item item = itemRepository.findById(cartItemDto.getItemId()) //장바구니에 담을 상품 엔티티 조회
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByUsername(username); // 현재 로그인한 회원 엔티티 조회

        Cart cart = cartRepository.findByMemberId(member.getId()); // 현재 로그인한 회원의 장바구니 엔티티 조회

        if(cart == null){ // 회원에게 장바구니가 없으면, 만들어줌
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        // 상품이 장바구니에 들어가있는지 아닌지 조회
        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        // 만약 상품이 이미 있으면은 개수를 +
        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else { // 아니면은 CartItem 에 상품 저장
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }


    // username을 이용하여 카트 리스트를 조회합니다.
//    @Transactional(readOnly = true)
//    public List<Item> getCartList(Long cartId){
//
//        List<Item> emptyCartDetailDtoList = new ArrayList<>();
//
////        CartItem cartItem = cartRepository.findByCartId(cartId);
//
//
////        if(cart == null){ // 위에서 유저 카트 조회해서, 없으면은 그냥 반환하고
////            return emptyCartDetailDtoList;
////        }
//
////        List<Item> cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
//        return cartDetailDtoList; // 카트 있으면은 cartItemRepository 의 JPQL 쿼리로 걸러진 아이템들을 담영서 반환
//    }



}
