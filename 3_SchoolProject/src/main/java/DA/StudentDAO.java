package DA;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amaury Esparza on 8/24/2015.
 */
public class StudentDAO {
    private final DBCollection studentsCollection;

    public StudentDAO(final DB schoolDatabase){
        studentsCollection =  schoolDatabase.getCollection("students");
    }

    public void removeLowestHomework(){
        try {
            Document filter = new Document();
            DBCursor students = getAllStudents();

            while(students.hasNext()){
                BasicDBObject student = (BasicDBObject) students.next();
                BasicDBList scores = (BasicDBList) student.get("scores");
                BasicDBObject homework1 = (BasicDBObject) scores.get("2");
                BasicDBObject homework2 = (BasicDBObject) scores.get("3");

                if (homework1.getDouble("score") > homework2.getDouble("score")){
                    scores.remove(3);
                }else{
                    scores.remove(2);
                }
                student.put("scores", scores);
                studentsCollection.save(student);
                System.out.println(student);
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public DBCursor getAllStudents(){
        return studentsCollection.find();
    }
}
