package com.ssafy.fundyou1.fund.service;

import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.cart.entity.CartItem;
import com.ssafy.fundyou1.cart.repository.CartItemRepository;
import com.ssafy.fundyou1.cart.repository.CartRepository;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.repository.FundingItemRepository;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class FundingService {
    private final MemberRepository memberRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private FundingItemRepository fundingItemRepository;
    @Autowired
    private FundingRepository fundingRepository;
    @Autowired
    private MemberService memberService;


    public FundingService(FundingRepository fundingRepository,
                          FundingItemRepository fundingItemRepository,
                          CartItemRepository cartItemRepository,
                          MemberRepository memberRepository) {
        this.fundingRepository = fundingRepository;
        this.fundingItemRepository = fundingItemRepository;
        this.cartItemRepository = cartItemRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public List<FundingItem> createFunding() {
        // 사용자 정보
        MemberResponseDto meDto = memberService.getMyInfo();

        // Dto => Entity
        Member member = memberRepository.findByUsername(meDto.getUsername());

        // 펀딩 개설
        Funding created = Funding.createFunding(member);

        // 새펀딩 저장 및 아이디 값 리턴
        Funding createdFundingRepo = fundingRepository.save(created);

        //---------------------------------------------------------------------------
        // 장바구니 아이템 펀딩 아이템으로 변환

        // 장바구니 찾기
        Cart foundCart = cartRepository.findByMemberId(member.getId());

        // 장바구니 상품 가져오기
        List<CartItem> foundCartItemList = cartItemRepository.findAllByCartId(foundCart.getId());

        // 펀딩 상품 넣어야 할 펀딩 아이디
        Long funding_id = createdFundingRepo.getId();

        // funding_item으로 변경하여 저장
//        List<FundingItem> plusFundingItems = new ArrayList<>();
        for(CartItem cartItem : foundCartItemList){

            FundingItem.createFundingItem(createdFundingRepo, cartItem.getItem(), cartItem.getCount());

        }

        List<FundingItem> nowFundingItemList = fundingItemRepository.findByFundingId(funding_id);
        // 펀딩 상품 리스트 리턴

        return nowFundingItemList;
    }

    // 펀딩하는 상품 추가 메서드
    @Transactional
    public List<FundingItem> addFundingItems(Funding funding) {
        // 사용자 정보
        MemberResponseDto meDto = memberService.getMyInfo();

        // Dto => Entity
        Member member = memberRepository.findByUsername(meDto.getUsername());




    }

    // 내 펀딩 중 특정 펀팅 선택
    public Funding findMyFunding(Long fundingId){
        // 사용자 정보
        MemberResponseDto meDto = memberService.getMyInfo();

        // Dto => Entity
        Member member = memberRepository.findByUsername(meDto.getUsername());

        // 펀딩 찾기
        Funding funding = fundingRepository.findByIdAndMemberId(fundingId, member.getId());
    }

}
