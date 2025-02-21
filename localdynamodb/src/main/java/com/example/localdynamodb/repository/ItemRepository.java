package com.example.localdynamodb.repository;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import org.springframework.stereotype.Repository;
import com.example.localdynamodb.entity.Item;

@Repository
public class ItemRepository {

    private final DynamoDbTable<Item> itemTable;
    

    public ItemRepository(DynamoDbEnhancedClient enhancedClient) {
        this.itemTable = enhancedClient.table("Item", TableSchema.fromBean(Item.class));  //Explicit table name
    }

    public void save(Item item) {
    	itemTable.putItem(item);
    }

    public Item findById(String itemId) {
        return itemTable.getItem(r -> r.key(k -> k.partitionValue(itemId)));
    }

    public void deleteById(String itemId) {
    	itemTable.deleteItem(r -> r.key(k -> k.partitionValue(itemId)));
        
    }
    
}

