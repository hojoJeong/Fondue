package com.ssafy.fundyou1.ar.service;

import com.ssafy.fundyou1.ar.dto.ArImageSaveRequestDto;
import com.ssafy.fundyou1.ar.entity.ArImage;
import com.ssafy.fundyou1.ar.repository.ArRepository;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ArService {
    @Autowired
    private ArRepository arRepository;

    @Transactional
    public ArImage saveArImageUrl(ArImageSaveRequestDto arImageSaveRequestDto){
        return arRepository.save(new ArImage(arImageSaveRequestDto));
    }

    @Transactional
    public List<ArImage> getArImageList(Long funding_id, Long item_id){
        return arRepository.findArImageList(funding_id, item_id, SecurityUtil.getCurrentMemberId());
    }
}
