package model;

import com.arangodb.entity.BaseDocument;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {

    private Long id;

    private String destination;

    private String origin;

    private Client client;

    private Courier courier;

    public static Course parseDocument(BaseDocument courseDocument) {
        return new Course(Long.parseLong(courseDocument.getKey()),
                courseDocument.getAttribute("destination").toString(),
                courseDocument.getAttribute("origin").toString(),
                new Client(Long.parseLong(courseDocument.getAttribute("client_id").toString()),
                        courseDocument.getAttribute("client_name").toString(),
                        courseDocument.getAttribute("client_surname").toString()),
                new Courier(Long.parseLong(courseDocument.getAttribute("courier_id").toString()),
                        courseDocument.getAttribute("courier_name").toString(),
                        courseDocument.getAttribute("courier_surname").toString()));
    }

    public BaseDocument toBaseDocument() {
        BaseDocument courseDocument = new BaseDocument();
        courseDocument.setKey(this.id.toString());
        courseDocument.addAttribute("destination", this.destination);
        courseDocument.addAttribute("origin", this.origin);
        courseDocument.addAttribute("client_id", this.client.getId());
        courseDocument.addAttribute("client_name", this.client.getName());
        courseDocument.addAttribute("client_surname", this.client.getSurname());
        courseDocument.addAttribute("courier_id", this.courier.getId());
        courseDocument.addAttribute("courier_name", this.courier.getName());
        courseDocument.addAttribute("courier_surname", this.courier.getSurname());
        return courseDocument;
    }

}
