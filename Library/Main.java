//Jarrett test commit

public class Main {
    public static void main(String[] args) {
        // Sample usage
        LibrarySystem library = new LibrarySystem();

        User user1 = new User("John Doe", "123 Main St.", "555-1234", "LIBCARD001");
        User user2 = new User("Jane Smith", "456 Oak St.", "555-5678", "LIBCARD002");

        Book book1 = new Book("The Catcher in the Rye", false, true);
        Book book2 = new Book("To Kill a Mockingbird", false, false);
        AudioVideoMaterial avMaterial1 = new AudioVideoMaterial("The Godfather", false, false);

        library.addUser(user1);
        library.addUser(user2);
        library.addBook(book1);
        library.addBook(book2);
        library.addAudioVideoMaterial(avMaterial1);

        library.checkOutItem(user1, book1); // John Doe checks out The Catcher in the Rye
        library.checkOutItem(user1, book2); // John Doe checks out To Kill a Mockingbird
        library.checkOutItem(user1, avMaterial1); // John Doe checks out The Godfather
        library.checkOutItem(user2, book1); // Jane Smith checks out The Catcher in the Rye (Bestseller, 2 weeks)

        library.returnItem(user1, book2); // John Doe returns To Kill a Mockingbird

        // Sample usage for renewing items and requesting items
        library.checkOutItem(user1, book1); // John Doe checks out The Catcher in the Rye
        library.checkOutItem(user1, book2); // John Doe checks out To Kill a Mockingbird

        user1.renewItem(book1); // John Doe renews The Catcher in the Rye

        library.requestItem(user2, book1); // Jane Smith requests The Catcher in the Rye
        user1.renewItem(book1); // John Doe tries to renew The Catcher in the Rye again

        library.trackDueDatesAndFines(); // Check and display overdue fines
    }
}