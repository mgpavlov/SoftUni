package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.Author;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_FILE_PATH = "D:\\Downloads\\HW\\SpringDataAdvanced\\src\\main\\resources\\files\\authors.txt";

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] authorFileContent = this.fileUtil.getFileContent(AUTHORS_FILE_PATH);
        for (String line : authorFileContent) {
            String[] names = line.split("\\s+");

            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }

    @Override
    public List<String> getAllAuthorsFirstNameEndsWith(String suffix) {
        List<Author> authors = this.authorRepository.getAllByFirstNameEndingWith(suffix);

        return authors.stream().map(author ->{
          return   author.getFirstName() + " " + author.getLastName();
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getAuthorsByBookCopiesCount() {
        List<Object> authorsInfo = this.authorRepository.getAuthorsByBooksCopies();
        return authorsInfo.stream().map(Object::toString).collect(Collectors.toList());
    }

    @Override
    public Integer getAuthorBooksCount(String firstName, String lastName) {
        return null;
    }
}
