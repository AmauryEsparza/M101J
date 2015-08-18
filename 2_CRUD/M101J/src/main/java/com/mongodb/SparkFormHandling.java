package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

/**
 * Created by Amaury Esparza on 8/10/2015.
 */
public class SparkFormHandling {

    public static void main(String[] args) {
        //Configure Freemarker
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        //Configure spark
        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();
                try {
                    Template fruitPickerTemplate = configuration.getTemplate("fruit_picker.ftl");
                    Map<String, Object> fruitsMap = new HashMap<String, Object>();
                    fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));

                    fruitPickerTemplate.process(fruitsMap, writer);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });

        //Handle the post method
        Spark.post("/favorite_fruit", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                final String fruit = request.queryParams("fruit");

                if(fruit == null){
                    return "Why don't you pick one?";
                }
                else{
                    return "Your favorite fruit is " + fruit;
                }
            }
        });

    }
}
