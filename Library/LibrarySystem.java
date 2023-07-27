import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LibrarySystem {
    private List<User> users;
    private List<Book> availableBooks;
    private List<AudioVideoMaterial> availableAudioVideoMaterials;
    private static final int MAX_ALLOWED_CHECKOUTS = 5;
    private int nextLibraryCardNumber;

    public LibrarySystem() {
        this.users = new ArrayList<>();
        this.availableBooks = new ArrayList<>();
        this.availableAudioVideoMaterials = new ArrayList<>();
        this.nextLibraryCardNumber = 1;
    }

    // Case 1: Add User with the next available library card number
    public void addUser(User user) {
        String libraryCardNumber = String.format("%04d", nextLibraryCardNumber);
        user.setLibraryCardNumber(libraryCardNumber);
        users.add(user);
        nextLibraryCardNumber++; // Increment the next library card number for the next user
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
            if (book.getTitle().equals(title)) {
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

    // Returns next available library card number
    public int getNextLibraryCardNumber() {
        int nextLibraryCardNumber = users.size() + 1;
        return nextLibraryCardNumber;
    }

    // Returns list of available books
    public List<Book> getAvailableBooks() {
        return availableBooks;
    }

    // Returns list of available AV Material
    public List<AudioVideoMaterial> getAvailableAudioVideoMaterials() {
        return availableAudioVideoMaterials;
    }

    // Shows lists of all checked out items for a user
    public void handleShowCheckedOutItems(User user) {
        // Show checked-out books
        System.out.println("Books currently checked out:");
        List<Book> checkedOutBooks = user.getCheckedOutBooks();
        if (checkedOutBooks != null && !checkedOutBooks.isEmpty()) {
            for (Book checkedOutBook : checkedOutBooks) {
                System.out.println(checkedOutBook.getTitle() + " Due:(" + checkedOutBook.getDueDate() + ")");
            }
        } else {
            System.out.println(" ");
        }

        // Show checked-out audio/video materials
        System.out.println("Audio/Video materials currently checked out:");
        List<AudioVideoMaterial> checkedOutAVMaterials = user.getCheckedOutAVMaterials();
        if (checkedOutAVMaterials != null && !checkedOutAVMaterials.isEmpty()) {
            for (AudioVideoMaterial avMaterial : checkedOutAVMaterials) {
                System.out.println(avMaterial.getTitle() + " Due:(" + avMaterial.getDueDate() + ")");
            }
        } else {
            System.out.println(" ");
        }
    }

    // Handler for checking out books
    public void handleCheckOutBook(User user, LibrarySystem librarySystem) {
        System.out.print("Enter the title of the book you want to check out:");
        Scanner scanner = new Scanner(System.in);
        String bookTitle = scanner.nextLine();
    
        Book bookToCheckout = librarySystem.getBookByTitle(bookTitle);
        if (bookToCheckout != null) {
            boolean bookCheckedOut = librarySystem.checkoutItem(user, bookTitle);
            if (bookCheckedOut) {
                System.out.println("Book \"" + bookTitle + "\" checked out successfully. Due date: " + bookToCheckout.getDueDate());
            } else {
                System.out.println("Book \"" + bookTitle + "\" is not available or you have reached the maximum allowed checkouts.");
            }
        } else {
            System.out.println("Book \"" + bookTitle + "\" not found in the library.");
        }
    }
    
    // Handler for checking out AV Material
    public void handleCheckoutAVMaterial(User user, LibrarySystem librarySystem) {
        System.out.print("Enter the title of the AV Material you want to check out:");
        Scanner scanner = new Scanner(System.in);
        String AVTitle = scanner.nextLine();
    
        AudioVideoMaterial avMaterialToCheckout = librarySystem.getAVMaterialByTitle(AVTitle);
        if (avMaterialToCheckout != null) {
            boolean AVItemCheckedOut = librarySystem.checkoutItem(user, AVTitle);
            if (AVItemCheckedOut) {
                System.out.println("AV Material \"" + AVTitle + "\" checked out successfully. Due Date: " + avMaterialToCheckout.getDueDate());
            } else {
                System.out.println("AV Material \"" + AVTitle + "\" is not available or you have reached the maximum allowed checkouts.");
            }
        } else {
            System.out.println("AV Material \"" + AVTitle + "\" not found in the library.");
        }
    }
    
    // Checks out an item based on available material - verifies age, current amount of material checked out and bestSeller status
    public boolean checkoutItem(User user, String itemTitle) {
        LibraryItem itemToCheckout = null;
    
        // Check available books
        for (Book book : availableBooks) {
            if (book.getTitle().equals(itemTitle) && book.isAvailable()) {
                itemToCheckout = book;
                break;
            }
        }
    
        // Check available audio/video materials if the item is not found in books
        if (itemToCheckout == null) {
            for (AudioVideoMaterial avMaterial : availableAudioVideoMaterials) {
                if (avMaterial.getTitle().equals(itemTitle) && avMaterial.isAvailable()) {
                    itemToCheckout = avMaterial;
                    break;
                }
            }
        }
    
        if (itemToCheckout == null) {
            System.out.println("Item with the title \"" + itemTitle + "\" not found.");
            return false; // Item not found
        }

        // Check if the item is reference-only, and deny checkout if it is
        if (itemToCheckout.isReferenceOnly()) {
            System.out.println("Cannot check out \"" + itemTitle + "\" as it is a reference-only item.");
            return false;
        }
    
        // Check if the user has reached the maximum allowed checkouts
        if (user.getAge() <= 12 && user.getCheckedOutBooks().size() + user.getCheckedOutAVMaterials().size() >= MAX_ALLOWED_CHECKOUTS) {
            System.out.println("You have already checked out the maximum amount of items.");
            return false;
        }
    
        // Checkout the item to the user and set the due date
        if (itemToCheckout instanceof Book) {
            boolean isBestSeller = ((Book) itemToCheckout).isBestSeller();
            itemToCheckout.setDueDate(isBestSeller);
        }

        itemToCheckout.setCheckedOut(true);
    
        // Add the checked-out item to the user's list of checked-out items
        if (itemToCheckout instanceof Book) {
            user.addCheckedOutBook((Book) itemToCheckout);
        } else if (itemToCheckout instanceof AudioVideoMaterial) {
            user.addCheckedOutAVMaterial((AudioVideoMaterial) itemToCheckout);
        }
        return true;
    }

    public boolean returnItem(User user, Scanner scanner) {
        List<Book> checkedOutBooks = user.getCheckedOutBooks();
        List<AudioVideoMaterial> checkedOutAVMaterials = user.getCheckedOutAVMaterials();
    
        // Show items currently checked out to the user
        handleShowCheckedOutItems(user);
    
        System.out.print("Enter the title of the item to return: ");
        String returnTitle = scanner.nextLine();
    
        // Check if the item is a book
        for (Book book : checkedOutBooks) {
            if (book.getTitle().equals(returnTitle)){
                book.returnBook();
                user.removeCheckedOutBook(book);
                return true;
            }
        }
    
        // Check if the item is an AV material
        for (AudioVideoMaterial avMaterial : checkedOutAVMaterials) {
            if (avMaterial.getTitle().equals(returnTitle)) {
                avMaterial.returnAVMaterial();
                user.removeCheckedOutAVMaterial(avMaterial);
                return true;
            }
        }
    
        // Item not found in user's checked-out items
        System.out.println("Item with the title \"" + returnTitle + "\" is not checked out by " + user.getName());
        return false;
    }
    
    
    // Renew an item once after checking it is not requested
    public boolean renewItem(User user, Scanner scanner) {
        List<Book> checkedOutBooks = user.getCheckedOutBooks();
        List<AudioVideoMaterial> checkedOutAVMaterials = user.getCheckedOutAVMaterials();
    
        handleShowCheckedOutItems(user);
    
        System.out.print("Enter the title of the item to renew: ");
        String renewTitle = scanner.nextLine();
    
        // Check if the item is a book
        for (Book book : checkedOutBooks) {
            if (book.getTitle().equals(renewTitle)) {
                if (book.canRenew()) {
                    // Check if the item is requested by another user, prevent renewal if requested
                    if (book.isItemRequested()) {
                        System.out.println("Cannot renew the book. There is an outstanding request for this item.");
                        return false;
                    } else {
                        // Renew the book
                        //LocalDate newDueDate = book.getDueDate().plusDays(book.getCheckoutPeriod());
                        book.setDueDate(true);
                        book.incrementRenewalCount(); // Update the renewal count
                        System.out.println(book.getTitle() + " renewed successfully for " + user.getName() + " (Due: " + book.getDueDate() + ")");
                        return true;
                    }
                } else {
                    System.out.println("Cannot renew the book. Maximum renewal limit reached.");
                    return false;
                }
            }
        }
    
        // Check if the item is an AV material
        for (AudioVideoMaterial avMaterial : checkedOutAVMaterials) {
            if (avMaterial.getTitle().equals(renewTitle)) {
                if (avMaterial.canRenew()) {
                    // Check if the item is requested by another user, prevent renewal if requested
                    if (avMaterial.isItemRequested()) {
                        System.out.println("Cannot renew the AV material. There is an outstanding request for this item.");
                        return false;
                    } else {
                        // Renew the AV material
                        //LocalDate newDueDate = avMaterial.getDueDate().plusDays(avMaterial.getCheckoutPeriod());
                        avMaterial.setDueDate(true);
                        avMaterial.incrementRenewalCount(); // Update the renewal count
                        System.out.println(avMaterial.getTitle() + " renewed successfully for " + user.getName() + " (Due: " + avMaterial.getDueDate() + ")");
                        return true;
                    }
                } else {
                    System.out.println("Cannot renew the AV material. Maximum renewal limit reached.");
                    return false;
                }
            }
        }
    
        // Item not found
        System.out.println("Item with the title \"" + renewTitle + "\" is not checked out by " + user.getName());
        return false;
    }
    
    
    public void requestItem(User user, LibraryItem item, Scanner scanner) {
        if (!item.isReferenceOnly()) {
            System.out.println("Would you like to request \"" + item.getTitle() + "\"? (yes/no)");
            String userResponse = scanner.nextLine().trim().toLowerCase();
    
            if (userResponse.equals("yes")) {
                item.toggleRequest(true);
                System.out.println("Item request for " + item.getTitle() + " submitted by " + user.getName());
            } else if (userResponse.equals("no")) {
                System.out.println("Item request for " + item.getTitle() + " cancelled.");
            } else {
                System.out.println("Invalid response. Please enter 'yes' or 'no'.");
            }
        } else {
            System.out.println("Reference items cannot be requested.");
        }
    }

    public double calculateOverdueFines(User user) {
        double totalFines = 0.0;

        for (Book checkedOutBook : user.getCheckedOutBooks()) {
            if (checkedOutBook.isOverdue()) {
                // Calculate the number of days the book is overdue
                int daysOverdue = (int) ChronoUnit.DAYS.between(checkedOutBook.getDueDate(), LocalDate.now());
                double fine = checkedOutBook.getFinePerDay() * daysOverdue;
            
                // Ensure the fine doesn't exceed the maximum allowed fine for a book
                fine = Math.min(fine, checkedOutBook.getMaxFine());
                totalFines += fine;
        }
    }
    return totalFines;
}

}
