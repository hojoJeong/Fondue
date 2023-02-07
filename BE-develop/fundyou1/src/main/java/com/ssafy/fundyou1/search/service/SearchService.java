package com.ssafy.fundyou1.search.service;

import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.search.dto.SearchKeyWord;
import com.ssafy.fundyou1.search.entity.Search;
import com.ssafy.fundyou1.search.repository.SearchRepository;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    SearchRepository searchRepository;

    @Transactional
    public List<Item> getItemBySearch(SearchKeyWord searchKeyWord) {
        addSearchCount(searchKeyWord.getKeyword());
        return itemRepository.findBySearch(searchKeyWord.getKeyword(), searchKeyWord.getMin_price(), searchKeyWord.getMax_price());
    }

    public List<Search> getItemBySearchRank() {
        return searchRepository.findSearchKeywordRankTop10();
    }

    @Transactional
    public void addSearchCount(String keyword) {
        Search search = Search.builder().keyword(keyword).search_count(0).build();
        if (duplicateSearchKeywordCheck(keyword)) {
            // 키워드가 이미 있음. 카운트 + 1
            searchRepository.increaseSearchCount(keyword);
        } else {
            searchRepository.save(search);
            searchRepository.increaseSearchCount(keyword);
        }
    }

    public boolean duplicateSearchKeywordCheck(String keyword){
        return searchRepository.existsByKeyword(keyword);
    }
}
