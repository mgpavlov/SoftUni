package bookshopsystemapp.service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    List<String> getAllBooksTitlesWithAgeRestriction(String ageRestrictionStr);

    List<String> getAllBooksTitlesWithGoldenEditionAndLessThan5000Copies();

    List<String> getAllBooksWithPriceLowerThan5AndHigherThan40();

    List<String> getAllBooksNotReleasedOnYear(String year);

    List<String> getAllBooksReleasedBeforeDate(LocalDate date);

    List<String> getAllBooksTitlesContainsString(String containsString);

    List<String> getBookTitleAndAuthorForAuthorsLastNameStartsWith(String text);

    Integer getCountOfBooksWithTitleLongerThan(int length);

    String getBookDetailsByTitle(String title);

    Integer increaseCopiesForBooksReleasedAfterDate(LocalDate startDate, int copiesToAdd);

    Integer removeBooksWithCopiesLessThan(int minCopies);
}
