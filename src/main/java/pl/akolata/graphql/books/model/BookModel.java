package pl.akolata.graphql.books.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.akolata.graphql.books.persistence.entity.Book;

import java.time.LocalDateTime;

@Data
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class BookModel {
    private String id;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String title;

    public BookModel(Book book) {
        this.id = book.getId();
        this.version = book.getVersion();
        this.createdAt = book.getCreatedAt();
        this.updatedAt = book.getUpdatedAt();
        this.title = book.getTitle();
    }

    public String getPing() {
        log.info("[BookModel]getPong");
        return "pong";
    }

}
