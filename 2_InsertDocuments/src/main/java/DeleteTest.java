import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

/**
 * Created by Amaury Esparza on 9/19/2015.
 */
public class DeleteTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("test_db");
        MongoCollection<Document> collection = db.getCollection("insertTest");

        collection.drop();
        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document()
                    .append("_id", i)
                    .append("x", i));

        }

        //collection.deleteOne(new Document("_id", 1));

        collection.deleteMany(new Document("_id", new Document("$gt", 4)));

        for(Document curr: collection.find().into(new ArrayList<Document>())){
            Helpers.printJson(curr);
        }
    }
}
