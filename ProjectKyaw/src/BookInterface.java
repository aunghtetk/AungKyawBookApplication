import java.util.ArrayList;
import java.util.Map;

public interface BookInterface {
    default void displayAllBooks(ArrayList<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books stored.");
        } else {
            for (Book book : books) {
                book.displayDetails();
                System.out.println("----------------------");
            }
        }
    }

    Map<String, Integer> numberBooksPerGenre(ArrayList<Book> books);

    double getTotalCost(ArrayList<Book> books);
}