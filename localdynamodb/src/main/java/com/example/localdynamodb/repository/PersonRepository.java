package com.example.localdynamodb.repository;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.localdynamodb.entity.Person;

@Repository
public class PersonRepository {

    private final DynamoDbTable<Person> personTable;
    
    private DynamoDbClient dynamoDbClient;

    public PersonRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDbClient) {
    	this.dynamoDbClient = dynamoDbClient;
        this.personTable = enhancedClient.table("Person", TableSchema.fromBean(Person.class));  //Explicit table name
    }

    public void save(Person person) {
        personTable.putItem(person);
    }

    public Person findById(String personId) {
        return personTable.getItem(r -> r.key(k -> k.partitionValue(personId)));
    }

    public void deleteById(String personId) {
        personTable.deleteItem(r -> r.key(k -> k.partitionValue(personId)));
        
    }
    
    public List<String> getTables() {
    	return dynamoDbClient.listTables().tableNames();
    }
    
    
    
    
}

