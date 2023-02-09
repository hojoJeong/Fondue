package com.ssafy.fundyou1.fund.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityScan
public class InvitedMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="invited_member_id")
    private Long id; // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @JsonIgnore
    private Member member; // FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    @JsonIgnore
    private Funding funding;

    @Builder
    public InvitedMember(Long id, Member member, Funding funding) {
        this.id = id;
        this.member = member;
        this.funding = funding;
    }
}
