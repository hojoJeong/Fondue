package com.ssafy.fundyou1.category.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.item.entity.Item;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;


    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();


    @Builder
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public static Category createCategory(String categoryName) {
        return Category.builder()
                .categoryName(categoryName)
                .build();
    }


}
