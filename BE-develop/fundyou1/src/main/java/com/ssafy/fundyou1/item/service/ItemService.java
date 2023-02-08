package com.ssafy.fundyou1.item.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.category.repository.CategoryRepository;
import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
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
    public List<ItemDto> getCategoryItemList(Long categoryId) {

        return itemRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(item -> ItemDto.createItemDto(item))
                .collect(Collectors.toList());
    }

    // 상품 디테일
    public Item itemDetail(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    // 전체 상품 조회
    public List<Item> getAllItems() {
        return itemRepository.findAll();
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
        List<RandomItemResponse> randomItemResponseList = new ArrayList<RandomItemResponse>();
        for (Item item : itemRepository.findRandomItemById()) {
            randomItemResponseList.add(new RandomItemResponse(item.getId(), item.getImage(), item.getIsAr(), item.getIsFavorite(), item.getPrice(), item.getTitle()));
        }
//        return itemRepository.findRandomItemById()
//                .stream()
//                .map(item -> ItemDto.createItemDto(item))
//                .collect(Collectors.toList());
        System.out.println("랜덤: " + randomItemResponseList);
        return randomItemResponseList;
    }

    public List<Item> getTopItemList(Long categoryId, Long minPrice, @Param("maxPrice") Long maxPrice) {
        List<Item> list = itemRepository.findTopItem(categoryId, minPrice, maxPrice);
        System.out.println("testinfo : " + list);

        return itemRepository.findTopItem(categoryId, minPrice, maxPrice);
    }


    // 회원별 구분 아이템 전체 리스트
    public List<ItemResponseDto> findAllItem(Long memberId) {
        List<Like> findLikeItems = likeRepository.findAllByMember_Id(memberId);

        List<Item> findAllItems = itemRepository.findAll();

        List<ItemResponseDto> itemList = new ArrayList<>();

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
