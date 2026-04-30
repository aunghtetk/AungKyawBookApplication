# AungKyawBookApplication
# Book Application

## Project Description
This Java program is a book management application. It allows the user to add printed books and audio books, display all books, search books, sort books by title, show statistics, display the most recent books, save books to a file, and load books from a file.

The program uses an abstract `Book` class, a `BookInterface`, and two child classes: `PrintedBook` and `AudioBook`. Printed books calculate cost using pages, while audio books calculate cost using duration in minutes.

## How to Run the Program
1. Open the project in IntelliJ IDEA or another Java IDE.
2. Make sure these files are in the same project folder:
   - `BookApplication.java`
   - `Book.java`
   - `PrintedBook.java`
   - `AudioBook.java`
   - `BookInterface.java`
   - `books.txt`
3. Run `BookApplication.java`.
4. Use the menu options:
   - 1: Add Book
   - 2: Display All Books
   - 3: Search Books
   - 4: Sort Books by Title
   - 5: Display Statistics
   - 6: Display Last Books
   - 7: Save to File
   - 8: Load from File
   - 9: Exit

## Design Explanation
This project uses object-oriented programming principles.

### Abstraction
The `Book` class is abstract because it represents a general book, but the program creates specific book types such as printed books and audio books.

### Inheritance
`PrintedBook` and `AudioBook` inherit from the `Book` class. This allows both classes to share common fields such as title, author, genre, and book type.

### Polymorphism
The program stores both printed books and audio books in one `ArrayList<Book>`. When `displayDetails()` or `getCost()` is called, Java automatically uses the correct version from either `PrintedBook` or `AudioBook`.

### Encapsulation
The class fields are private, and the program uses getter methods to access the data safely.

### File Handling
The program saves book data to `books.txt` and loads book data from the same file. This allows the book list to be stored even after the program closes.

video link
https://www.loom.com/share/7dcb6477dc724c58a19f9aad09028921
