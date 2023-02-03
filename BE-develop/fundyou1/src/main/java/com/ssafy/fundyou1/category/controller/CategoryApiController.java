package com.ssafy.fundyou1.category.controller;

import com.ssafy.fundyou1.category.dto.CategorySaveRequest;
import com.ssafy.fundyou1.category.service.CategoryService;
import com.ssafy.fundyou1.member.dto.request.MemberSaveRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = {"카테고리"})
public class CategoryApiController {

    private final CategoryService categoryService;


    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    @ApiOperation(value = "카테고리", notes = "카테고리 생성")
    @ApiResponses({
            @ApiResponse(code = 409, message = "CONFLICT\n카테고리 아이디 중복(C01)\n카테고리 이름 중복(C02)")
    })
    public ResponseEntity<Map<String,Object>> save(@RequestBody CategorySaveRequest request) {
        String categoryName = categoryService.saveCategory(request);
        Map<String,Object> result = new HashMap<>();
        result.put("categoryName",categoryName );
        return ResponseEntity.ok().body(result);
    }


}
