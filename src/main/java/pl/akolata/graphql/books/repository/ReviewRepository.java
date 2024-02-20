package pl.akolata.graphql.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akolata.graphql.books.persistence.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
}
