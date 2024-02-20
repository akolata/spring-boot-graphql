package pl.akolata.graphql.books.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.akolata.graphql.books.persistence.entity.Author;
import pl.akolata.graphql.books.repository.AuthorRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> findAuthorsByBookId(String bookId) {
        log.info("[AuthorService][findAuthorsByBookId(bookId:{})]", bookId);
        List<Author> authors = authorRepository.findAllByBooks_Id(bookId);
        log.info("[AuthorService][findAuthorsByBookId(bookId:{})] found {} authors", bookId, authors.size());
        return authors;
    }

}
