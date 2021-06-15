package config;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class Config {
    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static Session session;

    public Config() {
        configuration = new Configuration.Builder().uri("bolt://localhost:7687").credentials("neo4j", "toor").build();
        sessionFactory = new SessionFactory(configuration, "ogm");
        session = sessionFactory.openSession();
        session.purgeDatabase();
    }

    public static Session getSession() {
        return session;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
