package pl.akolata.graphql.books.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.akolata.graphql.books.persistence.entity.Author;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AuthorModel {
    private String id;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String firstName;
    private String lastName;

    public AuthorModel(Author author) {
        this.id = author.getId();
        this.version = author.getVersion();
        this.createdAt = author.getCreatedAt();
        this.updatedAt = author.getUpdatedAt();
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
    }
}
