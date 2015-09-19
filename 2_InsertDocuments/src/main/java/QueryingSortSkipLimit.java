import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

/**
 * Created by Amaury Esparza on 9/18/2015.
 */
public class QueryingSortSkipLimit {

    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("test_db");
        MongoCollection<Document> collection = db.getCollection("insertTest");

        collection.drop();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++) {
                collection.insertOne(new Document()
                        .append("i", i)
                        .append("j", j));
            }
        }

        Bson projection = fields(excludeId(), include("i", "j"));
        Bson sort = new Document("i", 1).append("j", -1);
        List<Document> all = collection.find().projection(projection)
                .sort(sort)
                .skip(20)
                .limit(50)
                .into(new ArrayList<Document>());

        for(Document cur : all){
            Helpers.printJson(cur);
        }
    }
}
