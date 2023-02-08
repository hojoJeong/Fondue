package com.ssafy.fundyou1.item.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.category.repository.CategoryRepository;
import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import com.ssafy.fundyou1.item.dto.*;
import com.ssafy.fundyou1.item.entity.Item;
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

    //희주 상품 데이터 추가

    @Transactional
    public Long saveItem(ItemSaveRequest request) {
        checkDuplicateItemTitle(request.getTitle(), request.getBrand());
        Category category = categoryRepository.findByCategoryName(request.getCategoryName());
        Item item = request.toItem(category);
        return itemRepository.save(item).getId();
    }


    // 상품 이름 브랜드 중복 검사

    public void checkDuplicateItemTitle(String title, String brand) {
        if (itemRepository.existsByTitleAndBrand(title, brand)) {
            throw new BusinessException(ErrorCode.ITEM_TITLE_BRAND_DUPLICATED);
        }
    }


    // 설명서 안 JSON 안의 json 리스트 객체 파싱 저장
    @Transactional
    public String saveDescriptionList(String title, List<DescriptionData> description) throws JsonProcessingException {
        Item item = itemRepository.findByTitle(title);

        ObjectMapper mapper = new ObjectMapper();
        item.setDescription(Collections.singletonList(mapper.writeValueAsString(description)));
        return item.getTitle();
    }


    @Transactional
    // 카테고리별 아이템 불러오기
    public List<ItemDto> getCategoryItemList(Long categoryId) {

        return itemRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(item -> {
                    item.getDescription();
                    return ItemDto.createItemDto(item);
                })
                .collect(Collectors.toList());
    }

    // 상품 디테일
    @Transactional
    public Item itemDetail(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    // 전체 상품 조회
    @Transactional
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    @Transactional
    public List<RandomItemResponse> getRandomItemList() {
        System.out.println("랜덤: " + itemRepository.findRandomItemById());
        List<RandomItemResponse> randomItemResponseList = new ArrayList<RandomItemResponse>();
        for (Item item : itemRepository.findRandomItemById()) {
            randomItemResponseList.add(new RandomItemResponse(item.getId(), item.getImage(), item.getIsAr(), item.getIsFavorite(), item.getPrice(), item.getTitle()));
        }
        return randomItemResponseList;
    }

    public List<Item> getTopItemList(Long categoryId, Long minPrice, @Param("maxPrice") Long maxPrice) {
        return itemRepository.findTopItem(categoryId, minPrice, maxPrice);
    }
}
