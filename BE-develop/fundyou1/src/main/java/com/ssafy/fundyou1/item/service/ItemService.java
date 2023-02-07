package com.ssafy.fundyou1.item.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.category.repository.CategoryRepository;
import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import com.ssafy.fundyou1.item.dto.DescriptionData;
import com.ssafy.fundyou1.item.dto.ItemDto;
import com.ssafy.fundyou1.item.dto.ItemForm;
import com.ssafy.fundyou1.item.dto.ItemSaveRequest;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    //희주 상품 데이터 추가

    @Transactional
    public Long saveItem(ItemSaveRequest request) {
        checkDuplicateItemTitle(request.getTitle(), request.getBrand());

        Category category = categoryRepository.findByCategoryName(request.getCategoryName());

        Item item = request.toItem(category);
        return itemRepository.save(item).getId();
    }


    // 희주 상품 이름 브랜드 중복 검사

    public void checkDuplicateItemTitle(String title, String brand) {
        if(itemRepository.existsByTitleAndBrand(title, brand)) {
            throw new BusinessException(ErrorCode.ITEM_TITLE_BRAND_DUPLICATED);
        }
    }


    // 설명서 안 JSON 안의 json 리스트 객체 파싱 저장
    @Transactional
    public String saveDescriptionList(String title, List<DescriptionData> description ) throws JsonProcessingException {
        Item item = itemRepository.findByTitle(title);

        ObjectMapper mapper = new ObjectMapper();
        item.setDescription(Collections.singletonList(mapper.writeValueAsString(description)));
        return item.getTitle();
    }



    // 카테고리별 아이템 불러오기
    public List<ItemDto> getCategoryItemList(Long categoryId){

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
    public List<ItemDto> getRandomItemList(){

        return itemRepository.findRandomItemById()
                .stream()
                .map(item -> ItemDto.createItemDto(item))
                .collect(Collectors.toList());
    }

    public List<Item> getTopItemList(Long categoryId, Long minPrice, @Param("maxPrice") Long maxPrice) {
        List<Item> list = itemRepository.findTopItem(categoryId, minPrice, maxPrice);
        System.out.println("testinfo : " + list);

        return itemRepository.findTopItem(categoryId, minPrice, maxPrice);
    }
}
