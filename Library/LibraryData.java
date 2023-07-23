import java.util.ArrayList;

public class LibraryData {
    private LibrarySystem library;

    public LibraryData(LibrarySystem librarySystem) {
        library = librarySystem;
    }

    public void initData() {
        // Create ArrayLists for users, books, and audio/video materials
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<AudioVideoMaterial> avMaterials = new ArrayList<>();

        // Create and add some users
        users.add(new User("Bedynek, Jarrett", "123 Main St", "555-1234", "0001", 28));
        users.add(new User("Borja, Danny", "456 Oak Ave", "555-5678", "0002", 10));
        users.add(new User("Edgar, Marshall", "789 Cedar Ave", "555-4861", "0003", 28));

        // Create and add some books
        books.add(new Book("To Kill A Mockingbird", true, true));
        books.add(new Book("The Great Gatsby", false, true));
        books.add(new Book("Of Mice And Men", true, true));

        // Create and add some audio/video materials
        avMaterials.add(new AudioVideoMaterial("E.T. the Extra-Terrestrial", true, false));
        avMaterials.add(new AudioVideoMaterial("Mrs. Doubtfire", false, true)); 
    }
}
