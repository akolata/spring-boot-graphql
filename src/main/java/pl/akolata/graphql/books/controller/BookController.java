package pl.akolata.graphql.books.controller;

import graphql.GraphQLError;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Controller;
import pl.akolata.graphql.books.model.AuthorModel;
import pl.akolata.graphql.books.model.BookModel;
import pl.akolata.graphql.books.model.CreateBookInput;
import pl.akolata.graphql.books.model.ReviewModel;
import pl.akolata.graphql.books.persistence.entity.Book;
import pl.akolata.graphql.books.persistence.entity.Review;
import pl.akolata.graphql.books.service.AuthorService;
import pl.akolata.graphql.books.service.BookService;
import pl.akolata.graphql.books.service.ReviewService;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final ReviewService reviewService;

    @SchemaMapping(typeName = "Query", value = "findAllBooks")
    public List<BookModel> findAllBooks() {
        log.info("[BookController][findAllBooks]");
        List<BookModel> books = bookService.findAll().stream().map(BookModel::new).collect(Collectors.toList());
        log.info("[BookController][findAllBooks] found {} books", books.size());
        return books;
    }

    /**
     * Returns JPA entities of all reviews in the database, just to see if GraphQL mapping for DateTime scalar works
     *
     * @return JPA entities of all reviews
     */
    @QueryMapping
    public List<Review> findAllReviewsReturnEntity() {
        log.info("[BookController][findAllReviewsReturnEntity]");
        List<Review> reviews = reviewService.findAllReviews();
        log.info("[BookController][findAllReviewsReturnEntity] returning {} reviews", reviews.size());
        return reviews;
    }

    @QueryMapping
    public Optional<BookModel> findBookById(@Argument String id) {
        log.info("[BookController][findBookById({})]", id);
        Optional<BookModel> book = bookService.findById(id).map(BookModel::new);
        log.info("[BookController][findBookById({})] found {}", id, book.isPresent());
        return book;
    }

    @QueryMapping
    public List<BookModel> findBookByTitle(@Argument String title) {
        log.info("[BookController][findBookByTitle({})]", title);
        List<BookModel> books = bookService.findByTitle(title).stream().map(BookModel::new).toList();
        log.info("[BookController][findBookByTitle({})] returning {} books", title, books.size());
        return books;
    }

    @QueryMapping
    public String throwAnException() {
        throw new CustomException("Let's see how @GraphQlExceptionHandler works");
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(CustomException ex) {
        log.info("[BookController][handle CustomException]", ex);
        return GraphQLError.newError().errorType(ErrorType.INTERNAL_ERROR).message(ex.getMessage()).build();
    }

    @MutationMapping
    public BookModel createBook(@Argument @Valid CreateBookInput input) {
        log.info("[BookController][createBook({})]", input);
        Book book = bookService.createBook(input);
        log.info("[BookController][createBook({})] created with id {}", input, book.getId());
        return new BookModel(book);
    }

    @SchemaMapping
    public List<AuthorModel> authors(BookModel bookModel) {
        log.info("[BookController][BookModel#authors data fetcher] for BookModel(id:{}, title:{})", bookModel.getId(),
            bookModel.getTitle());
        List<AuthorModel> authorsModels = findAuthorsByBookId(bookModel);
        log.info("[BookController][BookModel#authors data fetcher] found {} authors", authorsModels.size());
        // there is NO BookModel#setAuthors call
        return authorsModels;
    }

    @BatchMapping
    public Map<BookModel, List<ReviewModel>> reviews(List<BookModel> bookModels) {
        log.info("[BookController][BookModel#reviews batch data fetcher] for List<BookModel>");
        Map<BookModel, List<ReviewModel>> reviewsByBooks = getBatchReviewsByBooks(bookModels);
        log.info("[BookController][BookModel#reviews batch data fetcher] loaded reviews for {} books", bookModels.size());
        return reviewsByBooks;
    }

    private List<AuthorModel> findAuthorsByBookId(BookModel bookModel) {
        return authorService.findAuthorsByBookId(bookModel.getId())
            .stream()
            .map(AuthorModel::new)
            .toList();
    }

    private Map<BookModel, List<ReviewModel>> getBatchReviewsByBooks(List<BookModel> bookModels) {
        Set<String> booksIds = bookModels.stream().map(BookModel::getId).collect(Collectors.toSet());
        Map<Book, List<Review>> reviewsByBooks = reviewService.findReviewsByBooksId(booksIds);
        Map<BookModel, List<ReviewModel>> reviewsModelsByBookModels = new HashMap<>(reviewsByBooks.size());
        for (Map.Entry<Book, List<Review>> entry : reviewsByBooks.entrySet()) {
            BookModel bookModel = new BookModel(entry.getKey());
            List<ReviewModel> reviewModels = entry.getValue().stream().map(ReviewModel::new).toList();
            reviewsModelsByBookModels.put(bookModel, reviewModels);
        }
        return reviewsModelsByBookModels;
    }

    public static class CustomException extends RuntimeException {
        public CustomException(String message) {
            super(message);
        }
    }
}
