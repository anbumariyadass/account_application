package com.example.localdynamodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.localdynamodb.entity.Item;
import com.example.localdynamodb.service.ItemService;

@RestController
@RequestMapping("/curditem")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping
	public ResponseEntity<String> addItem(@RequestBody Item item) {
		String result = itemService.addItem(item);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{itemId}")
	public ResponseEntity<Item> getItem(@PathVariable String itemId) {
		Item item = itemService.getItem(itemId);
		return ResponseEntity.ok(item);
	}
	
	@PutMapping
	public ResponseEntity<String> updateItem(@RequestBody Item item) {
		String result = itemService.updateItem(item);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{itemId}")
	public ResponseEntity<String> deleteItem(@PathVariable String itemId) {
		String response = itemService.deleteItem(itemId);
		return ResponseEntity.ok(response);
	}
	
}
