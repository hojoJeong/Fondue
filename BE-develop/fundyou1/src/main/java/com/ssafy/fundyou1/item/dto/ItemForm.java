package com.ssafy.fundyou1.item.dto;

import com.ssafy.fundyou1.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemForm {
    private Long id;
    private int price;

    private String image;

    private String descriptionImg;

    private String title;

    private Boolean isAr;

    private String description;
    
    private String brand;

    private Category category;

    private int sellingCount;

}
