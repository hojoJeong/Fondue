package com.ssafy.fundyou1.ar.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArImageSaveRequestDto {
    private Long funding_id;

    private Long item_id;

    private String url;
}
