package com.mycompany.practicaixmongo.DAO;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mycompany.practicaixmongo.Conexion.ConexionMongo;
import com.mycompany.practicaixmongo.Model.Monsters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

public class MHDao {

    private MongoCollection<Document> collection;

    public MHDao() {
        MongoDatabase db = ConexionMongo.getDatabase();
        collection = db.getCollection("monsterHunter");
    }

    public List<Monsters> selectAll() {
        List<Monsters> lista = new ArrayList<>();
        List<Bson> pipeline = Arrays.asList(
                Aggregates.project(Projections.fields(
                        Projections.include("id", "name", "species", "type", "description", "elements")
                ))
        );
        AggregateIterable<Document> result = collection.aggregate(pipeline);
        for (Document doc : result) {
            lista.add(documentToMonster(doc));
        }
        return lista;
    }

    // INSERT
    public void insert(Monsters monster) {
        collection.insertOne(monsterToDocument(monster));
    }

    // UPDATE
    public void update(Monsters monster) {
        Document update = new Document("$set", monsterToDocument(monster));
        collection.updateOne(eq("id", monster.getId()), update);
    }

    // DELETE
    public void delete(int id) {
        collection.deleteOne(eq("id", id));
    }

    private Monsters documentToMonster(Document doc) {
        Monsters monstruo = new Monsters();
        monstruo.setId(doc.getInteger("id"));
        monstruo.setName(doc.getString("name"));
        monstruo.setSpecies(doc.getString("species"));
        monstruo.setType(doc.getString("type"));
        monstruo.setDescription(doc.getString("description"));
        monstruo.setElements((List<String>) doc.get("elements"));
        return monstruo;
    }

    private Document monsterToDocument(Monsters montruo) {

        Document doc = new Document();
        doc.append("id", montruo.getId());
        doc.append("name", montruo.getName());
        doc.append("species", montruo.getSpecies());
        doc.append("type", montruo.getType());
        doc.append("description", montruo.getDescription());
        doc.append("elements", montruo.getElements());
        return doc;
    }
}
