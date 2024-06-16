package com.itacademy.Persistance.Mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
public class DatabaseConnection {
    private static MongoClient mongoClient = null;
    private static MongoDatabase database = null;

    public static void connect() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            database = mongoClient.getDatabase("thelittleshopofhorrorsdb");
            System.out.println("Conexión a MongoDB establecida.");
        }
    }

    public static void disconnect() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            database = null;
            System.out.println("Conexión a MongoDB cerrada.");
        }
    }

    public static MongoDatabase getDatabase() {
        if (database == null) {
            connect();
        }
        return database;
    }

}
