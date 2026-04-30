import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public abstract class Book implements BookInterface {
    private String title;
    private String author;
    private String genre;
    private String bookType;

    public Book(String title, String author, String genre, String bookType) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookType = bookType;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getBookType() {
        return bookType;
    }

    public abstract double getCost();

    public abstract void displayDetails();

    @Override
    public double getTotalCost(ArrayList<Book> books) {
        double total = 0;

        for (Book book : books) {
            total += book.getCost();
        }

        return total;
    }

    @Override
    public Map<String, Integer> numberBooksPerGenre(ArrayList<Book> books) {
        Map<String, Integer> genreCount = new HashMap<>();

        for (Book book : books) {
            String genre = book.getGenre();
            genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
        }

        return genreCount;
    }

    public String toFileString() {
        return bookType + "," + title + "," + author + "," + genre + "," + getCost();
    }
}