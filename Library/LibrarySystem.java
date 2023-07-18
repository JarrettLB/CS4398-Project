import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LibrarySystem {
    private Map<String, User> users;
    private List<Book> availableBooks;
    private List<AudioVideoMaterial> availableAudioVideoMaterials;

    public LibrarySystem() {
        this.users = new HashMap<>();
        this.availableBooks = new ArrayList<>();
        this.availableAudioVideoMaterials = new ArrayList<>();
    }

    public void addUser(User user) {
        users.put(user.getLibraryCardNumber(), user);
    }

    public void addBook(Book book) {
        availableBooks.add(book);
    }

    public void addAudioVideoMaterial(AudioVideoMaterial avMaterial) {
        availableAudioVideoMaterials.add(avMaterial);
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
}