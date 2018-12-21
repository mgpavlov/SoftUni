package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;
    List<String> getAllAuthorsFirstNameEndsWith(String suffix);

    List<String> getAuthorsByBookCopiesCount();

    Integer getAuthorBooksCount(String firstName, String lastName);
}
