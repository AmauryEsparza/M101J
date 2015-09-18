import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Amaury Esparza on 9/14/2015.
 */
public class InsertTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("test_db");
        MongoCollection<Document> collection = db.getCollection("insertTest");

        collection.drop();

        Document smith = new Document("name", "Smith")
                .append("age", 30)
                .append("profession", "programmer");
        Document jones = new Document("name", "Jones")
                .append("age", 22)
                .append("profession", "hacker");


        collection.insertOne(smith);
        collection.insertOne(jones);

        //Find, FindOne and Count
        System.out.println(collection.find().first().toString());
    }
}
