package bookshopsystemapp.repository;

import bookshopsystemapp.domain.entities.AgeRestriction;
import bookshopsystemapp.domain.entities.Book;
import bookshopsystemapp.domain.entities.EditionType;
import bookshopsystemapp.dto.ReducedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByAgeRestriction(final AgeRestriction ageRestriction);

    List<Book> getAllByEditionTypeIsAndCopiesLessThan(final EditionType editionType, final int copies);

    List<Book> getAllByPriceLessThanOrPriceGreaterThan(final BigDecimal lowerThanPrice, final BigDecimal greaterThanPrice);

    @Query("SELECT b FROM bookshopsystemapp.domain.entities.Book AS b WHERE FUNCTION('YEAR', b.releaseDate) <> :year")
    List<Book> getBooksByReleaseDateNot_Year(@Param("year") final int year);

    List<Book> getAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> getAllByReleaseDateBefore(final LocalDate date);

    List<Book> getAllByTitleContains(String containsString);

    @Query("SELECT b FROM bookshopsystemapp.domain.entities.Book AS b WHERE b.author.lastName LIKE :start%")
    List<Book> getBooksByAuthorLastNameStartsWith(@Param("start") final String start);

    @Query("SELECT COUNT(b) FROM bookshopsystemapp.domain.entities.Book AS b WHERE LENGTH(b.title) > :length ")
    Integer getCountOfBooksWithTitleLongerThan(@Param("length") final int length);

    ReducedBook getBookByTitle(String title);

    @Modifying
    @Query("UPDATE bookshopsystemapp.domain.entities.Book AS b SET b.copies = b.copies + :cnt WHERE b.releaseDate > :date")
    Integer increaseCopiesForBooksReleasedAfterDate(@Param("date") LocalDate startDate, @Param("cnt") int copiesToAdd);

    @Modifying
    @Query("DELETE FROM bookshopsystemapp.domain.entities.Book AS b WHERE b.copies < :copies")
    Integer removeBooksWithCopiesLessThan(@Param("copies") int minCopies);
}
