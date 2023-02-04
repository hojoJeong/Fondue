package com.ssafy.fundyou1.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.category.entity.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class CategorySaveRequest {

    @ApiModelProperty(position = 1, notes = "카테고리명", example = "인테리어")
    @JsonProperty("categoryName")
    private String categoryName;


    public CategorySaveRequest() {

    }

    public CategorySaveRequest(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category toCategory() {
        return Category.createCategory(categoryName);
    }

}
