package com.ssafy.fundyou1.like.service;


import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.item.dto.ItemResponseDto;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.item.service.ItemService;
import com.ssafy.fundyou1.like.entity.Like;
import com.ssafy.fundyou1.like.repository.LikeRepository;
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

    // 찜목록에 아이템을 추가하는 로직
    @Transactional
    public Long addLike(Long memberId, Long itemId) {

        Optional<Member> member = memberRepository.findById(memberId); // 찜 목록에 담을 회원 엔티티 조회

        Like createLike = Like.createLike(member.get(), itemId);

        likeRepository.save(createLike);

        return createLike.getItem_id();

    }

    // 찜 아이템 1개 찾기 (아이템 아이디, 회원 아이디)
    @Transactional
    public Like findOneLikeItem( Long itemId, Long memberId) {
        Like likeItem = likeRepository.findLikeItem(itemId, memberId);
        return likeItem;
    }

    //회원 찜 목록 리스트
    @Transactional(readOnly = true)
    public List<ItemResponseDto> findLikeByMemberId(Long memberId) {
        List<Like> findLikeItems = likeRepository.findAllByMember_Id(memberId);
        // 찜목록에 아이템이 있으면
        if ( findLikeItems.size() != 0) {
            List<ItemResponseDto> likeItemResponse = new ArrayList<>();
            for (Like like : findLikeItems) {
                // 아이템 아이디
                Long likeItemId  = like.getItem_id();
                // 아이템 찾기
                Optional<Item> likeItem = itemRepository.findById(likeItemId);
                // 찜 여부 is_favorite -> true
                likeItemResponse.add(new ItemResponseDto( likeItem.get(), true));
            }
            return likeItemResponse;
        }
        return new ArrayList();
    }


    // 사용자의 찜목록 아이템 삭제 (현재 회원 아이디, 아이템 아이디)
    @Transactional
    public Integer deleteByLikeItemId(Long itemId) {
        return likeRepository.deleteLikeItem(SecurityUtil.getCurrentMemberId(), itemId);
    }

}
