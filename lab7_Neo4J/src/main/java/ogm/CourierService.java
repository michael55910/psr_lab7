package ogm;

import org.neo4j.ogm.session.Session;

public class CourierService extends GenericService<Courier>{

    public CourierService(Session session) {
        super(session);
    }

    @Override
    Class<Courier> getEntityType() {
        return Courier.class;
    }
}
