import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by Amaury Esparza on 9/19/2015.
 */
public class UpdateReplaceTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("test_db");
        MongoCollection<Document> collection = db.getCollection("insertTest");

        collection.drop();
        for(int i = 0; i < 8; i++) {
            collection.insertOne(new Document()
                    .append("_id", i)
                    .append("x", i));

        }

        //collection.replaceOne(eq("x", 5), new Document("_id", 5).append("x", 20).append("update", true));

        //collection.updateOne(eq("_id", 9), new Document("$set", new Document("x", 20)), new UpdateOptions().upsert(true));

        collection.updateMany(gt("_id", 5), new Document("$inc", new Document("x",1)));

        for(Document curr: collection.find().into(new ArrayList<Document>())){
            Helpers.printJson(curr);
        }
    }
}
