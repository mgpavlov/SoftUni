package _03_Inheritance.EXERCISES._02_Book_Shop;

public class GoldenEditionBook extends Book {
    public GoldenEditionBook(String author, String title, Double price) throws Exception {
        super(author, title, price);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + super.getPrice() * 0.3;
    }
}
