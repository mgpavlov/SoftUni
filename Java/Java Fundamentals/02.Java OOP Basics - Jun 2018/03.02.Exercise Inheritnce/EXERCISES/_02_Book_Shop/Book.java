package _03_Inheritance.EXERCISES._02_Book_Shop;

public class Book {
    private String title;
    private String author;
    private double price;

    public Book(String author, String title, double price) {
        this.setAuthor(author);
        this.setTitle(title);
        this.setPrice(price);
    }

    public String getTitle() {
        return this.title;
    }

    private void setTitle(String title) {
        if (title.length() < 3) {
            throw new IllegalArgumentException("Title not valid!");
        }

        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    private void setAuthor(String author) {
        String[] authorArgs = author.split("\\s+");
        if (authorArgs.length == 2) {
            String secondName = authorArgs[1];
            if (Character.isDigit(secondName.charAt(0))) {
                throw new IllegalArgumentException("Author not valid!");
            }
        }

        this.author = author;
    }

    public double getPrice() {
        return this.price;
    }

    private void setPrice(double price) {
        if (price <= 0.0) {
            throw new IllegalArgumentException("Price not valid!");
        }

        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: ").append(this.getClass().getSimpleName())
                .append(System.lineSeparator())
                .append("Title: ").append(this.getTitle())
                .append(System.lineSeparator())
                .append("Author: ").append(this.getAuthor())
                .append(System.lineSeparator())
                .append("Price: ").append(this.getPrice())
                .append(System.lineSeparator());
        return sb.toString();
    }
}
