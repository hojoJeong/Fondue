package com.ssafy.fundyou1.item.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.fundyou1.category.service.CategoryService;
import com.ssafy.fundyou1.global.dto.BaseResponseBody;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.item.dto.*;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.service.ItemService;
import com.ssafy.fundyou1.like.dto.LikeItemResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/item")
@Api(tags = {"아이템"})
public class ItemApiController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    // 상품 등록

    @PostMapping("/save")
    @ApiOperation(value = "상품등록", notes = "상품등록")
    @ApiResponses({
            @ApiResponse(code = 409, message = "CONFLICT\n 상품 이름.브랜드 중복(I01)\n")
    })
    public ResponseEntity saveItem(@RequestBody @Valid ItemSaveRequest request) throws JsonProcessingException {
        Long itemId = itemService.saveItem(request);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success",itemId  ));

    }


    // 전체 상품 목록 조회
    @GetMapping("/list")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // 상품 디테일
    @GetMapping("/{id}")
    public Item itemDetail(@PathVariable Long id){
        return itemService.itemDetail(id);
    }


    // 카테고리별 상품 목록 조회
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ItemDto>> getCategoryItemList(@PathVariable Long categoryId) {
        List<ItemDto> dtos = itemService.getCategoryItemList(categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 홈화면 조건에 맞는 Top5 아이템 조회
    @ApiOperation(value = "홈화면 Top5", notes = "카테고리, 가격범위에 맞는 아이템 5개 반환")
    @GetMapping("/category/{categoryId}/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Item>> getTopItemList(@PathVariable Long categoryId,@PathVariable Long minPrice,@PathVariable Long maxPrice) {
        List<Item> dtos = itemService.getTopItemList(categoryId, minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 램덤 5개 조회
    @GetMapping(value = "/random")
    public ResponseEntity<List<RandomItemResponse>> getRandomItemList(){
        List<RandomItemResponse> randomItemResponseList = itemService.getRandomItemList();

        return ResponseEntity.status(HttpStatus.OK).body(randomItemResponseList);
    }


    // 아이템 전체 조회

    @GetMapping(value = "/list/member")
    public ResponseEntity<List<ItemResponseDto>> getItemAll() {
        List<ItemResponseDto> ItemList = itemService.findAllItem(SecurityUtil.getCurrentMemberId());

        return ResponseEntity.status(HttpStatus.OK).body(ItemList);
    }


}