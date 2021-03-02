package me.Asylx.Utils;



import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.entity.Player;

import java.net.UnknownHostException;

public class Mongo {

    public static MongoDatabase mongoDatabase;
    public static MongoCollection mongoCollection;

    public static void SetupMongoDB() throws UnknownHostException {
        MongoClientURI uri = new MongoClientURI("mongodb+srv://Admin:admin@data.rpud2.mongodb.net/Players?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        mongoDatabase = mongoClient.getDatabase("Data");
        mongoCollection = mongoDatabase.getCollection("Players");

        System.out.println("Database CONNECTED.");
    }

    public static Document getData(Player p) {
        Document PlayerData = (Document) mongoCollection.find(new Document("UUID", p.getUniqueId().toString())).first();
        return PlayerData;
    }

    public static void InsertPlayerData(Player p, String object, Object newValueObject) {
        Document PlayerData = (Document) mongoCollection.find(new Document("UUID", p.getUniqueId().toString())).first();

        if (PlayerData != null) {
            Bson newValue = new Document(object, newValueObject);
            Bson updatedOperation = new Document("$set", newValue);
            mongoCollection.updateOne(PlayerData, updatedOperation);

            System.out.println("OPERATION UPDATED.");
        } else {
            System.out.println("FAILED. NO DATA FOUND!");
        }

    }


}
