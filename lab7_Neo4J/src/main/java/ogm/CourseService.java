package ogm;

import org.neo4j.ogm.session.Session;

import java.util.HashMap;
import java.util.Map;

public class CourseService extends GenericService<Course> {

    public CourseService(Session session) {
		super(session);
	}
    
	@Override
	Class<Course> getEntityType() {
		return Course.class;
	}

/*	Iterable<Map<String, Object>> getCourseRelationships() {
		String query =
				"MATCH (b:Course)-[r]-() " +
						"WITH type(r) AS t, COUNT(r) AS c " +
						"WHERE c >= 1 " +
						"RETURN t, c";
		System.out.println("Executing " + query);
		HashMap<String, Object> params = new HashMap<String, Object>();
		return session.query(query, params).queryResults();
	}*/
}