package bookshopsystemapp.controller;

import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class BookshopController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... strings) throws Exception {
        /*this.authorService.seedAuthors();
        this.categoryService.seedCategories();
        this.bookService.seedBooks();*/

        this.bookTitlesByAgeRestriction();//p01
//        this.bookTitlesWithGoldenEditionAndLessThan5000Copies();//p02
//        this.booksByPriceLowerThan5AndHigherThan40();//p03
//        this.booksNotReleasedOnYear();//p04
//        this.booksReleasedBeforeDate();//p05
//        this.authorsFirstNameEndsWith();//p06
//        this.booksSearch();//p07
//        this.bookTitlesSearch();//p08
//        this.countBooks();//p09
//        this.totalBookCopies();//p10
//        this.reducedBook();//p11
//        this.increaseBookCopies();//p12
//        this.removeBooks();//p13
    }

    //p01
    private void bookTitlesByAgeRestriction() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String ageRestriction = reader.readLine();
        this.bookService.getAllBooksTitlesWithAgeRestriction(ageRestriction).forEach(System.out::println);
    }

    //p02
    private void bookTitlesWithGoldenEditionAndLessThan5000Copies() {
        this.bookService.getAllBooksTitlesWithGoldenEditionAndLessThan5000Copies().forEach(System.out::println);
    }

    //p03
    private void booksByPriceLowerThan5AndHigherThan40() {
        this.bookService.getAllBooksWithPriceLowerThan5AndHigherThan40().forEach(System.out::println);
    }

    //p04
    private void booksNotReleasedOnYear() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String year = reader.readLine();
        this.bookService.getAllBooksNotReleasedOnYear(year).forEach(System.out::println);
    }

    //p05
    private void booksReleasedBeforeDate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /*String[] input = reader.readLine().split("-");
        String year = input[2];
        String month = input[1];
        String day = input[0];
        LocalDate date = LocalDate.parse(year+"-"+month+"-"+day);*/
        LocalDate date = LocalDate.parse(reader.readLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.bookService.getAllBooksReleasedBeforeDate(date).forEach(System.out::println);
    }

    //p06
    private void authorsFirstNameEndsWith() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String suffix = reader.readLine();
        this.authorService.getAllAuthorsFirstNameEndsWith(suffix).forEach(System.out::println);
    }

    //p07
    private void booksSearch() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String containsString = reader.readLine();

        this.bookService.getAllBooksTitlesContainsString(containsString).forEach(System.out::println);
    }

    //p08
    private void bookTitlesSearch() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        this.bookService.getBookTitleAndAuthorForAuthorsLastNameStartsWith(text).forEach(System.out::println);
    }

    //p09
    private void countBooks() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(reader.readLine());
        Integer result = this.bookService.getCountOfBooksWithTitleLongerThan(length);
        System.out.println(result);
    }

    //p10
    private void totalBookCopies() throws IOException {
        this.authorService.getAuthorsByBookCopiesCount().forEach(System.out::println);
    }

    //p11
    private void reducedBook() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String title = reader.readLine().trim();
        String result = this.bookService.getBookDetailsByTitle(title);
        System.out.println(result);
    }

    //p12
    private void increaseBookCopies() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LocalDate startDate = LocalDate.parse(reader.readLine().trim(), DateTimeFormatter.ofPattern("dd MMM yyyy"));
        int copiesToAdd = Integer.parseInt(reader.readLine());
        Integer modifiedBooks = this.bookService.increaseCopiesForBooksReleasedAfterDate(startDate, copiesToAdd);
        System.out.println(modifiedBooks * copiesToAdd);
    }

    //p13
    private void removeBooks() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int minCopies = Integer.parseInt(reader.readLine());
        Integer deletedTitles = this.bookService.removeBooksWithCopiesLessThan(minCopies);
        System.out.printf("%d books were removed%n", deletedTitles);
    }
}
