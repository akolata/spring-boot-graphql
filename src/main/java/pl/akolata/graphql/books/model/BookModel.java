package pl.akolata.graphql.books.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.akolata.graphql.books.persistence.entity.Book;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class BookModel {
    private String id;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String title;
    private List<AuthorModel> authors = new ArrayList<>();
    private List<ReviewModel> reviews = new ArrayList<>();

    public BookModel(Book book) {
        this.id = book.getId();
        this.version = book.getVersion();
        this.createdAt = book.getCreatedAt();
        this.updatedAt = book.getUpdatedAt();
        this.title = book.getTitle();
    }
}
