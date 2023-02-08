package com.ssafy.fundyou1.like.dto;


import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeItemResponseDto {
    @ApiModelProperty(name = "찜목록 회원 아이디 ", example = "1,2")
    Long memberId;
    @ApiModelProperty(name = "아이템 아이디", example = "1,2")
    Long itemId;


    public LikeItemResponseDto(Long itemid, Member member){
        this.memberId = member.getId();
        this.itemId  = itemid;
    }

}
