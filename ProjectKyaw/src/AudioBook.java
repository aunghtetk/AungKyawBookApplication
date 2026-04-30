import java.util.ArrayList;

public class AudioBook extends Book {
    private int durationMinutes;

    public AudioBook(String title, String author, String genre, int durationMinutes) {
        super(title, author, genre, "AudioBook");
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public double getCost() {
        return durationMinutes * 0.25;
    }

    @Override
    public void displayDetails() {
        System.out.println("Book Type: Audio Book");
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Genre: " + getGenre());
        System.out.println("Duration: " + durationMinutes + " minutes");
        System.out.printf("Cost: $%.2f%n", getCost());
    }

    public static double averageDuration(ArrayList<Book> books) {
        int totalDuration = 0;
        int count = 0;

        for (Book book : books) {
            if (book instanceof AudioBook) {
                AudioBook audio = (AudioBook) book;
                totalDuration += audio.getDurationMinutes();
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return (double) totalDuration / count;
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + durationMinutes;
    }
}