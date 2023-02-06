package com.ssafy.fundyou1.search.service;

import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import com.ssafy.fundyou1.search.dto.SearchKeyWord;
import com.ssafy.fundyou1.search.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    SearchRepository searchRepository;

    public List<Item> getItemBySearch(SearchKeyWord searchKeyWord){
        return itemRepository.findBySearch(searchKeyWord.getKeyword(), searchKeyWord.getMin_price(), searchKeyWord.getMax_price());
    }

    public List<Item> getItemBySearchRank(){
        return searchRepository.findBySearchRank();
    }
}
