package pl.akolata.graphql.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akolata.graphql.books.persistence.entity.Book;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleLikeIgnoreCase(String title);

    @Query("FROM Book book LEFT JOIN FETCH book.reviews WHERE book.id IN :booksIds")
    List<Book> findByIdsFetchReviews(Collection<String> booksIds);
}
