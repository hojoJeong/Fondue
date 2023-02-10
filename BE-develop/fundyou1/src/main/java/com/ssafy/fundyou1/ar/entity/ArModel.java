package com.ssafy.fundyou1.ar.entity;

import com.ssafy.fundyou1.ar.dto.ArModelSaveRequestDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ArModel {

    @Id
    @GeneratedValue
    @Column(name = "armodel_id")
    private Long armodel_id;

    @Column(name = "item_id")
    private Long item_id;

    @Column(name = "url")
    private String url;

    public ArModel(ArModelSaveRequestDto arModelSaveRquestDto) {
        this.item_id = arModelSaveRquestDto.getItem_id();
        this.url = arModelSaveRquestDto.getUrl();
    }
}
