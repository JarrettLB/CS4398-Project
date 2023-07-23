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
        Book book2 = new Book("The Great Gatsby", false, true);
        Book book3 = new Book("Of Mice And Men", true, true);

        // Add books to the LibrarySystem
        librarySystem.addBook(book1);
        librarySystem.addBook(book2);
        librarySystem.addBook(book3);

        // Create and add some audio/video materials
        AudioVideoMaterial avMaterial1 = new AudioVideoMaterial("E.T. the Extra-Terrestrial", true, false);
        AudioVideoMaterial avMaterial2 = new AudioVideoMaterial("Mrs. Doubtfire", false, true);

        // Add audio/video materials to the LibrarySystem
        librarySystem.addAudioVideoMaterial(avMaterial1);
        librarySystem.addAudioVideoMaterial(avMaterial2);
    }
}
