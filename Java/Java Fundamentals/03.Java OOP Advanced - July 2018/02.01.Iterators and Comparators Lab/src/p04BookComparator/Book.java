package p04BookComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book implements Comparable<Book>{
    private String title;
    private int year;
    private List<String> authors;

    public Book(String title, int year, String... authors) {
        this.setTitle(title);
        this.setYear(year);
        this.setAuthors(authors);
    }

    String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    int getYear() {
        return year;
    }

    private void setYear(int year) {
        this.year = year;
    }

    private List<String> getAuthors() {
        return authors;
    }

    private void setAuthors(String... authors) {
        if(authors.length==0){
            this.authors = new ArrayList<>();
        }else{
            this.authors = new ArrayList<>(Arrays.asList(authors));
        }
    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public int compareTo(Book o) {

        if(this.getTitle().compareTo(o.getTitle()) == 0){
            return this.getYear()-o.getYear();
        }
        return this.getTitle().compareTo(o.getTitle());
    }
}