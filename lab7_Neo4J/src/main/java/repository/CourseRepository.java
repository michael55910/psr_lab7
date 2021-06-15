package repository;

import config.Config;
import ogm.Client;
import ogm.Courier;
import ogm.Course;
import org.neo4j.ogm.session.Session;
import ogm.CourseService;

import java.util.Scanner;

public class CourseRepository {
    CourseService courseService;

    Session session;

    public CourseRepository() {
        session = Config.getSession();
        courseService = new CourseService(session);
    }

    public Course add() {
        Course course = enterCourseInfo();
        courseService.createOrUpdate(course);
        System.out.println(course);
        return course;
    }

    public Course getById(Long id) {
        Course course = courseService.read(id);
        if (course != null) {
            System.out.println(course);
        } else {
            System.out.println("Id not found");
        }
        return course;
    }

    public void deleteById(Long id) {
        Course course = courseService.read(id);
        if (course != null) {
            courseService.delete(id);
        } else {
            System.out.println("Id not found");
        }
    }

    public Course updateById(Long id) {
        Course course = courseService.read(id);
        if (course != null) {
            course = enterCourseInfo();
            course.setId(id);
            courseService.createOrUpdate(course);
            System.out.println(course);
        } else {
            System.out.println("Id not found");
        }
        return course;
    }

    public void getByDestinationCity(String city) {
        Iterable<Course> courses = courseService.readAll();
        if (courses != null) {
            for (Course course : courses) {
                if(course.getDestination().equals(city)) {
                    System.out.println(course + "\n");
                }
            }
        } else {
            System.out.println("empty");
        }
    }

    public void getCountDestinationCity(String city) {
        int count = 0;
        Iterable<Course> courses = courseService.readAll();
        if (courses != null) {
            for (Course course : courses) {
                if(course.getDestination().equals(city)) {
                    count++;
                }
            }
        } else {
            System.out.println("empty");
        }
        System.out.println("Number of courses with that destination: " + count);
    }

    public void getAll() {
        Iterable<Course> courses = courseService.readAll();
        if (courses != null) {
            for (Course course : courses) {
                System.out.println(course + "\n");
            }
        } else {
            System.out.println("empty");
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
        return new Course(destination, source, new Client(clientName, clientSurname), new Courier(courierName, courierSurname));
    }
}
