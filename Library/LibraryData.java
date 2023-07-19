public class LibraryData {
    private LibrarySystem library;

    public LibraryData(LibrarySystem librarySystem) {
        library = librarySystem;
    }

    public void initData() {
        // Create and add some users
        User user1 = new User("Bedynek, Jarrett", "123 Main St", "555-1234", "0001");
        User user2 = new User("Borja, Danny", "456 Oak Ave", "555-5678", "0002");
        User user3 = new User("Edgar, Marshall", "789 Cedar Ave", "555-4861", "0003");
        library.addUser(user1);
        library.addUser(user2);
        library.addUser(user3);

        // Create and add some books
        Book book1 = new Book("To Kill A Mockingbird", true, true);
        Book book2 = new Book("The Great Gatsby", false, true);
        Book book3 = new Book("Of Mice And Men", true, true);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Create and add some audio/video materials
        AudioVideoMaterial avMaterial1 = new AudioVideoMaterial("E.T. the Extra-Terrestrial", true, false);
        AudioVideoMaterial avMaterial2 = new AudioVideoMaterial("Mrs. Doubtfire", false, true);
        library.addAudioVideoMaterial(avMaterial1);
        library.addAudioVideoMaterial(avMaterial2);
    }
}
