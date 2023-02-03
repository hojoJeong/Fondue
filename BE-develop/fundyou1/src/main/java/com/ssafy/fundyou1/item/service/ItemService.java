package com.ssafy.fundyou1.item.service;

import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.category.repository.CategoryRepository;
import com.ssafy.fundyou1.item.dto.ItemDto;
import com.ssafy.fundyou1.item.dto.ItemForm;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // 상품 데이터 추가
    @Transactional
    public ItemDto create(Long categoryId, ItemForm dto) {
        Category category = categoryRepository.findById(dto.getCategory().getId())
                .orElseThrow(() -> new IllegalArgumentException("상품 생성 실패! 대상 카테고리가 없습니다"));

        Item item = Item.createItem(dto, category);

        Item created = itemRepository.save(item);

        return ItemDto.createItemDto(created);

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
