import java.util.*;
import java.time.LocalDate;

public class LibrarySystem {
    private List<User> users;
    private List<Book> availableBooks;
    private List<AudioVideoMaterial> availableAudioVideoMaterials;
    private static final int MAX_ALLOWED_CHECKOUTS = 5;

    public LibrarySystem() {
        this.users = new ArrayList<>();
        this.availableBooks = new ArrayList<>();
        this.availableAudioVideoMaterials = new ArrayList<>();

        // Initialize data using LibraryDataInitializer
        LibraryData dataInitializer = new LibraryData(this);
        dataInitializer.initData();
    }

    // Case 1: Add User
    public void addUser(User user) {
        users.add(user);
    }

    // Case 2: Lookup User
    public User getUserByLibraryCardNumber(String libraryCardNumber) {
        for (User user : users) {
            if (user.getLibraryCardNumber().equals(libraryCardNumber)) {
                return user;
            }
        }
        return null;
    }

    // Case 3: Add Book
    public void addBook(Book book) {
        availableBooks.add(book);
    }

    // Case 4: Lookup Book
    public Book getBookByTitle(String title) {
        for (Book book : availableBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Book not found
    }

    // Case 5: Add Audio/Video Material
    public void addAudioVideoMaterial(AudioVideoMaterial avMaterial) {
        availableAudioVideoMaterials.add(avMaterial);
    }

    // Case 6: Lookup Audio/Video Material
    public AudioVideoMaterial getAVMaterialByTitle(String title) {
        for (AudioVideoMaterial avMaterial : availableAudioVideoMaterials) {
            if (avMaterial.getTitle().equalsIgnoreCase(title)) {
                return avMaterial;
            }
        }
        return null; // Audio/Video Material not found
    }

    public List<Book> getAvailableBooks() {
        return availableBooks;
    }

    public List<AudioVideoMaterial> getAvailableAudioVideoMaterials() {
        return availableAudioVideoMaterials;
    }

    public void handleShowCheckedOutItems(User user) {
        // Show checked-out books
        System.out.println("Books currently checked out:");
        List<Book> checkedOutBooks = user.getCheckedOutBooks();
        if (checkedOutBooks != null) {
            for (Book checkedOutBook : checkedOutBooks) {
                System.out.println(checkedOutBook.getTitle());
            }
        } else {
            System.out.println("No books currently checked out.");
        }

        // Show checked-out audio/video materials
        System.out.println("Audio/Video materials currently checked out:");
        List<AudioVideoMaterial> checkedOutAVMaterials = user.getCheckedOutAVMaterials();
        if (checkedOutAVMaterials != null) {
            for (AudioVideoMaterial avMaterial : checkedOutAVMaterials) {
                System.out.println(avMaterial.getTitle());
            }
        } else {
            System.out.println("No audio/video materials currently checked out.");
        }
    }

    public void handleCheckOutBook(User user, LibrarySystem librarySystem){
        System.out.println("Enter the title of the book you want to check out:");
        Scanner scanner = new Scanner(System.in);
        String bookTitle = scanner.nextLine();

        boolean bookCheckedOut = librarySystem.checkoutItem(user, bookTitle);
        if (bookCheckedOut) {
            Book book = new Book(bookTitle, bookCheckedOut, bookCheckedOut);
            System.out.println("Book \"" + bookTitle + "\" checked out successfully. Due date: " + book.getDueDate());
        } else {
            System.out.println("Book \"" + bookTitle + "\" is not available or you have reached the maximum allowed checkouts.");
        }
    }

    public void handleCheckoutAVMaterial(User user, LibrarySystem librarySystem){
        System.out.println("Enter the title of the AV Material you want to check out:");
        Scanner scanner = new Scanner(System.in);
        String AVTitle = scanner.nextLine();

        boolean AVItemCheckedOut = librarySystem.checkoutItem(user, AVTitle);
        if (AVItemCheckedOut) {
            AudioVideoMaterial avmaterial = new AudioVideoMaterial(AVTitle, AVItemCheckedOut, AVItemCheckedOut);
            System.out.println("AV Material \"" + AVTitle + "\" checked out successfully. Due Date: " + avmaterial.getDueDate());
        } else {
            System.out.println("AV Material \"" + AVTitle + "\" is not available or you have reached the maximum allowed checkouts.");
        }
    }

    public boolean checkoutItem(User user, String itemTitle) {
        // Find the item (book or AV material) with the given title
        LibraryItem itemToCheckout = null;
    
        // Check available books
        for (Book book : availableBooks) {
            if (book.getTitle().equalsIgnoreCase(itemTitle)) {
                itemToCheckout = book;
                break;
            }
        }
    
        // Check available audio/video materials if item is not found in books
        if (itemToCheckout == null) {
            for (AudioVideoMaterial avMaterial : availableAudioVideoMaterials) {
                if (avMaterial.getTitle().equalsIgnoreCase(itemTitle)) {
                    itemToCheckout = avMaterial;
                    break;
                }
            }
        }
    
        if (itemToCheckout == null || !itemToCheckout.isAvailable()) {
            return false; // Item not found or already checked out
        }
    
        // Check if the user has reached the maximum allowed checkouts
        if (user.getCheckedOutBooks().size() >= MAX_ALLOWED_CHECKOUTS) {
            return false; // User has reached the maximum allowed checkouts
        }
    
        // Checkout the item to the user and set the due date
        //itemToCheckout.handleCheckOutBook();
    
        // Set the due date based on the type of item
        int checkoutPeriod = itemToCheckout.getCheckoutPeriod();
        LocalDate dueDate = LocalDate.now().plusDays(checkoutPeriod);
        itemToCheckout.setDueDate(dueDate);
    
        return true;
    }
    


    public boolean returnItem(User user, String itemTitle) {
        List<Book> availableBooks = getAvailableBooks();
        List<Book> checkedOutBooks = user.getCheckedOutBooks();
        List<AudioVideoMaterial> availableAVMaterials = getAvailableAudioVideoMaterials();
        List<AudioVideoMaterial> checkedOutAVMaterials = user.getCheckedOutAVMaterials();

        // Check if the item is a book
        for (Book book : checkedOutBooks) {
            if (book.getTitle().equalsIgnoreCase(itemTitle)) {
                user.removeCheckedOutBook(book);
                availableBooks.add(book);
                return true;
            }
        }

        // If the item is not a book, check if it is an AV material
        for (AudioVideoMaterial avMaterial : checkedOutAVMaterials) {
            if (avMaterial.getTitle().equalsIgnoreCase(itemTitle)) {
                user.removeCheckedOutAVMaterial(avMaterial);
                availableAVMaterials.add(avMaterial);
                return true;
            }
        }

        // Item not found in user's checked-out items
        return false;
    }

    public boolean renewItem(User user, String itemTitle) {
        List<Book> checkedOutBooks = user.getCheckedOutBooks();
        List<AudioVideoMaterial> checkedOutAVMaterials = user.getCheckedOutAVMaterials();

        // Check if the item is a book
        for (Book book : checkedOutBooks) {
            if (book.getTitle().equalsIgnoreCase(itemTitle)) {
                if (book.canRenew()) {
                    // If the item is requested by another user, it cannot be renewed
                    if (isItemRequested(book)) {
                        System.out.println("Cannot renew the book. There is an outstanding request for this item.");
                        return false;
                    } else {
                        // Renew the book
                        LocalDate newDueDate = book.getDueDate().plusDays(book.getCheckoutPeriod());
                        book.setDueDate(newDueDate);
                        System.out.println(book.getTitle() + " renewed successfully for " + user.getName());
                        return true;
                    }
                } else {
                    System.out.println("Cannot renew the book. Maximum renewal limit reached.");
                    return false;
                }
            }
        }

        // If the item is not a book, check if it is an AV material
        for (AudioVideoMaterial avMaterial : checkedOutAVMaterials) {
            if (avMaterial.getTitle().equalsIgnoreCase(itemTitle)) {
                if (avMaterial.canRenew()) {
                    // If the item is requested by another user, it cannot be renewed
                    if (isItemRequested(avMaterial)) {
                        System.out.println("Cannot renew the AV material. There is an outstanding request for this item.");
                        return false;
                    } else {
                        // Renew the AV material
                        LocalDate newDueDate = avMaterial.getDueDate().plusDays(avMaterial.getCheckoutPeriod());
                        avMaterial.setDueDate(newDueDate);
                        System.out.println(avMaterial.getTitle() + " renewed successfully for " + user.getName());
                        return true;
                    }
                } else {
                    System.out.println("Cannot renew the AV material. Maximum renewal limit reached.");
                    return false;
                }
            }
        }

        // Item not found
        System.out.println("Item with the title \"" + itemTitle + "\" is not checked out by " + user.getName());
        return false;
    }

    private boolean isItemRequested(LibraryItem item) {
        return false;
    }

    public void requestItem(User user, LibraryItem item) {
        if (!item.isReferenceOnly()) {
            // Implement the logic to handle item requests here
            System.out.println("Item request for " + item.getTitle() + " submitted by " + user.getName());
        } else {
            System.out.println("Reference items cannot be requested.");
        }
    }
}
