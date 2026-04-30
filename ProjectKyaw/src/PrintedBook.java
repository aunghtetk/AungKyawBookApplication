import java.util.ArrayList;

public class PrintedBook extends Book { //inheritance
    private int pages; //encapsulation

    public PrintedBook(String title, String author, String genre, int pages) {
        super(title, author, genre, "PrintedBook");
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public double getCost() {
        return pages * 0.50;
    }

    @Override
    public void displayDetails() {
        System.out.println("Book Type: Printed Book");
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Genre: " + getGenre());
        System.out.println("Pages: " + pages);
        System.out.printf("Cost: $%.2f%n", getCost());
    }

    public static double averagePages(ArrayList<Book> books) {
        int totalPages = 0;
        int count = 0;

        for (Book book : books) {
            if (book instanceof PrintedBook) {
                PrintedBook printed = (PrintedBook) book;
                totalPages += printed.getPages();
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return (double) totalPages / count;
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + pages;
    }
}
