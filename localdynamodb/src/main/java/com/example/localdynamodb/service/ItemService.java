package com.example.localdynamodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.localdynamodb.entity.Item;
import com.example.localdynamodb.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
    private ItemRepository itemRepository;
	
	public String addItem(Item item) {
		itemRepository.save(item);
		return item.getName() +" is added successfully";
	}
	
	public Item getItem(String itemId) {
		return itemRepository.findById(itemId);
	}
	
	public String updateItem(Item item) {
		itemRepository.save(item);
		return item.getName() +" is updated successfully";
	}
	
	public String deleteItem(String itemId) {
		itemRepository.deleteById(itemId);
		return itemId +" is deleted successfully";
	}
}
