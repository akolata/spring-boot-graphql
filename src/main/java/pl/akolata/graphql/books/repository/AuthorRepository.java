package pl.akolata.graphql.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akolata.graphql.books.persistence.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
    List<Author> findAllByBooks_Id(String id);
}
