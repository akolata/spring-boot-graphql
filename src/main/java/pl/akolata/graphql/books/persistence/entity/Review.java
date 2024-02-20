package pl.akolata.graphql.books.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Review extends BaseEntity {

    private String review;

    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "book_id")
    private Book book;
}
