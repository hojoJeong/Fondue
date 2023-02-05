package com.ssafy.fundyou1.fund.service;

import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.cart.entity.CartItem;
import com.ssafy.fundyou1.cart.entity.CartItemRepository;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.repository.FundingItemRepository;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FundingService {
    private final CartItemRepository cartItemRepository;
    private final FundingItemRepository fundingItemRepository;

    private FundingRepository fundingRepository;

    public FundingService(FundingRepository fundingRepository,
                          FundingItemRepository fundingItemRepository,
                          CartItemRepository cartItemRepository) {
        this.fundingRepository = fundingRepository;
        this.fundingItemRepository = fundingItemRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional
    public Long createFunding(Member member) {
        // 펀딩 개설
        Funding created = Funding.createFunding(member);
        Funding createdRepo = fundingRepository.save(created);

        // 펀딩하는 상품 추가

        // 새펀딩 id 리턴
        return createdRepo.getId();
    }

    @Transactional
    public List<FundingItem> addFundingItems(Member member, Long fundingId) {

        // 장바구니 찾기
//        Cart foundCartId = cartRepository.findByUserId(member.getId());
        // 지금 cart repository 없어서 언니랑 합치고 진행해야함

        // 장바구니 상품 가져오기
        // 이것도 cartitemrepository 없어서 언니랑 합치고 진행해야함
//        List<CartItem> foundCartItemList = cartItemRepository.findByCartId(foundCartId);

        // 이부분 fundingId를
        // 펀딩 상품 넣어야 할 펀딩 아이디
//        Long funding_id = funding.getId();

        // funding_item으로 변경하여 저장
        List<FundingItem> plusFundingItems = new ArrayList<>();
        for(CartItem cartItem : foundCartItemList){

            FundingItem.createFundingItem(funding, cartItem.getItem(), cartItem.getCartItemCount());

        }

        List<FundingItem> nowFundingItemList = fundingItemRepository.findByFundingId(funding_id);
        // 펀딩 상품 리스트 리턴
        return nowFundingItemList;

    }

}
