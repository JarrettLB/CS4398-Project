import java.util.*;
import java.time.LocalDate;

public class LibrarySystem {
    private Map<String, User> users;
    private List<Book> availableBooks;
    private List<AudioVideoMaterial> availableAudioVideoMaterials;

    public LibrarySystem() {
        this.users = new HashMap<>();
        this.availableBooks = new ArrayList<>();
        this.availableAudioVideoMaterials = new ArrayList<>();

        // Initialize data using LibraryDataInitializer
        LibraryData dataInitializer = new LibraryData(this);
        dataInitializer.initData();
    }

    // Case 1: Add User
    public void addUser(User user) {
        users.put(user.getLibraryCardNumber(), user);
    }

    // Case 2: Lookup User
    public User getUserByLibraryCardNumber(String libraryCardNumber) {
        return users.get(libraryCardNumber);
    }

    // Case 3: Add Book
    public void addBook(String title, boolean isReferenceOnly, boolean isBestSeller) {
        Book book = new Book(title, isReferenceOnly, isBestSeller);
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

    public void checkOutItem(User user, LibraryItem item) {
        if (!user.getCheckedOutItems().contains(item)) {
            if (item instanceof Book && user.getCheckedOutItems().stream().filter(i -> i instanceof Book).count() >= 5) {
                System.out.println("Children can only check out five items at a time.");
                return;
            }
            user.checkOutItem(item);
            System.out.println(item.getTitle() + " checked out successfully by " + user.getName());
        } else {
            System.out.println("The user has already checked out this item.");
        }
    }

    public void returnItem(User user, LibraryItem item) {
        if (user.getCheckedOutItems().contains(item)) {
            user.returnItem(item);
            System.out.println(item.getTitle() + " returned successfully by " + user.getName());
        } else {
            System.out.println("The user hasn't checked out this item.");
        }
    }

    public void renewItem(User user, LibraryItem item) {
        user.renewItem(item);
    }

    public void requestItem(User user, LibraryItem item) {
        if (!item.isReferenceOnly()) {
            // Implement the logic to handle item requests here
            System.out.println("Item request for " + item.getTitle() + " submitted by " + user.getName());
        } else {
            System.out.println("Reference items cannot be requested.");
        }
    }

    // Method to track due dates and calculate fines
    public void trackDueDatesAndFines() {
        LocalDate today = LocalDate.now();

        for (User user : users.values()) {
            for (LibraryItem item : user.getCheckedOutItems()) {
                if (item.getDueDate() != null && item.getDueDate().isBefore(today)) {
                    long daysOverdue = item.getDueDate().until(today).getDays();
                    double overdueFine = Math.min(item.getFinePerDay() * daysOverdue, item.getMaxFine());
                    System.out.println(user.getName() + " has an overdue item: " + item.getTitle() +
                            " (Overdue for " + daysOverdue + " days). Fine: $" + overdueFine);
                }
            }
        }
    }

    // New methods for core functionality

    public User getUserByName(String userName) {
        for (User user : users.values()) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null; // User not found
    }

    public LibraryItem getItemByTitle(String itemTitle) {
        for (LibraryItem item : availableBooks) {
            if (item.getTitle().equals(itemTitle)) {
                return item;
            }
        }
        for (LibraryItem item : availableAudioVideoMaterials) {
            if (item.getTitle().equals(itemTitle)) {
                return item;
            }
        }
        return null; // Item not found
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public List<LibraryItem> getItems() {
        List<LibraryItem> allItems = new ArrayList<>();
        allItems.addAll(availableBooks);
        allItems.addAll(availableAudioVideoMaterials);
        return allItems;
    }

    
}
