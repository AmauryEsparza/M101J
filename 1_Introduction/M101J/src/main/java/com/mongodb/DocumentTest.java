package com.mongodb;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;

import static com.mongodb.Helpers.printJSON;

/**
 * Created by Amaury Esparza on 8/18/2015.
 */
public class DocumentTest {

    public static void main(String[] args){
        Document document = new Document()
                .append("str", "MongoDB, hello")
                .append("int", 42)
                .append("long", 1L)
                .append("double", 1.1)
                .append("bool", false)
                .append("date", new Date())
                .append("objectId", new ObjectId())
                .append("null", null)
                .append("embeddedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1,2,3));

        printJSON(document);
    }
}
