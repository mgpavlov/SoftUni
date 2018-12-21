package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.*;
import bookshopsystemapp.dto.ReducedBook;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.repository.BookRepository;
import bookshopsystemapp.repository.CategoryRepository;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final static String BOOKS_FILE_PATH = "D:\\Downloads\\HW\\SpringDataAdvanced\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] booksFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
        for (String line : booksFileContent) {
            String[] lineParams = line.split("\\s+");

            Book book = new Book();
            book.setAuthor(this.getRandomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(lineParams[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(lineParams[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);

            int copies = Integer.parseInt(lineParams[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(lineParams[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineParams[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();
            for (int i = 5; i < lineParams.length; i++) {
                title.append(lineParams[i]).append(" ");
            }

            book.setTitle(title.toString().trim());

            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        }
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.authorRepository.count() - 1)) + 1;

        return this.authorRepository.findById(randomId).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new LinkedHashSet<>();

        Random random = new Random();
        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();

            categories.add(category);
        }

        return categories;
    }

    private Category getRandomCategory() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

        return this.categoryRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<String> getAllBooksTitlesAfter() {
        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));

        return books.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllAuthorsWithBookBefore() {
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

        return books.stream().map(b -> String.format("%s %s", b.getAuthor().getFirstName(), b.getAuthor().getLastName())).collect(Collectors.toSet());
    }

    @Override
    public List<String> getAllBooksTitlesWithAgeRestriction(String ageRestrictionStr) {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionStr.toUpperCase());

        return this.bookRepository.findAllByAgeRestriction(ageRestriction).stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksTitlesWithGoldenEditionAndLessThan5000Copies() {
        List<Book> books = this.bookRepository.getAllByEditionTypeIsAndCopiesLessThan(EditionType.GOLD, 5000);
        return books.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksWithPriceLowerThan5AndHigherThan40() {
        List<Book> books = this.bookRepository.getAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
        return books.stream().map(b-> String.format("%s - $%.2f", b.getTitle(), b.getPrice())).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksNotReleasedOnYear(String year) {
        LocalDate before = LocalDate.parse(year + "-01-01");
        LocalDate after = LocalDate.parse(year + "-12-31");
        List<Book> books = this.bookRepository.getAllByReleaseDateBeforeOrReleaseDateAfter(before, after);
        List<Book> books1 = this.bookRepository.getBooksByReleaseDateNot_Year(Integer.valueOf(year));

        return books1.stream().map(Book::getTitle).collect(Collectors.toList());
        /*return books.stream().map(Book::getTitle).collect(Collectors.toList());*/
    }

    @Override
    public List<String> getAllBooksReleasedBeforeDate(LocalDate date) {
        List<Book> books = this.bookRepository.getAllByReleaseDateBefore(date);

        return books.stream().map(b-> String.format("Title: %s%nEdition: %s%nPrice: $%.2f",b.getTitle(), b.getEditionType(), b.getPrice())).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksTitlesContainsString(String containsString) {
        List<Book> books = this.bookRepository.getAllByTitleContains(containsString);

        return books.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> getBookTitleAndAuthorForAuthorsLastNameStartsWith(String text) {
        List<Book> books = this.bookRepository.getBooksByAuthorLastNameStartsWith(text);

        return books
                .stream()
                .map(book -> String.format("%s (%s %s)", book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getCountOfBooksWithTitleLongerThan(int length) {
        Integer countOfBooksWithTitleLongerThan = this.bookRepository.getCountOfBooksWithTitleLongerThan(length);

        return countOfBooksWithTitleLongerThan;
    }

    @Override
    public String getBookDetailsByTitle(String title) {
        ReducedBook reducedBook = this.bookRepository.getBookByTitle(title);
        return (reducedBook == null) ? "Book Not Found" : reducedBook.toString();
    }

    @Override
    public Integer increaseCopiesForBooksReleasedAfterDate(LocalDate startDate, int copiesToAdd) {
        return this.bookRepository.increaseCopiesForBooksReleasedAfterDate(startDate, copiesToAdd);
    }

    @Override
    public Integer removeBooksWithCopiesLessThan(int minCopies) {
        return this.bookRepository.removeBooksWithCopiesLessThan(minCopies);
    }

}
