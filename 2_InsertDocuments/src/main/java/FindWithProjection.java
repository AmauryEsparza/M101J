import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

/**
 * Created by Amaury Esparza on 9/17/2015.
 */
public class FindWithProjection {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("test_db");
        MongoCollection<Document> collection = db.getCollection("insertTest");

        collection.drop();
        for(int i = 0; i < 10; i++){
            collection.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100))
                    .append("i", i));
        }

        //Bson filter = new Document("x", 0)
        //      .append("y", new Document("$gt", 10).append("$lt", 90));
        Bson filter = and(eq("x", 0), gt("y", 10), lt("y",90));

        //This filter means that a x will be exclude.
        //Bson projection = new Document("x", 0).append("_id", 0);
        Bson projection = fields(excludeId(), include("i", "y"));
        List<Document> all = collection.find(filter)
                .projection(projection)
                .into(new ArrayList<Document>());

        for(Document cur: all){
            Helpers.printJson(cur);
        }
        long count = collection.count(filter);
        System.out.println(count);
    }
}
