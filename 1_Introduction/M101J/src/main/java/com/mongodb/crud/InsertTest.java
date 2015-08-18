package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.Helpers.printJSON;
/**
 * Created by Amaury Esparza on 8/18/2015.
 */
public class InsertTest {
    public static void main(String[] args){
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("insertTest");
        collection.drop();


        Document smith = new Document("name", "Smith")
                .append("age", 30)
                .append("profession", "programmer");
        Document jones = new Document("name", "jones")
                .append("age", 32)
                .append("profession", "hacker");
        Document amaury = new Document("name", "Amaury")
                .append("age", 22)
                .append("profession", "mobile developer");

        printJSON(smith);

        collection.insertMany(Arrays.asList(smith, jones, amaury));
        smith.remove("_id");
        collection.insertOne(smith);

        printJSON(smith);
    }
}
