package ogm;

import lombok.*;
import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@ToString
@NodeEntity(label = "Course")
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "destination")
    private String destination;

    @Property(name = "origin")
    private String origin;

    @Relationship(type = "COURSE_CLIENT")
    private Set<Client> clients = new HashSet<>();

    @Relationship(type = "COURSE_COURIER")
    private Set<Courier> couriers = new HashSet<>();

    public Course(String destination, String origin, Client client, Courier courier) {
        this.destination = destination;
        this.origin = origin;
        this.clients.add(client);
        this.couriers.add(courier);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Courier> getCouriers() {
        return couriers;
    }

    public void setCouriers(Set<Courier> couriers) {
        this.couriers = couriers;
    }

    public Course() {
    }

    public Course(Long id, String destination, String origin, Set<Client> clients, Set<Courier> couriers) {
        this.id = id;
        this.destination = destination;
        this.origin = origin;
        this.clients = clients;
        this.couriers = couriers;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addCourier(Courier courier) {
        couriers.add(courier);
    }

}
