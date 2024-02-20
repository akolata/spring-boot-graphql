package pl.akolata.graphql.books.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.akolata.graphql.books.persistence.entity.Book;
import pl.akolata.graphql.books.persistence.entity.Review;
import pl.akolata.graphql.books.repository.BookRepository;
import pl.akolata.graphql.books.repository.ReviewRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    public List<Review> findAllReviews() {
        log.info("[BookService][findAllReviews]");
        List<Review> reviews = reviewRepository.findAll();
        log.info("[BookService][findAllReviews] found {} reviews", reviews.size());
        return reviews;
    }

    public Map<Book, List<Review>> findReviewsByBooksId(Collection<String> booksIds) {
        log.info("[ReviewService][findReviewsByBooksId(booksIds)]");
        List<Book> books = bookRepository.findByIdsFetchReviews(booksIds);
        Map<Book, List<Review>> reviewsByBook = books
            .stream()
            .collect(Collectors.toMap(Function.identity(), book -> book.getReviews().stream().toList()));
        log.info("[ReviewService][findReviewsByBooksId(booksIds)] found reviews for {} books", reviewsByBook.size());
        return reviewsByBook;
    }
}
