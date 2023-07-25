import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class User {
    private String name;
    private String address;
    private String phoneNumber;
    private String libraryCardNumber;
    private int age;
    private List<Book> checkedOutBooks;
    private List<AudioVideoMaterial> checkedOutAVMaterials;

    public User(String name, String address, String phoneNumber, String libraryCardNumber, int age) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.libraryCardNumber = libraryCardNumber;
        this.age = age;
        this.checkedOutBooks = new ArrayList<>();
        this.checkedOutAVMaterials = new ArrayList<>();
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

    public void setLibraryCardNumber(String libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
    }

    public int getAge(){
        return age;
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void addCheckedOutBook(Book book) {
        if (checkedOutBooks == null) {
            checkedOutBooks = new ArrayList<>();
        }
        checkedOutBooks.add(book);
    }

    public void removeCheckedOutBook(Book book) {
        if (checkedOutBooks != null) {
            checkedOutBooks.remove(book);
        }
    }

    public List<AudioVideoMaterial> getCheckedOutAVMaterials() {
        return checkedOutAVMaterials;
    }

    public void addCheckedOutAVMaterial(AudioVideoMaterial avMaterial) {
        if (checkedOutAVMaterials == null) {
            checkedOutAVMaterials = new ArrayList<>();
        }
        checkedOutAVMaterials.add(avMaterial);
    }

    public void removeCheckedOutAVMaterial(AudioVideoMaterial avMaterial) {
        if (checkedOutAVMaterials != null) {
            checkedOutAVMaterials.remove(avMaterial);
        }
    }

    public void renewItem(LibraryItem item) {
        List<Book> checkedOutBooks = getCheckedOutBooks();
        List<AudioVideoMaterial> checkedOutAVMaterials = getCheckedOutAVMaterials();

        if (item instanceof Book) {
            if (checkedOutBooks.contains(item)) {
                if (item.canRenew()) {
                    // If the item is requested by another user, it cannot be renewed
                    if (isItemRequested(item)) {
                        System.out.println("Cannot renew the item. There is an outstanding request for this item.");
                    } else {
                        // Renew the item
                        LocalDate newDueDate = item.getDueDate().plusDays(item.getCheckoutPeriod());
                        item.setDueDate(true);
                        System.out.println(item.getTitle() + " renewed successfully for " + getName());
                    }
                } else {
                    System.out.println("Cannot renew the item. Maximum renewal limit reached.");
                }
            } else {
                System.out.println("The user hasn't checked out this book.");
            }
        } else if (item instanceof AudioVideoMaterial) {
            if (checkedOutAVMaterials.contains(item)) {
                if (item.canRenew()) {
                    // If the item is requested by another user, it cannot be renewed
                    if (isItemRequested(item)) {
                        System.out.println("Cannot renew the item. There is an outstanding request for this item.");
                    } else {
                        // Renew the item
                        LocalDate newDueDate = item.getDueDate().plusDays(item.getCheckoutPeriod());
                        item.setDueDate(true);
                        System.out.println(item.getTitle() + " renewed successfully for " + getName());
                    }
                } else {
                    System.out.println("Cannot renew the item. Maximum renewal limit reached.");
                }
            } else {
                System.out.println("The user hasn't checked out this audio/video material.");
            }
        } else {
            System.out.println("Invalid item type.");
        }
    }

    private boolean isItemRequested(LibraryItem item) {
        return false;
    }
}