package com.ssafy.fundyou1.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.category.entity.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveRequest {

    @ApiModelProperty(position = 1, notes = "카테고리명", example = "인테리어")
    @JsonProperty("categoryName")
    private String categoryName;

    public Category toCategory() {
        return Category.createCategory(categoryName);
    }

}
