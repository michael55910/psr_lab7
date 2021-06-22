package config;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.mapping.ArangoJack;

public class Config {

    private static ArangoDatabase db = null;

    public Config() {
        ArangoDB arangoDB = new ArangoDB.Builder().user("root").password("toor").serializer(new ArangoJack()).build();
        db = arangoDB.db("lab7");
    }

    public static ArangoDatabase getDb() {
        return db;
    }
}
