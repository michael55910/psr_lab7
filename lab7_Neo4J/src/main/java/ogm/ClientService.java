package ogm;

import org.neo4j.ogm.session.Session;

public class ClientService extends GenericService<Client> {

    public ClientService(Session session) {
        super(session);
    }

    @Override
    Class<Client> getEntityType() {
        return Client.class;
    }
}
