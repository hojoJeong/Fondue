package com.ssafy.fundyou1.item.repository;

import com.ssafy.fundyou1.item.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            //신규 등록
            em.persist(item);

        } else {
            //id값 있으면 합친다.
            em.merge(item);
        }
    }
    // 아이템 하나 찾는거
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }
    // 아이템 전부 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
