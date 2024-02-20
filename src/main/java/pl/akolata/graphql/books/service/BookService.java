package pl.akolata.graphql.books.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.akolata.graphql.books.model.CreateBookInput;
import pl.akolata.graphql.books.persistence.entity.Author;
import pl.akolata.graphql.books.persistence.entity.Book;
import pl.akolata.graphql.books.repository.AuthorRepository;
import pl.akolata.graphql.books.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> findAll() {
        log.info("[BookService][findAll]");
        List<Book> books = bookRepository.findAll();
        log.info("[BookService][findAll] found {} books", books.size());
        return books;
    }

    public Optional<Book> findById(String id) {
        log.info("[BookService][findById(id:{})]", id);
        Optional<Book> book = bookRepository.findById(id);
        log.info("[BookService][findById(id:{})] found {}", id, book.isPresent());
        return book;
    }

    public List<Book> findByTitle(String title) {
        log.info("[BookService][findByTitle(title:{})]", title);
        List<Book> books = bookRepository.findByTitleLikeIgnoreCase("%" + title + "%");
        log.info("[BookService][findByTitle(title:{})] found {} books", title, books.size());
        return books;
    }

    @Transactional
    public Book createBook(CreateBookInput input) {
        log.info("[BookService][createBook]");
        List<Author> authors = input.getAuthorsIds().stream()
            .map(id -> authorRepository.findById(id).orElseThrow())
            .toList();

        Book book = new Book();
        book.setTitle(input.getTitle());

        for (Author author : authors) {
            book.addAuthor(author);
        }

        return bookRepository.saveAndFlush(book);
    }

}
