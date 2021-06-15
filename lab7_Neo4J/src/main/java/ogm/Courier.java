package ogm;

import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@ToString
@NodeEntity(label = "Courier")
public class Courier {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "surname")
    private String surname;

    public Courier(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Courier(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Courier() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
