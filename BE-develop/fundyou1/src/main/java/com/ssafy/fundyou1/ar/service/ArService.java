package com.ssafy.fundyou1.ar.service;

import com.ssafy.fundyou1.ar.dto.ArImageSaveRequestDto;
import com.ssafy.fundyou1.ar.dto.ArModelSaveRequestDto;
import com.ssafy.fundyou1.ar.entity.ArImage;
import com.ssafy.fundyou1.ar.entity.ArModel;
import com.ssafy.fundyou1.ar.repository.ArModelRepository;
import com.ssafy.fundyou1.ar.repository.ArImageRepository;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ArService {
    @Autowired
    private ArImageRepository arImageRepository;
    @Autowired
    private ArModelRepository arModelRepository;

    @Transactional
    public ArImage saveArImageUrl(ArImageSaveRequestDto arImageSaveRequestDto){
        return arImageRepository.save(new ArImage(arImageSaveRequestDto));
    }

    @Transactional
    public List<ArImage> getArImageList(Long funding_id, Long item_id){
        return arImageRepository.findArImageList(funding_id, item_id, SecurityUtil.getCurrentMemberId());
    }

    @Transactional
    public ArModel saveArModelUrl(ArModelSaveRequestDto arModelSaveRequestDto){
        return arModelRepository.save(new ArModel(arModelSaveRequestDto));
    }

    @Transactional
    public ArModel getArModel(Long item_id){
        return arModelRepository.findArModelByItemId(item_id);
    }
}
