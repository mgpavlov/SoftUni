package p02Library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Library<Book> implements Iterable<Book> {
    private Book[] books;

    public Library(Book... books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return this.books;
    }


    @Override
    public Iterator<Book> iterator() {
        return new LibIterator();
    }



    private final class LibIterator implements Iterator<Book>{
        private int counter = 0;
        @Override
        public boolean hasNext() {
            return this.counter < books.length;
        }

        @Override
        public Book next() {
            return books[counter++];
        }
    }
}
