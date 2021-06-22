package repository;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDBException;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import config.Config;
import model.Client;
import model.Courier;
import model.Course;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class CourseRepository {

    private final Random random = new Random(System.currentTimeMillis());

    final ArangoDatabase db;
    final String collectionName = "courses";
    final ArangoCollection collection;

    public CourseRepository() {
        db = Config.getDb();
        try {
            CollectionEntity myArangoCollection = db.createCollection(collectionName);
            System.out.println("Collection created: " + myArangoCollection.getName());
        } catch (ArangoDBException e) {
            System.err.println("Failed to create collection: " + collectionName + "; " + e.getMessage());
        }
        collection = db.collection(collectionName);
    }

    public Course add() {
        Course course = enterCourseInfo();
        collection.insertDocument(course.toBaseDocument());
        System.out.println(course);
        return course;
    }

    public Course getById(Long id) {
        Course course = null;
        if (collection.documentExists(id.toString())) {
            BaseDocument courseDocument = collection.getDocument(id.toString(), BaseDocument.class);
            course = Course.parseDocument(courseDocument);
            System.out.println(course);
        } else {
            System.out.println("Id not found");
        }
        return course;
    }

    public void deleteById(Long id) {
        if (collection.documentExists(id.toString())) {
            collection.deleteDocument(id.toString());
        } else {
            System.out.println("Id not found");
        }
    }

    public Course updateById(Long id) {
        Course course = null;
        if (collection.documentExists(id.toString())) {
            course = enterCourseInfo();
            course.setId(id);
            collection.replaceDocument(id.toString(), course.toBaseDocument());
            System.out.println(course);
        } else {
            System.out.println("Id not found");
        }
        return course;
    }

    public void getByDestinationCity(String city) {

        try {
            String query = "FOR t IN " + collectionName + " FILTER t.destination == @city RETURN t";
            Map<String, Object> bindVars = Collections.singletonMap("city", city);
            ArangoCursor<BaseDocument> cursor = db.query(query, bindVars, null, BaseDocument.class);
            cursor.forEachRemaining(aDocument -> {
                System.out.println(Course.parseDocument(aDocument) + "\n");
            });
        } catch (ArangoDBException e) {
            System.err.println("Failed to execute query. " + e.getMessage());
        }
    }

    public void getCountDestinationCity(String city) {

        try {
            String query = "FOR t IN " + collectionName + " FILTER t.destination == @city COLLECT WITH COUNT INTO length RETURN length";
            Map<String, Object> bindVars = Collections.singletonMap("city", city);
            ArangoCursor<Integer> cursor = db.query(query, bindVars, null, Integer.class);
            cursor.forEachRemaining(count -> {
                System.out.println("Number of courses with " + city + " as destination:" + count);
            });
        } catch (ArangoDBException e) {
            System.err.println("Failed to execute query. " + e.getMessage());
        }
    }

    public void getAll() {
        try {
            String query = "FOR t IN " + collectionName + " RETURN t";
            ArangoCursor<BaseDocument> cursor = db.query(query, null, null, BaseDocument.class);
            cursor.forEachRemaining(aDocument -> {
                System.out.println(Course.parseDocument(aDocument));
            });
        } catch (ArangoDBException e) {
            System.err.println("Failed to execute query. " + e.getMessage());
        }
    }

    private Course enterCourseInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Destination: ");
        String destination = scanner.next();
        System.out.print("Source: ");
        String source = scanner.next();
        System.out.print("Client name: ");
        String clientName = scanner.next();
        System.out.print("Client surname: ");
        String clientSurname = scanner.next();
        System.out.print("Courier name: ");
        String courierName = scanner.next();
        System.out.print("Courier surname: ");
        String courierSurname = scanner.next();
        return new Course(Math.abs(random.nextLong()), destination, source, new Client(Math.abs(random.nextLong()), clientName, clientSurname), new Courier(Math.abs(random.nextLong()), courierName, courierSurname));
    }
}
