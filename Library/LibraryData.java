import java.time.LocalDate;

public class LibraryData {
    private LibrarySystem librarySystem;

    public LibraryData(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    public void initData() {

        
        // Create and add some users
        User user1 = new User("Bedynek, Jarrett", "123 Main St", "555-1234", "0001", 28);
        User user2 = new User("Borja, Danny", "456 Oak Ave", "555-5678", "0002", 10);
        User user3 = new User("Edgar, Marshall", "789 Cedar Ave", "555-4861", "0003", 28);

        // Add users to the LibrarySystem
        librarySystem.addUser(user1);
        librarySystem.addUser(user2);
        librarySystem.addUser(user3);

        // Create and add some books
        Book book1 = new Book("To Kill A Mockingbird", true, true);
        Book book2 = new Book("A", false, false);
        Book book3 = new Book("B", false, true);
        Book book4 = new Book("C", false, true);
        Book book5 = new Book("D", false, true);
        Book book6 = new Book("E", false, true);
        Book book7 = new Book("F", false, true);

        // Add books to the LibrarySystem
        librarySystem.addBook(book1);
        librarySystem.addBook(book2);
        librarySystem.addBook(book3);
        librarySystem.addBook(book4);
        librarySystem.addBook(book5);
        librarySystem.addBook(book6);
        librarySystem.addBook(book7);

        // Create and add an overdue book to a user
        Book overdueBook = new Book("Overdue Book", false, false);
        overdueBook.setDueDate(true); // Due date set 14 days in the past
        user2.addCheckedOutBook(overdueBook);

        // Create and add some audio/video materials
        AudioVideoMaterial avMaterial1 = new AudioVideoMaterial("X", false, false);
        AudioVideoMaterial avMaterial2 = new AudioVideoMaterial("Y", false, true);
        AudioVideoMaterial avMaterial3 = new AudioVideoMaterial("Z", false, true);

        // Add audio/video materials to the LibrarySystem
        librarySystem.addAudioVideoMaterial(avMaterial1);
        librarySystem.addAudioVideoMaterial(avMaterial2);
        librarySystem.addAudioVideoMaterial(avMaterial3);
    }
}
