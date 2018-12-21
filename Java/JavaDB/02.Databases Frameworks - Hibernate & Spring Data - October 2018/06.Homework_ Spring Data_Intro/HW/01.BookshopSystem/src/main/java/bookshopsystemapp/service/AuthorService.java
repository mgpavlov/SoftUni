package bookshopsystemapp.service;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    List<String> getAuthorsByBooksCount();
    void seedAuthors() throws IOException;

}
