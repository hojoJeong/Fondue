package com.ssafy.fundyou1.item.api;

import com.ssafy.fundyou1.category.service.CategoryService;
import com.ssafy.fundyou1.item.dto.ItemDto;
import com.ssafy.fundyou1.item.dto.ItemForm;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.service.ItemService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ItemApiController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    // 상품 등록
    @PostMapping("/items")
    public ResponseEntity<Void> create(@RequestBody ItemForm dto) {
        ItemDto created = itemService.create(dto.getCategory().getId(), dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 전체 상품 목록 조회
    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // 상품 디테일
    @GetMapping("/item/{id}")
    public Item itemDetail(@PathVariable Long id){
        return itemService.itemDetail(id);
    }


    // 카테고리별 상품 목록 조회
    @GetMapping("/item/category/{id}")
    public ResponseEntity<List<ItemDto>> getCategoryItemList(@PathVariable Long categoryId) {
        List<ItemDto> dtos = itemService.getCategoryItemList(categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 홈화면 조건에 맞는 Top5 아이템 조회
    @GetMapping("/items/category/{id}/{min_price}/{max_price}")
    public ResponseEntity<List<ItemDto>> getTopItemList(@PathVariable Long categoryId, Long minPrice, Long maxPrice) {
        List<ItemDto> dtos = itemService.getTopItemList(categoryId, minPrice, maxPrice);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 램덤 5개 조회
    @RequestMapping(value = "/item/random")
    public ResponseEntity<List<ItemDto>> getRandomItemList(Model model){
        List<ItemDto> dtos = itemService.getRandomItemList();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


}