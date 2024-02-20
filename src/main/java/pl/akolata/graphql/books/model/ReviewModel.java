package pl.akolata.graphql.books.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.akolata.graphql.books.persistence.entity.Review;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ReviewModel {
    private String id;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String review;

    public ReviewModel(Review review) {
        this.id = review.getId();
        this.version = review.getVersion();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
        this.review = review.getReview();
    }
}
