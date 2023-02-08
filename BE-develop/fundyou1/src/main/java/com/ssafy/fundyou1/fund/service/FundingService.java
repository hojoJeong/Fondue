package com.ssafy.fundyou1.fund.service;

import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.cart.repository.CartRepository;
import com.ssafy.fundyou1.fund.dto.FundingResultMemberDto;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import com.ssafy.fundyou1.fund.repository.FundingItemMemberRepository;
import com.ssafy.fundyou1.fund.repository.FundingItemRepository;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class FundingService {
    private final FundingItemMemberRepository fundingItemMemberRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private FundingItemRepository fundingItemRepository;
    @Autowired
    private FundingRepository fundingRepository;
    @Autowired
    private MemberService memberService;

    // 펀딩 개설
    @Transactional
    public Long createFunding(Long endDate) {
        // 사용자 정보
        MemberResponseDto meDto = memberService.getMyInfo();
        Member member = memberRepository.findByUsername(meDto.getUsername());

        // 펀딩 개설
        Calendar cal = Calendar.getInstance();
        Long nowDate = cal.getTimeInMillis();   // Date(long date)
//        Long nowDate = LocalDateTime.now().getTimeInMills();
//        Long nowDate = System.currentTimeMillis();
        Funding createdFunding = Funding.createFunding(member, endDate);

        // 새펀딩 저장 및 아이디 값 리턴
        Funding savedFunding = fundingRepository.save(createdFunding);

        //---------------------------------------------------------------------------
        // 장바구니 아이템 펀딩 아이템으로 변환


        // 장바구니 상품 가져오기
        List<Cart> foundCartList = cartRepository.findAllByMember_Id(member.getId());

        // 펀딩 상품 넣어야 할 펀딩 아이디
        Long funding_id = savedFunding.getId();

        // funding_item으로 변경하여 저장
        for(Cart cart : foundCartList){
            FundingItem createdFundingItem = FundingItem.createFundingItem(savedFunding, cart.getItem(), cart.getCount());
            fundingItemRepository.save(createdFundingItem);
            // 장바구니에서 삭제
            cartRepository.delete(cart);

        }

        return funding_id;


    }


    // 내 펀딩 중 특정 펀팅 선택
    public Funding findMyFunding(Long fundingId){
        // 사용자 정보
        MemberResponseDto meDto = memberService.getMyInfo();
        Member member = memberRepository.findByUsername(meDto.getUsername());

        // 펀딩 찾기
        Funding funding = fundingRepository.findByIdAndMemberId(fundingId, member.getId());

        return funding;
    }


    // 펀딩 통계 (참여 멤버)
    public List<FundingResultMemberDto> fundingResultMemberDtoList(Long fundingId) {

        // 해당 펀딩(fundingId)의 펀딩 아이템 리스트 찾고
        List<FundingItem> fundingItemList = fundingItemRepository.findByFundingId(fundingId);

        List<FundingResultMemberDto> createdFundingResultMemberDtoList = new ArrayList<>();

        // 펀딩 아이템 리스트에 funding_item_member 찾기
        for(FundingItem fundingItem : fundingItemList){
            // 해당 펀딩 아이템에 참여한 사람
            List<FundingItemMember> fundingItemMemberList = fundingItemMemberRepository.findAllByFundingItemId(fundingItem.getId());

            for(FundingItemMember fundingItemMember : fundingItemMemberList){
                // 펀딩 참여자
                Member member = fundingItemMember.getMember();

                if(!createdFundingResultMemberDtoList.contains(member)){
                    int totalPrice = fundingItemMemberRepository.findSumByMemberIdAndFundingItemId(member.getId(), fundingItem.getId());
                    FundingResultMemberDto fundingResultMemberDto = new FundingResultMemberDto(member, totalPrice);
                    createdFundingResultMemberDtoList.add(fundingResultMemberDto);
                }
            }

        }

        Collections.sort(createdFundingResultMemberDtoList, new Comparator<FundingResultMemberDto>() {
            @Override
            public int compare(FundingResultMemberDto o1, FundingResultMemberDto o2) {
                return o2.getAttendedPrice() - o1.getAttendedPrice();
            }
        });

        return createdFundingResultMemberDtoList;
    }


    // 내 펀딩 리스트


}
