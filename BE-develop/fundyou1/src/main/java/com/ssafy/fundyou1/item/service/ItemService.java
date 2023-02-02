package com.ssafy.fundyou1.item.service;

import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }


    @Transactional
    public void updateItem(Long itemId,String title, int price, String image, String descriptionImg, String isAr, String description, String brand, Category category ) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setTitle(title);
        findItem.setPrice(price);
        findItem.setDescription(description);
        findItem.setBrand(brand);
        findItem.setDescriptionImg(descriptionImg);
        findItem.setCategory(category);
        findItem.setIsAr(isAr);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
