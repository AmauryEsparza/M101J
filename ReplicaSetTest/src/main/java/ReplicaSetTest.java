import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static java.util.Arrays.asList;

/**
 * Created by Amaury Esparza on 9/13/2015.
 */
public class ReplicaSetTest {

    public static void main(String[] args) throws InterruptedException{
        //This it's the normal form of connect
        //MongoClient client = new MongoClient(new ServerAddress("localhost", 27019));
        //With a Replica Set can pass as List of servers
        MongoClient client = new MongoClient(asList(new ServerAddress("localhost", 27017),
                                                    new ServerAddress("localhost", 27018),
                                                    new ServerAddress("localhost", 27019)),
                                                MongoClientOptions.builder().requiredReplicaSetName("rs1").build());

        MongoCollection<Document> collection = client.getDatabase("course").getCollection("replication");
        collection.drop();

        for(int i = 0; i < Integer.MAX_VALUE; i++){
            try {
                collection.insertOne(new Document("_id", i));
                System.out.println("Inserted document: " + i);
            }catch (MongoException ex){
                System.out.println("Exception inserting document " + i + ": " + ex.getMessage());
            }

            Thread.sleep(5000);
        }
    }
}
