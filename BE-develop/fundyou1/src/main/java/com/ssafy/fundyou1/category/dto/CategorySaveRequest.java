package com.ssafy.fundyou1.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.category.entity.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//카테고리 저장 DTO
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveRequest {

    @JsonProperty("categoryName")
    private String categoryName;

    public Category toCategory() {
        return Category.createCategory(categoryName);
    }

}
