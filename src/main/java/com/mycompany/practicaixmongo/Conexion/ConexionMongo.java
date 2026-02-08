package com.mycompany.practicaixmongo.Conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private static final String CONNECTION_STRING =
            "mongodb+srv://<usuario>:<contrasenya>@cluster0.mw5y8zu.mongodb.net/?appName=Cluster0";//Cambiar el usuario y contraseña por los de tu base de datos

    private static final String DB_NAME = "AED";//Cambiar el nombre de la base de datos por el que tengas en MongoDB

    public static void conectar() {

        if (mongoClient == null) {

            ServerApi serverApi = ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build();

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                    .serverApi(serverApi)
                    .build();

            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(DB_NAME);

            System.out.println("Mongo conectado correctamente");
        }
    }

    public static MongoDatabase getDatabase() {
        if (database == null) {
            conectar();
        }
        return database;
    }

    public static void cerrar() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
