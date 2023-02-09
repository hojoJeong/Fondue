package com.ssafy.fundyou1.ar.api;

import com.ssafy.fundyou1.ar.dto.ArImageSaveRequestDto;
import com.ssafy.fundyou1.ar.entity.ArImage;
import com.ssafy.fundyou1.ar.service.ArService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ar")
public class ArImageController {

    @Autowired
    private ArService arService;
    // AR 이미지 url 저장 API
    @ApiOperation(value = "AR 사진 저장", notes ="AR배치 사진 URL을 저장합니다.")
    @PostMapping("/img/save")
    public ResponseEntity<ArImage> saveArImageUrl(@RequestBody ArImageSaveRequestDto arImageSaveRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(arService.saveArImageUrl(arImageSaveRequestDto));
    }

    @ApiOperation(value = "AR 사진 가져오기", notes = "상품의 AR배치 사진을 가져옵니다.")
    @GetMapping("/img/list/{funding_id}/{item_id}")
    public ResponseEntity<List<ArImage>> getArImageList(@PathVariable Long funding_id, @PathVariable Long item_id){
        return ResponseEntity.status(HttpStatus.OK).body(arService.getArImageList(funding_id,item_id));
    }
}
