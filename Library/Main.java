import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        // Create an instance of LibrarySystem
        LibrarySystem librarySystem = new LibrarySystem();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Library Management System.");
        int choice;

        do {
            System.out.println("What would you like to do?");
            System.out.println("    1. Add User");
            System.out.println("    2. Lookup User");
            System.out.println("    3. Add Book");
            System.out.println("    4. Lookup Book");
            System.out.println("    5. Add Audio/Video Material");
            System.out.println("    6. Lookup Audio/Video Material");
            System.out.println("    0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer input

            switch (choice) {
                case 1:
                    System.out.println("Please enter the following information:");

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Library Card Number: ");
                    String libraryCardNumber = scanner.nextLine();

                    System.out.print("Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input

                    User newUser = new User(name, address, phoneNumber, libraryCardNumber, age);
                    librarySystem.addUser(newUser);
                    break;

                case 2:
                    // Lookup User by Library Card Number
                    System.out.print("Enter the Library Card Number to search: ");
                    String searchLibraryCardNumber = scanner.nextLine();

                    User foundUser = librarySystem.getUserByLibraryCardNumber(searchLibraryCardNumber);
                    if (foundUser != null) {
                        System.out.println("User found:");
                        System.out.println("Name: " + foundUser.getName());
                        System.out.println("Address: " + foundUser.getAddress());
                        System.out.println("Phone Number: " + foundUser.getPhoneNumber());
                        System.out.println("Library Card Number: " + foundUser.getLibraryCardNumber());
                        System.out.println("Age: " + foundUser.getAge());
                    } else {
                        System.out.println("User with the provided Library Card Number not found.");
                    }
                    break;

                case 3:
                    // Add Book
                    System.out.println("Please enter the following information to add a book:");

                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Is Reference Only (true/false): ");
                    boolean isReferenceOnly = scanner.nextBoolean();
                    scanner.nextLine(); // Consume the newline character after reading the boolean input

                    System.out.print("Is Best Seller (true/false): ");
                    boolean isBestSeller = scanner.nextBoolean();
                    scanner.nextLine(); // Consume the newline character after reading the boolean input

                    librarySystem.addBook(title, isReferenceOnly, isBestSeller);
                    System.out.println("Book added successfully!");
                    break;

                case 4:
                    // Lookup Book by Title
                    System.out.print("Enter the title of the book to search: ");
                    String searchBookTitle = scanner.nextLine();

                    Book foundBook = librarySystem.getBookByTitle(searchBookTitle);
                    if (foundBook != null) {
                        System.out.println("Book found:");
                        System.out.println("Title: " + foundBook.getTitle());
                        System.out.println("Is Reference Only: " + foundBook.isReferenceOnly());
                        System.out.println("Is Best Seller: " + foundBook.isBestSeller());
                    } else {
                        System.out.println("Book with the provided title not found.");
                    }
                    break;

                case 5:
                    // Add Audio/Video Material
                    System.out.println("Please enter the following information to add an audio/video material:");

                    System.out.print("Title: ");
                    String avTitle = scanner.nextLine();

                    System.out.print("Is Reference Only (true/false): ");
                    boolean avIsReferenceOnly = scanner.nextBoolean();
                    scanner.nextLine(); // Consume the newline character after reading the boolean input

                    System.out.print("Is Best Seller (true/false): ");
                    boolean avIsBestSeller = scanner.nextBoolean();
                    scanner.nextLine(); // Consume the newline character after reading the boolean input

                    librarySystem.addAudioVideoMaterial(new AudioVideoMaterial(avTitle, avIsReferenceOnly, avIsBestSeller));
                    System.out.println("Audio/Video Material added successfully!");
                    break;

                case 6:
                    // Lookup Audio/Video Material by Title
                    System.out.print("Enter the title of the audio/video material to search: ");
                    String searchAVMaterialTitle = scanner.nextLine();

                    AudioVideoMaterial foundAVMaterial = librarySystem.getAVMaterialByTitle(searchAVMaterialTitle);
                    if (foundAVMaterial != null) {
                        System.out.println("Audio/Video Material found:");
                        System.out.println("Title: " + foundAVMaterial.getTitle());
                        System.out.println("Is Reference Only: " + foundAVMaterial.isReferenceOnly());
                        System.out.println("Is Best Seller: " + foundAVMaterial.isBestSeller());
                    } else {
                        System.out.println("Audio/Video Material with the provided title not found.");
                    }
                    break;
                    
                case 0:
                    // Exit
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }

        } while (choice != 0);

        scanner.close();
    }
}
