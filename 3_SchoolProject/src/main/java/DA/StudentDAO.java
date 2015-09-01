package DA;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amaury Esparza on 8/24/2015.
 */
public class StudentDAO {
    private final MongoCollection<Document> studentsCollection;

    public StudentDAO(final MongoDatabase schoolDatabase){
        studentsCollection =  schoolDatabase.getCollection("students");
    }

    public void removeLowestHomework(){
        try {
            Document filter = new Document();
            List<Document> students = getAllStudents();

            for(Document student : students){
                BasicDBList scores = (BasicDBList) student.get("scores");

                BasicDBObject homework1 = (BasicDBObject) scores.get("2");
                BasicDBObject homework2 = (BasicDBObject) scores.get("3");

                if (homework1.getDouble("score") > homework2.getDouble("score")){
                    scores.remove(3);
                }else{
                    scores.remove(2);
                }
                student.put("scores", scores);
                studentsCollection.replaceOne(Filters.eq("_id", student.get("_id")), student);
                System.out.println(student);
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Document> getAllStudents(){
        return studentsCollection.find().into(new ArrayList<Document>());
    }
}
