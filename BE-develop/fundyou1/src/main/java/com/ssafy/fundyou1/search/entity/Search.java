package com.ssafy.fundyou1.search.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Search {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "search_count")
    private Long search_count;

    @Builder
    public Search(String keyword, long search_count) {
        this.keyword = keyword;
        this.search_count = search_count;
    }
}
