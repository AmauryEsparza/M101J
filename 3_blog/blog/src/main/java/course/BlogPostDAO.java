package course;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.SimpleDate;
import org.bson.BSON;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import java.util.Locale;

public class BlogPostDAO {
    MongoCollection<Document> postsCollection;

    public BlogPostDAO(final MongoDatabase blogDatabase) {
        postsCollection = blogDatabase.getCollection("posts");
    }

    // Return a single post corresponding to a permalink
    public Document findByPermalink(String permalink) {

        // XXX HW 3.2,  Work Here
        Document post = null;
        try {
            post = postsCollection.find(eq("permalink", permalink)).first();
        }catch(ClassCastException ex){
            ex.printStackTrace();
        }
        return post;
    }

    // Return a list of posts in descending order. Limit determines
    // how many posts are returned.
    public List<Document> findByDateDescending(int limit) {

        // XXX HW 3.2,  Work Here
        // Return a list of DBObjects, each one a post from the posts collection
        List<Document> posts = null;
        try {
            posts = postsCollection.find().sort(new Document("date", -1)).limit(limit).into(new ArrayList<Document>());
            //System.out.println(posts.toString());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return posts;
    }


    public String addPost(String title, String body, List tags, String username) {

        System.out.println("inserting blog entry " + title + " " + body);

        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();


        // XXX HW 3.2, Work Here
        // Remember that a valid post has the following keys:
        // author, body, permalink, tags, comments, date
        //
        // A few hints:
        // - Don't forget to create an empty list of comments
        // - for the value of the date key, today's datetime is fine.
        // - tags are already in list form that implements suitable interface.
        // - we created the permalink for you above.

        // Build the post object and insert it
        Document post = new Document();

        post.put("title", title);
        post.put("author", username);
        post.put("body", body);
        post.put("permalink", permalink);
        post.put("tags", tags);
        post.put("comments", new ArrayList<String>());
        try {
            //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
            Date now = new Date();
            //String nowString = format.format(now);
            //System.out.println("Now" + nowString);
            post.put("date", now);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        postsCollection.insertOne(post);
        return permalink;
    }




    // White space to protect the innocent








    // Append a comment to a blog post
    public void addPostComment(final String name, final String email, final String body,
                               final String permalink) {

        // XXX HW 3.3, Work Here
        // Hints:
        // - email is optional and may come in NULL. Check for that.
        // - best solution uses an update command to the database and a suitable
        //   operator to append the comment on to any existing list of comments
        Document update = new Document().append("author", name).append("body", body);
        if(email != null)
            update.append("email", email);
        postsCollection.updateOne(new Document("permalink", permalink), new Document("$push",
                new Document("comments", update)));

    }
}
