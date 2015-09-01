import DA.StudentDAO;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Amaury Esparza on 8/24/2015.
 */
public class MainClass {

    private final StudentDAO studentDAO;
    public static void main(String[] args){
        MainClass mainClass = new MainClass();

    }

    public MainClass(){
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("school");

        studentDAO = new StudentDAO(database);
        studentDAO.removeLowestHomework();
    }
}
