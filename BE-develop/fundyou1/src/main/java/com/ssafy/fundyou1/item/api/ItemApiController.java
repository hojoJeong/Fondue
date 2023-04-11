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
    public ResponseEntity saveItem(@RequestBody ItemSaveRequest request){
        Long itemId = itemService.saveItem(request);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success",itemId ));

    }


    // 멤버별 좋아요 구분해서 아이템 전체 조회
    @GetMapping(value = "/list")
    @ApiOperation(value = "전체 아이템 조회", notes = "회원 별로 is_favorite로 아이템 좋아요 구분 할 수 있음")
    public ResponseEntity<List<ItemResponseDto>> getItemAll() {
        // 현재 멤버 id를 넘겨주고 전체 아이템 찾기
        List<ItemResponseDto> ItemList = itemService.findAllItem(SecurityUtil.getCurrentMemberId());

        return ResponseEntity.status(HttpStatus.OK).body(ItemList);
    }



    // 아이템 1개 디테일
    @GetMapping("/{itemId}")
    @ApiOperation(value = "id 값으로 아이템 1개 찾기", notes = "id 값으로 아이템 1개 찾습니다.")
    public ItemResponseDto getItemDetail(@PathVariable Long itemId){
        return itemService.getItemDetail(itemId);
    }


    // 카테고리별 아이템 목록 조회
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ItemResponseDto>> getCategoryItemList(@PathVariable Long categoryId) {
        List<ItemResponseDto> categoryItemList = itemService.getCategoryItemList(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryItemList);
    }


    // 홈화면 조건에 맞는 Top5 아이템 조회
    @ApiOperation(value = "홈화면 Top5", notes = "카테고리, 가격범위에 맞는 아이템 5개 반환")
    @GetMapping("/ranking/{categoryId}/{minPrice}/{maxPrice}")
    public ResponseEntity<List<ItemResponseDto>> getRankItemList(@PathVariable Long categoryId,@PathVariable Long minPrice,@PathVariable Long maxPrice) {
        List<ItemResponseDto> rankItemList = itemService.getRankItemListWithFilter(categoryId, minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK).body(rankItemList);
    }

    @ApiOperation(value = "카테고리, 가격범위로 아이템 조회", notes = "카테고리, 가격범위에 맞는 아이템 반환")
    @GetMapping("/category/{categoryId}/{minPrice}/{maxPrice}")
    public ResponseEntity<List<ItemResponseDto>> getItemListWithFilter(@PathVariable Long categoryId,@PathVariable Long minPrice,@PathVariable Long maxPrice) {
        List<ItemResponseDto> itemResponseDtoList = itemService.getItemListWithFilter(categoryId, minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK).body(itemResponseDtoList);
    }

    // 램덤 6개 조회
    @GetMapping(value = "/random")
    @ApiOperation(value = "랜덤 6개 조회", notes = "아이템 중에 랜덤 6개 조회 반환")
    public ResponseEntity<List<ItemResponseDto>> getRandomItemList(){
        List<ItemResponseDto> randomItemResponseList = itemService.getRandomItemList();
        return ResponseEntity.status(HttpStatus.OK).body(randomItemResponseList);
    }
}