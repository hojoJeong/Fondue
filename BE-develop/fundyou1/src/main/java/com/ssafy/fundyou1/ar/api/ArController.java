package com.ssafy.fundyou1.ar.api;

import com.ssafy.fundyou1.ar.dto.ArImageSaveRequestDto;
import com.ssafy.fundyou1.ar.dto.ArModelSaveRequestDto;
import com.ssafy.fundyou1.ar.entity.ArImage;
import com.ssafy.fundyou1.ar.entity.ArModel;
import com.ssafy.fundyou1.ar.service.ArService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ar")
@Api(tags = {"AR"})
public class ArController {

    @Autowired
    private ArService arService;
    // AR 이미지 url 저장 API
    @ApiOperation(value = "AR 사진 저장", notes ="AR 배치사진 URL을 저장합니다.")
    @GetMapping("/img/save/{fundingItemId}/{url}")
    public ResponseEntity<ArImage> saveArImageUrl(@PathVariable Long fundingItem_id, @PathVariable String url){
        return ResponseEntity.status(HttpStatus.OK).body(arService.saveArImageUrl(fundingItem_id, url));
    }

    @ApiOperation(value = "AR 사진 가져오기", notes = "펀딩 아이디, 아이템 아이디로 상품의 AR배치 사진을 가져옵니다.")
    @GetMapping("/img/list/{fundingItemId}")
    public ResponseEntity<List<ArImage>> getArImageList(@PathVariable Long fundingItemId){
        return ResponseEntity.status(HttpStatus.OK).body(arService.getArImageList(fundingItemId));
    }

    @ApiOperation(value = "AR 모델 저장", notes = "AR 모델파일 URL을 저장합니다.")
    @PostMapping("/model/save")
    public ResponseEntity<ArModel> saveArModelUrl(@RequestBody ArModelSaveRequestDto arModelSaveRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(arService.saveArModelUrl(arModelSaveRequestDto));
    }

    @ApiOperation(value = "AR 모델 가져오기", notes = "아이템 아이디로 AR 모델을 가져옵니다.")
    @GetMapping("/model/{itemId}")
    public ResponseEntity<ArModel> getArModel(@PathVariable Long itemId){
        return ResponseEntity.status(HttpStatus.OK).body(arService.getArModel(itemId));
    }


}
