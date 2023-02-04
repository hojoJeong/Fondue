package com.ssafy.fundyou1.item.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.fundyou1.category.service.CategoryService;
import com.ssafy.fundyou1.item.dto.DescriptionData;
import com.ssafy.fundyou1.item.dto.ItemDto;
import com.ssafy.fundyou1.item.dto.ItemSaveRequest;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.service.ItemService;
import com.ssafy.fundyou1.member.dto.request.MemberSaveRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
@Api(tags = {"아이템"})
public class ItemApiController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    // 상품 등록
//    @PostMapping("/items")
//    public ResponseEntity<Void> create(@RequestBody ItemForm dto) {
//        ItemDto created = itemService.create(dto.getCategory().getId(), dto);
//        return (created != null) ?
//                ResponseEntity.status(HttpStatus.OK).build():
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }

    // 희주 - 상품 등록

    @PostMapping("/item")
    @ApiOperation(value = "상품등록", notes = "상품등록")
    @ApiResponses({
            @ApiResponse(code = 409, message = "CONFLICT\n 상품 이름.브랜드 중복(I01)\n")
    })
    public ResponseEntity<Map<String,Object>> saveItem(@RequestBody @Valid ItemSaveRequest request) throws JsonProcessingException {
        itemService.saveItem(request);
        List<DescriptionData> descriptionData = request.getDescription();

        String title = itemService.saveDescriptionList(request.getTitle(), descriptionData);

        Map<String,Object> result = new HashMap<>();
        result.put("save",title );

        return ResponseEntity.ok().body(result);
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
    @GetMapping("/item/category/{categoryId}")
    public ResponseEntity<List<Item>> getCategoryItemList(@PathVariable Long categoryId) {
        List<Item> dtos = itemService.getCategoryItemList(categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 홈화면 조건에 맞는 Top5 아이템 조회
    @ApiOperation(value = "TEST", notes = "TEST API")
    @GetMapping("/items/category/{categoryId}/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Item>> getTopItemList(@RequestHeader("Authorization") String token, @PathVariable Long categoryId,@PathVariable Long minPrice,@PathVariable Long maxPrice) {
        List<Item> dtos = itemService.getTopItemList(categoryId, minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 램덤 5개 조회
    @RequestMapping(value = "/item/random")
    public ResponseEntity<List<Item>> getRandomItemList(Model model){
        List<Item> dtos = itemService.getRandomItemList();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


}