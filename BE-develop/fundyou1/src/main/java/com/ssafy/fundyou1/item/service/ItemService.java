package com.ssafy.fundyou1.item.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.category.repository.CategoryRepository;
import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.item.dto.*;
import com.ssafy.fundyou1.item.entity.Description;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.DescriptionRepository;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.like.dto.LikeItemResponseDto;
import com.ssafy.fundyou1.like.entity.Like;
import com.ssafy.fundyou1.like.repository.LikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.metadata.ItemMetadata;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    //상품 데이터 추가
    @Transactional
    public Long saveItem(ItemSaveRequest request) {
        // 브랜드 이름, 중복검사
        checkDuplicateItemTitle(request.getTitle(), request.getBrand());
        // 카테고리찾기
        Category category = categoryRepository.findByCategoryName(request.getCategoryName());
        // 아이템 먼저 저장
        Item item = request.toItem(category);
        // description 여러개 리스트
        for(Description description : request.getDescription()) {
            Description newDescription  = Description.builder()
                    .item(item) // 위에서 등록한 itemEntity
                    .type(description .getType())  // 필드 1
                    .value(description .getValue())  // 필드 2
                    .build();
            descriptionRepository.save(newDescription);

        }
        return itemRepository.save(item).getId();
    }


    // 상품 이름 브랜드 중복 검사

    public void checkDuplicateItemTitle(String title, String brand) {
        if (itemRepository.existsByTitleAndBrand(title, brand)) {
            throw new BusinessException(ErrorCode.ITEM_TITLE_BRAND_DUPLICATED);
        }
    }




    // 카테고리별 아이템 불러오기
    public List<ItemResponseDto> getCategoryItemList(Long categoryId) {
        // 카테고리 아이템
        List<Item> categoryItemList = itemRepository.findAllByCategoryId(categoryId);
        // 좋아요한 아이템
        List<Like> findLikeItems = likeRepository.findAllByMember_Id(SecurityUtil.getCurrentMemberId());
        // 좋아요 구분된 카테고리 리스트
        List<ItemResponseDto> itemList = new ArrayList<>();
        // 회원이 좋아요한 아이템이랑 아닌 아이템이랑 is_favorite로 구분해주기
        for (Item item : categoryItemList) {
            Long ItemId  = item.getId();
            for (Like like : findLikeItems) {
                if (like.getItem_id() == ItemId) {
                    itemList.add(new ItemResponseDto(item, true));
                    break;
                } else {
                    itemList.add(new ItemResponseDto(item, false));
                }
            }
        }
        return itemList;
    }

    // 상품 디테일
    public List<ItemResponseDto> itemDetail(Long id) {

        Optional<Item> findItem = itemRepository.findById(id);
        List<ItemResponseDto> findItemOne = new ArrayList<>();
        if (findItem.isPresent()) {
            Item item = findItem.get();
            if(likeRepository.exists(item.getId(), SecurityUtil.getCurrentMemberId()) != null) {
                findItemOne.add(new ItemResponseDto(item, true));
            } else {
                findItemOne.add(new ItemResponseDto(item, false));
            }
        }
        return findItemOne;
    }


    // 랜덤 5개 상품 추출
//    "count": "1,2,3",
//            "image": "ssafy/img/thumbnail.jpg",
//            "isAr": false,
//            "isFavorite": false,
//            "itemId": "1,2",
//            "memberId": "1,2",
//            "price": 10000,
//            "title": "쇼파"
    public List<RandomItemResponse> getRandomItemList() {
        System.out.println("랜덤: " + itemRepository.findRandomItemById());
        // 멤버가 좋아하는 아이템
        List<Like> findLikeItems = likeRepository.findAllByMember_Id(SecurityUtil.getCurrentMemberId());
        List<RandomItemResponse> randomItemResponseList = new ArrayList<>();
        for (Item item : itemRepository.findRandomItemById()) {
            Long ItemId  = item.getId();
            for (Like like : findLikeItems) {
                if (like.getItem_id() == ItemId) {
                    randomItemResponseList.add(new RandomItemResponse(item, true));
                    break;
                } else {
                    randomItemResponseList.add(new RandomItemResponse(item, false));
                }
            }
        }
        System.out.println("랜덤: " + randomItemResponseList);
        return randomItemResponseList;
    }



    public List<RandomItemResponse> getTopItemList(Long categoryId, Long minPrice, @Param("maxPrice") Long maxPrice) {
        List<Item> toplist = itemRepository.findTopItem(categoryId, minPrice, maxPrice);
        System.out.println("탑 아이템 : " + toplist);
        // 멤버가 좋아하는 아이템
        List<Like> findLikeItems = likeRepository.findAllByMember_Id(SecurityUtil.getCurrentMemberId());
        List<RandomItemResponse> topResponseList = new ArrayList<>();
        for (Item item : itemRepository.findRandomItemById()) {
            Long ItemId  = item.getId();
            for (Like like : findLikeItems) {
                if (like.getItem_id() == ItemId) {
                    topResponseList.add(new RandomItemResponse(item, true));
                    break;
                } else {
                    topResponseList.add(new RandomItemResponse(item, false));
                }
            }
        }
        System.out.println("탑 아이템: " + topResponseList);
        return topResponseList;
    }


    // 멤버별별 구분 아이템 전체 리스트
    public List<ItemResponseDto> findAllItem(Long memberId) {
        // 멤버가 좋아하는 아이템
        List<Like> findLikeItems = likeRepository.findAllByMember_Id(memberId);
        // 전체 아이템 조회
        List<Item> findAllItems = itemRepository.findAll();
        // 아이템 리스트 만들기
        List<ItemResponseDto> itemList = new ArrayList<>();
        // 멤버가 좋아하는 아이템이랑 id값이 같으면 is_favorite ture/ 아니면 false
        for (Item item : findAllItems) {
            Long ItemId  = item.getId();
            for (Like like : findLikeItems) {
                if (like.getItem_id() == ItemId) {
                    itemList.add(new ItemResponseDto(item, true));
                    break;
                } else {
                    itemList.add(new ItemResponseDto(item, false));
                }
            }
        }
        return itemList;
    }

}
