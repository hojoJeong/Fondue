package com.ssafy.fundyou1.ar.entity;

import com.ssafy.fundyou1.ar.dto.ArImageSaveRequestDto;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArImage {
    @Id
    @GeneratedValue
    @Column(name = "arimage_id")
    private Long id;

    @Column(name = "funding_item_id")
    private Long funding_item_id;
    @Column(name = "url")
    private String url;

    public ArImage(Long funding_item_id, String url){
        this.funding_item_id = funding_item_id;
        this.url = url;
    }
}
