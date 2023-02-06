package com.ssafy.fundyou1.search.api;

import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.search.dto.SearchKeyWord;
import com.ssafy.fundyou1.search.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/search")
@Slf4j
@RestController
@Api(tags = {"검색"})
public class SearchController {

    @Autowired
    SearchService searchService;

    @PostMapping()
    @ApiOperation(value = "상품 검색", notes = "title: 상품명, minPrice: 최소가격, maxPrice: 최대가격\n" +
            "[category] 1: 2: 3: 4:")
    public ResponseEntity<List<Item>> getItemBySearch(@RequestBody SearchKeyWord searchKeyWord){
        return ResponseEntity.status(HttpStatus.OK).body(searchService.getItemBySearch(searchKeyWord));
    }
}
