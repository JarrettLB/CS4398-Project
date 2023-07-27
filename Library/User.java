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
}