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

    @Column(name = "funding_id")
    private Long funding_id;

    @Column(name = "member_id")
    private Long member_id;

    @Column(name = "item_id")
    private Long item_id;

    @Column(name = "url")
    private String url;

    public ArImage(ArImageSaveRequestDto arImageSaveRequestDto){
        this.funding_id = arImageSaveRequestDto.getFunding_id();
        this.member_id = SecurityUtil.getCurrentMemberId();
        this.item_id = arImageSaveRequestDto.getItem_id();
        this.url = arImageSaveRequestDto.getUrl();
    }
}
