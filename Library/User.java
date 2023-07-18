import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class User {
    private String name;
    private String address;
    private String phoneNumber;
    private String libraryCardNumber;
    private List<LibraryItem> checkedOutItems;

    public User(String name, String address, String phoneNumber, String libraryCardNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.libraryCardNumber = libraryCardNumber;
        this.checkedOutItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public List<LibraryItem> getCheckedOutItems() {
        return checkedOutItems;
    }

    public void checkOutItem(LibraryItem item) {
        // Check if the user has already checked out this item
        if (!getCheckedOutItems().contains(item)) {
            if (item instanceof Book && getCheckedOutItems().stream().filter(i -> i instanceof Book).count() >= 5) {
                System.out.println("Children can only check out five items at a time.");
                return;
            }

            // Set the due date for the item
            LocalDate dueDate = LocalDate.now().plusDays(item.getCheckoutPeriod());
            item.setDueDate(dueDate);
            // Add the item to the user's checkedOutItems
            getCheckedOutItems().add(item);
            System.out.println(item.getTitle() + " checked out successfully by " + getName() + ". Due date: " + dueDate);
        } else {
            System.out.println("The user has already checked out this item.");
        }
    }

    public void returnItem(LibraryItem item) {
        checkedOutItems.remove(item);
    }

    public void renewItem(LibraryItem item) {
        if (checkedOutItems.contains(item)) {
            if (item.canRenew()) {
                // If the item is requested by another user, it cannot be renewed
                if (isItemRequested(item)) {
                    System.out.println("Cannot renew the item. There is an outstanding request for this item.");
                } else {
                    item.setDueDate(item.getDueDate().plusDays(item.getCheckoutPeriod()));
                    System.out.println(item.getTitle() + " renewed successfully for " + getName());
                }
            } else {
                System.out.println("Cannot renew the item. Maximum renewal limit reached.");
            }
        } else {
            System.out.println("The user hasn't checked out this item.");
        }
    }

    private boolean isItemRequested(LibraryItem item) {
        return false;
    }
}