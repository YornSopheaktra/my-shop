package com.my.shop.myshop.domain;

import com.my.shop.myshop.entity.Item;

import java.util.List;

public interface ItemService {
    public List<Item> getAllItem();
    public Item getItemById(Integer id);
    public Item saveOrUpdate(Item item);
}
