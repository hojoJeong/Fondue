package com.ssafy.fundyou1.search.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.fundyou1.item.dto.ItemResponseDto;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.search.dto.SearchKeyWord;
import com.ssafy.fundyou1.search.entity.Search;
import com.ssafy.fundyou1.search.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RequestMapping("/search")
@Slf4j
@RestController
@Api(tags = {"검색"})
public class SearchController {

    @Autowired
    SearchService searchService;

    @PostMapping()
    @ApiOperation(value = "상품 검색", notes = "title: 상품명\nminPrice: 최소가격\nmaxPrice: 최대가격")
    public  ResponseEntity<List<ItemResponseDto>> getItemBySearch(@RequestBody SearchKeyWord searchKeyWord){
        return ResponseEntity.status(HttpStatus.OK).body(searchService.getItemBySearch(searchKeyWord));
    }

    @GetMapping("/keyword/rank")
    @ApiOperation(value = "인기 검색어", notes = "가장 많이 검색한 키워드 10개를 반환합니다.")
    public ResponseEntity<List<Search>> getSearchKeywordRank(){
        return ResponseEntity.status(HttpStatus.OK).body(searchService.getItemBySearchRank());
    }

}
