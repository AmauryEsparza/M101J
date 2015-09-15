import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;


/**
 * Created by Amaury Esparza on 9/14/2015.
 */
public class DocumentTest {
    public static void main(String[] args) {
        Document document = new Document()
                .append("str", "MongoDB, Hello")
                .append("int", 42)
                .append("l", 1L)
                .append("double", 1.1)
                .append("b", false)
                .append("date", new Date())
                .append("objectId", new ObjectId())
                .append("null", null)
                .append("embeddedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1,2,3));

        Helpers.printJson(document);

        BsonDocument bsonDocument = new BsonDocument("str", new BsonString("MongoDB, Hello"));


    }
}
