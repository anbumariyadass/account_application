package com.example.localdynamodb.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;

@DynamoDbBean
public class Item {

    private String itemId;
    private String name;
    private double price;

    // Default constructor (required by DynamoDB SDK)
    public Item() {}

    public Item(String itemId, String name, int age, double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    @DynamoDbPartitionKey  //Marks this as the Primary Key
    @DynamoDbAttribute("itemId")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDbAttribute("price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}

