package com.ssafy.fundyou1.fund.service;

import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.cart.repository.CartRepository;
import com.ssafy.fundyou1.fund.dto.FundingDto;
import com.ssafy.fundyou1.fund.dto.FundingItemDto;
import com.ssafy.fundyou1.fund.dto.FundingResultMemberDto;
import com.ssafy.fundyou1.fund.dto.MyFundingDto;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import com.ssafy.fundyou1.fund.repository.FundingItemMemberRepository;
import com.ssafy.fundyou1.fund.repository.FundingItemRepository;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.item.dto.ItemDto;
import com.ssafy.fundyou1.item.entity.Item;
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
    public Long createFunding(String fundingName, Long endDate) {
        // 사용자 정보
        Optional<Member> member = memberRepository.findById(SecurityUtil.getCurrentMemberId()); // 현재 로그인한 회원 엔티티 조회

        // 펀딩 개설
        Long startDate = System.currentTimeMillis();

        Funding createdFunding = Funding.createFunding(fundingName, member.get(), startDate, endDate);

        // 새펀딩 저장 및 아이디 값 리턴
        Funding savedFunding = fundingRepository.save(createdFunding);

        //-----------------------------------------------------------------------//
        // 장바구니 아이템 펀딩 아이템으로 변환

        // 장바구니 상품 가져오기
        List<Cart> foundCartList = cartRepository.findAllByMember_Id(member.get().getId());

        // 펀딩 상품 넣어야 할 펀딩 아이디
        Long funding_id = savedFunding.getId();

        // funding_item으로 변경하여 저장
        for(Cart cart : foundCartList){

            FundingItem createdFundingItem = FundingItem.createFundingItem(savedFunding, cart.getItem(), cart.getCount());

            fundingItemRepository.save(createdFundingItem);

            // 장바구니에서 삭제
            cartRepository.delete(cart);

            // 해당 아이템 구매 횟수 값 + 1
            itemRepository.updateCountPlus(cart.getItem().getId());

        }

        return funding_id;


    }



    // 펀딩 통계 (참여 멤버)
    @Transactional
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
    @Transactional
    public List<MyFundingDto> getMyOngoingFundingList() {
        Optional<Member> member = memberRepository.findById(SecurityUtil.getCurrentMemberId()); // 현재 로그인한 회원 엔티티 조회

        // "내" & "진행중" 펀딩
        List<Funding> myOngoingFundingList = fundingRepository.findAllByMemberIdAndByFundingStatus(member.get().getId(), true);

        List<MyFundingDto> myOngoingFundingListDto  = new ArrayList<>();

        for(Funding myOngoingFunding : myOngoingFundingList ){
            int totalPrice = fundingItemRepository.sumTotalPriceByFundingId(myOngoingFunding.getId());

            int currentFundingPrice = fundingItemRepository.sumCurrentFundingPriceByFundingId(myOngoingFunding.getId());

            List<FundingItem> fundingItemList = fundingItemRepository.findByFundingId(myOngoingFunding.getId());

            List<FundingItemDto> fundingItemDtoList = new ArrayList<>();

            for(FundingItem fundingItem : fundingItemList){

                FundingItemDto fundingItemDto = FundingItemDto.createFundingItemDto(fundingItem);

                fundingItemDtoList.add(fundingItemDto);
            }

            MyFundingDto myFundingDto = new MyFundingDto(myOngoingFunding, totalPrice, currentFundingPrice, (currentFundingPrice / totalPrice) * 100, fundingItemDtoList);

            myOngoingFundingListDto.add(myFundingDto);
        }

        return myOngoingFundingListDto;

    }


    public List<MyFundingDto> getMyClosedFundingList() {

        Optional<Member> member = memberRepository.findById(SecurityUtil.getCurrentMemberId()); // 현재 로그인한 회원 엔티티 조회

        // "내" & "종료된" 펀딩
        List<Funding> myClosedFundingList = fundingRepository.findAllByMemberIdAndByFundingStatus(member.get().getId(), false);

        List<MyFundingDto> myClosedFundingListDto  = new ArrayList<>();

        for(Funding myClosedFunding : myClosedFundingList ){
            int totalPrice = fundingItemRepository.sumTotalPriceByFundingId(myClosedFunding.getId());

            int currentFundingPrice = fundingItemRepository.sumCurrentFundingPriceByFundingId(myClosedFunding.getId());

            List<FundingItem> fundingItemList = fundingItemRepository.findByFundingId(myClosedFunding.getId());

            List<FundingItemDto> fundingItemDtoList = new ArrayList<>();

            for(FundingItem fundingItem : fundingItemList){
                FundingItemDto fundingItemDto = FundingItemDto.createFundingItemDto(fundingItem);

                fundingItemDtoList.add(fundingItemDto);
            }

            MyFundingDto myFundingDto = new MyFundingDto(myClosedFunding, totalPrice, currentFundingPrice, (currentFundingPrice / totalPrice) * 100, fundingItemDtoList);

            myClosedFundingListDto.add(myFundingDto);
        }

        return myClosedFundingListDto;
    }


    // 내 펀딩 중 특정 펀팅 선택
    @Transactional
    public FundingDto getFundingInfo(Long fundingId) {

        Funding funding = fundingRepository.getReferenceById(fundingId);

        int totalPrice = fundingItemRepository.sumTotalPriceByFundingId(funding.getId());

        int currentFundingPrice = fundingItemRepository.sumCurrentFundingPriceByFundingId(funding.getId());

        FundingDto fundingDto = new FundingDto(funding, totalPrice, currentFundingPrice, (currentFundingPrice / totalPrice) * 100);

        return fundingDto;
    }



    // 펀딩 종료 버튼 클릭
    @Transactional
    public String terminateFunding(Long fundingId) {

        // 펀딩 종료
        fundingRepository.updateStatus(fundingId, false);

        // 펀딩 상품 종료
        fundingItemRepository.updateFundingItemStatusByFundingId(fundingId, false);

        return "펀딩이 종료 되었습니다.";

    }
}
