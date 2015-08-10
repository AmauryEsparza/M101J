package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Amaury Esparza on 8/10/2015.
 */
public class SparkRoutes {
    public static void main(String[] args) {
        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World";
            }
        });

        Spark.get("/test", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return "Test page";
            }
        });

        Spark.get("/echo/:thing", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return request.params(":thing");
            }
        });

    }
}
