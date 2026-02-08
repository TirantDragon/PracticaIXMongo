package com.mycompany.practicaixmongo.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.practicaixmongo.Conexion.ConexionMongo;
import com.mycompany.practicaixmongo.Model.Monsters;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class MHDao {

    private MongoCollection<Document> collection;

    public MHDao() {
        MongoDatabase db = ConexionMongo.getDatabase();
        collection = db.getCollection("monsterHunter");
    }

    // SELECT ALL
    public List<Monsters> selectAll() {

        List<Monsters> lista = new ArrayList<>();

        for (Document doc : collection.find()) {
            lista.add(documentToMonster(doc));
        }

        return lista;
    }

    // SELECT BY ID
    public Monsters selectById(int id) {

        Document doc = collection.find(eq("id", id)).first();

        if (doc == null) return null;

        return documentToMonster(doc);
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
        Monsters m = new Monsters();
        m.setId(doc.getInteger("id"));
        m.setName(doc.getString("name"));
        m.setSpecies(doc.getString("species"));
        m.setType(doc.getString("type"));
        m.setDescription(doc.getString("description"));
        m.setElements((List<String>) doc.get("elements"));
        return m;
    }

    private Document monsterToDocument(Monsters m) {

        Document doc = new Document();
        doc.append("id", m.getId());
        doc.append("name", m.getName());
        doc.append("species", m.getSpecies());
        doc.append("type", m.getType());
        doc.append("description", m.getDescription());
        doc.append("elements", m.getElements());
        return doc;
    }
}
