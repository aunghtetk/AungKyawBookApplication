import java.util.*;
import java.io.*;

public class BookApplication {
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        do {
            System.out.println("\n===== Book Application =====");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Books");
            System.out.println("4. Sort Books by Title");
            System.out.println("5. Display Statistics");
            System.out.println("6. Display Last Books");
            System.out.println("7. Save to File");
            System.out.println("8. Load from File");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
        try {
            choice = scnr.nextInt();
            scnr.nextLine();

            switch (choice) {
                case 1:
                    addBooks();
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    sortBooks();
                    break;
                case 5:
                    displayStatistics();
                    break;
                case 6:
                    displayLastBooks();
                    break;
                case 7:
                    saveToFile();
                    break;
                case 8:
                    loadFromFile();
                    break;
                case 9:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }  catch ( Exception e){
            System.out.println("Please put number");
            scnr.nextLine();
            }

        } while (choice != 9);
    }
    private static void addBooks() {
        System.out.println("Which type of books do you want to add? Type A/a for audio book and P/p for printed books");
        System.out.print("Enter your choice : ");
        String input = scnr.nextLine();

        if(input.equalsIgnoreCase("p")){
            System.out.print("Enter printed book title: ");
            String title = scnr.nextLine();

            System.out.print("Enter printed book author: ");
            String author = scnr.nextLine();

            System.out.print("Enter printed book genre: ");
            String genre = scnr.nextLine();

            System.out.print("Enter number of pages: ");
            int pages = scnr.nextInt();
            scnr.nextLine();

            books.add(new PrintedBook(title, author, genre, pages));
            System.out.println("Printed book added.");

        } else if (input.equalsIgnoreCase("a")) {
            System.out.print("Enter audio book title: ");
            String title = scnr.nextLine();

            System.out.print("Enter audio book author: ");
            String author = scnr.nextLine();

            System.out.print("Enter audio book genre: ");
            String genre = scnr.nextLine();

            System.out.print("Enter duration in minutes: ");
            int duration = scnr.nextInt();
            scnr.nextLine();

            books.add(new AudioBook(title, author, genre, duration));
            System.out.println("Audio book added.");
        }else{
            System.out.println("please put the right character");
            return;
        }
    }

    private static void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books) {
            book.displayDetails();
            System.out.println("----------------------");
        }
    }

    private static void searchBooks() {
        System.out.println("Search by:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Genre");
        System.out.println("4. Book Type");
        System.out.print("Enter choice: ");

        int choice = scnr.nextInt();
        scnr.nextLine();

        System.out.print("Enter search keyword: ");
        String keyword = scnr.nextLine().toLowerCase();

        boolean found = false;

        for (Book book : books) {
            boolean match = false;

            if (choice == 1 && book.getTitle().toLowerCase().contains(keyword)) {
                match = true;
            } else if (choice == 2 && book.getAuthor().toLowerCase().contains(keyword)) {
                match = true;
            } else if (choice == 3 && book.getGenre().toLowerCase().contains(keyword)) {
                match = true;
            } else if (choice == 4 && book.getBookType().toLowerCase().contains(keyword)) {
                match = true;
            }

            if (match) {
                book.displayDetails();
                System.out.println("----------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    private static void sortBooks() {
        books.sort(Comparator.comparing(Book::getTitle));
        System.out.println("Books sorted by title.");
    }

    private static void displayStatistics() {
        double totalCost = 0;

        for (Book book : books) {
            totalCost += book.getCost();
        }

        System.out.println("Total Books: " + books.size());
        System.out.printf("Total Cost: $%.2f%n", totalCost);
        System.out.printf("Average Pages: %.2f%n", PrintedBook.averagePages(books));
        System.out.printf("Average Audio Duration: %.2f minutes%n", AudioBook.averageDuration(books));

        Map<String, Integer> genreCount = new HashMap<>();

        for (Book book : books) {
            genreCount.put(book.getGenre(), genreCount.getOrDefault(book.getGenre(), 0) + 1);
        }

        System.out.println("Books Per Genre:");
        for (String genre : genreCount.keySet()) {
            System.out.println(genre + ": " + genreCount.get(genre));
        }
    }

    private static void displayLastBooks() {
        System.out.println("\nLast 6 Overall Books:");
        displayLastOverall(6);

        System.out.println("\nLast 3 Printed Books:");
        displayLastByType("PrintedBook", 3);

        System.out.println("\nLast 3 Audio Books:");
        displayLastByType("AudioBook", 3);
    }

    private static void displayLastOverall(int n) {
        int start = Math.max(0, books.size() - n);

        for (int i = start; i < books.size(); i++) {
            books.get(i).displayDetails();
            System.out.println("----------------------");
        }
    }

    private static void displayLastByType(String type, int n) {
        int count = 0;

        for (int i = books.size() - 1; i >= 0 && count < n; i--) {
            if (books.get(i).getBookType().equals(type)) {
                books.get(i).displayDetails();
                System.out.println("----------------------");
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No books found for this type.");
        }
    }

    private static void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("books.txt"));

            for (Book book : books) {
                writer.println(book.toFileString());
            }

            writer.close();
            System.out.println("Books saved to books.txt.");

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    private static void loadFromFile() {
        books.clear();

        try {
            Scanner fileScanner = new Scanner(new File("books.txt"));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                String type = data[0];
                String title = data[1];
                String author = data[2];
                String genre = data[3];
                double temp = Double.parseDouble(data[4].trim());
                int value = (int) temp;

                if (type.equals("PrintedBook")) {
                    books.add(new PrintedBook(title, author, genre, value));
                } else if (type.equals("AudioBook")) {
                    books.add(new AudioBook(title, author, genre, value));
                }
            }

            fileScanner.close();
            System.out.println("Books loaded from books.txt.");

        } catch (IOException e) {
            System.out.println("Error loading file.");
        }
    }
}