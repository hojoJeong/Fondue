package com.ssafy.fundyou1.like.service;

import com.ssafy.fundyou1.cart.dto.CartItemResponseDto;
import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.item.dto.ItemDto;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.item.service.ItemService;
import com.ssafy.fundyou1.like.dto.LikeItemResponseDto;
import com.ssafy.fundyou1.like.dto.LikeRequestDto;
import com.ssafy.fundyou1.like.entity.Like;
import com.ssafy.fundyou1.like.repository.LikeRepository;
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
public class LikeService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

    @Autowired
    LikeRepository likeRepository;

    // 찜목록에 상품을 담는 로직
    public Long addLike(LikeRequestDto likeRequestDto, Long memberId) {

//        Optional<Item> item = itemRepository.findById(likeRequestDto.getItemId()); // 찜 목록에 담을 상품 엔티티 조회

        Long itemId = likeRequestDto.getItemId();
        Optional<Member> member = memberRepository.findById(memberId); // 찜 목록에 담을 회원 엔티티 조회

        Like createLike = Like.createLike(member.get(), itemId);

        likeRepository.save(createLike);

        return createLike.getId();

    }

    @Transactional
    public Like findOneLikeItem( Long itemId, Long memberId) {
        Like like = likeRepository.findLikeItem(itemId, memberId);
        return like;
    }

    //사용자 찜 목록 리스트

    @Transactional(readOnly = true)
    public List<LikeItemResponseDto> findLikeByMemberId(Long memberId) {
        List<Like> findLikeItems = likeRepository.findAllByMember_Id(memberId);

        List<Item> findAllItems = itemRepository.findAll();

        if ( findLikeItems.size() != 0) {
            List<LikeItemResponseDto> likeItemResponse = new ArrayList<>();
            for (Like like : findLikeItems) {
                Long likeItemId  = like.getItem_id();
                Optional<Item> likeItem = itemRepository.findById(likeItemId);
                Item likeItemOne = likeItem.get();
                likeItemResponse.add(new LikeItemResponseDto( like.getMember(), true, likeItemOne));
            }
            return likeItemResponse;
        }
        return null;
    }

    // 사용자 찜 목록 리스트 - 조인 컬럼 해제

    public List<Item> findAllItemLike(boolean b) {
        List<Item> likeList = itemRepository.findAllByIsFavorite(true);
        return likeList;
    }


    // 사용자의 찜목록 아이템 삭제
    @Transactional
    public List<LikeItemResponseDto> deleteByLikeItemId(Long id) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        likeRepository.deleteLikeItem(memberId, id);
        List<LikeItemResponseDto> likeItemResponse= findLikeByMemberId(memberId);
        return likeItemResponse;
    }

//    @Transactional
//    public List<LikeItemResponseDto> updateIsFavorited(Long itemId, boolean b, Long memberId) {
//        if (b) {
//            likeRepository.updateItemIsFavorite(itemId, b);
//        } else {
//            likeRepository.updateItemIsFavorite(itemId, b);
//        }
//        List<LikeItemResponseDto> likeItemResponse= findLikeByMemberId(memberId);
//        return likeItemResponse;
//    }

}
