package com.ssafy.fundyou1.item.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.category.repository.CategoryRepository;
import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.item.dto.*;
import com.ssafy.fundyou1.item.entity.Description;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.DescriptionRepository;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.like.dto.LikeItemResponseDto;
import com.ssafy.fundyou1.like.entity.Like;
import com.ssafy.fundyou1.like.repository.LikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.metadata.ItemMetadata;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    //상품 데이터 추가
    @Transactional
    public Long saveItem(ItemSaveRequest request) {
        // 브랜드 이름, 중복검사
        checkDuplicateItemTitle(request.getTitle(), request.getBrand());
        // 카테고리찾기
        Category category = categoryRepository.findByCategoryName(request.getCategoryName());
        // 아이템 먼저 저장
        Item item = request.toItem(category);
        // description 여러개 리스트
        for(Description description : request.getDescription()) {
            Description newDescription  = Description.builder()
                    .item(item) // 위에서 등록한 itemEntity
                    .itemType(description .getItemType())  // 필드 1
                    .content(description .getContent())  // 필드 2
                    .build();
            descriptionRepository.save(newDescription);
        }
        return itemRepository.save(item).getId();
    }


    // 상품 이름 브랜드 중복 검사
    public void checkDuplicateItemTitle(String title, String brand) {
        if (itemRepository.existsByTitleAndBrand(title, brand)) {
            throw new BusinessException(ErrorCode.ITEM_TITLE_BRAND_DUPLICATED);
        }
    }

    @Transactional
    // 카테고리별 아이템 불러오기
    public List<ItemResponseDto> getCategoryItemList(Long categoryId) {
        if(categoryId != 0){
           return matchFavoriteItem(itemRepository.findAllByCategoryId(categoryId), SecurityUtil.getCurrentMemberId());
        }else {
            return matchFavoriteItem(itemRepository.findAll(), SecurityUtil.getCurrentMemberId());
        }
    }
    @Transactional
    // 상품 디테일
    public ItemResponseDto itemDetail(Long id) {
        Optional<Item> findItem = itemRepository.findById(id);
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        if (findItem.isPresent()) {
            Item item = findItem.get();
            if(likeRepository.findLikeItem(item.getId(), SecurityUtil.getCurrentMemberId()) != null) {
                itemResponseDto = new ItemResponseDto(item, true);
            } else {
                itemResponseDto = new ItemResponseDto(item, false);
            }
        }
        return itemResponseDto;
    }

    @Transactional
    public List<ItemResponseDto> getRandomItemList() {
        // 멤버가 좋아하는 아이템
        return matchFavoriteItem(itemRepository.findSixRandomItem(), SecurityUtil.getCurrentMemberId());
    }

    @Transactional
    // 멤버별별 구분 아이템 전체 리스트
    public List<ItemResponseDto> findAllItem(Long memberId) {
        List<Item> totalItemList = itemRepository.findAll();
        return matchFavoriteItem(totalItemList, memberId);
    }

    // 특정 아이템리스트에 찜 status 변경 함수
    public List<ItemResponseDto> matchFavoriteItem(List<Item> totalItemList, Long memberId){
        List<Like> likeItemList = likeRepository.findAllByMember_Id(memberId);
        List<ItemResponseDto> itemResponseDtoList = new ArrayList();
        if(likeItemList.size() == 0){
            for(Item item: totalItemList){
                itemResponseDtoList.add(new ItemResponseDto(item, false));
            }
        }else{
            for(Item item: totalItemList){
                boolean flag = false;
                for(Like likeItem: likeItemList){
                    if(item.getId().equals(likeItem.getItem_id())){
                        flag = true;
                        itemResponseDtoList.add(new ItemResponseDto(item, true));
                        break;
                    }
                }
                if(flag == false){
                    itemResponseDtoList.add(new ItemResponseDto(item, false));
                }
            }
        }
        return itemResponseDtoList;
    }
    @Transactional
    public List<ItemResponseDto> getItemListWithFilter(Long categoryId, Long minPrice, Long maxPrice) {
        if(categoryId == 0){
            return matchFavoriteItem(itemRepository.findItemWithFilterNoCategory(minPrice,maxPrice), SecurityUtil.getCurrentMemberId());
        }else{
            return matchFavoriteItem(itemRepository.findItemWithFilter(categoryId, minPrice, maxPrice), SecurityUtil.getCurrentMemberId());
        }
    }

    @Transactional
    public List<ItemResponseDto> getRankItemListWithFilter(Long categoryId, Long minPrice, Long maxPrice){
        if (categoryId != 0){
            return matchFavoriteItem(itemRepository.findTopItem(categoryId, minPrice, maxPrice), SecurityUtil.getCurrentMemberId());
        }else{
            return matchFavoriteItem(itemRepository.findTopItemNoCategory(minPrice, maxPrice), SecurityUtil.getCurrentMemberId());
        }
    }
}
