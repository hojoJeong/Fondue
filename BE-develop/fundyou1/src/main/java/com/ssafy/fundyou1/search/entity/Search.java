package com.ssafy.fundyou1.search.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Search {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "keyword")
    private String keyword;
}
