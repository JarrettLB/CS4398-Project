import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        // Create an instance of LibrarySystem
        LibrarySystem librarySystem = new LibrarySystem();

        // Initialize data using LibraryData
        LibraryData initialData = new LibraryData(librarySystem);
        initialData.initData();

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
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add User Form
                    System.out.println("Please enter the following information:");

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Library Card Number: ");
                    int nextLibraryCardNumber = librarySystem.getNextLibraryCardNumber();
                    String libraryCardNumber = String.format("%04d", nextLibraryCardNumber);
                    System.out.println(libraryCardNumber);

                    System.out.print("Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    User newUser = new User(name, address, phoneNumber, String.format("%04d", nextLibraryCardNumber), age);
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

                        // Options when a user is found
                        int userOption;
                        do {
                            System.out.println("\nOptions for the found user:");
                            System.out.println("    1. Show Items Currently Checked Out");
                            System.out.println("    2. Check Out an Item");
                            System.out.println("    3. Return an Item");
                            System.out.println("    4. Renew an Item");
                            System.out.println("    0. Back to Main Menu");

                            System.out.print("Enter your choice: ");
                            userOption = scanner.nextInt();

                            switch (userOption) {
                                case 1:
                                    // Show Items Currently Checked Out
                                    librarySystem.handleShowCheckedOutItems(foundUser);
                                    break;

                                case 2:
                                    int checkoutOption;
                                    do {
                                        System.out.println("\nOptions for checking out item:");
                                        System.out.println("    1. Checkout Book");
                                        System.out.println("    2. Checkout Audio/Video Material");
                                        System.out.println("    0. Back to Previous Menu");
                    
                                        System.out.print("Enter your choice: ");
                                        checkoutOption = scanner.nextInt();
                                        scanner.nextLine(); // Consume the newline character after reading the integer input
                    
                                        switch (checkoutOption) {
                                            case 1:
                                                librarySystem.handleCheckOutBook(foundUser, librarySystem);
                                                break;
                                            case 2:
                                                librarySystem.handleCheckoutAVMaterial(foundUser, librarySystem);
                                                break;
                                            case 0:
                                                // Back to Previous Menu
                                                break;
                                            default:
                                                System.out.println("Invalid option. Please try again.");
                                                break;
                                        }
                                    } while (checkoutOption != 0);
                                    break;
                                        
                                case 3:
                                    // Return an Item
                                    System.out.print("Enter the title of the item to return: ");
                                    String returnTitle = scanner.nextLine();

                                    // Assuming the LibrarySystem has a method to return the item
                                    boolean returnSuccess = librarySystem.returnItem(foundUser, returnTitle);
                                    if (returnSuccess) {
                                        System.out.println("Item returned successfully.");
                                    } else {
                                        System.out.println("Item not found in the user's checked-out items.");
                                    }
                                    break;

                                case 4:
                                    // Renew an Item
                                    System.out.print("Enter the title of the item to renew: ");
                                    String renewTitle = scanner.nextLine();

                                    // Assuming the LibrarySystem has a method to renew the item
                                    boolean renewSuccess = librarySystem.renewItem(foundUser,renewTitle);
                                    if (renewSuccess) {
                                        System.out.println("Item renewed successfully.");
                                    } else {
                                        System.out.println("Item not found in the user's checked-out items.");
                                    }
                                    break;

                                case 0:
                                    // Back to Main Menu
                                    System.out.println("Returning to the Main Menu.");
                                    break;

                                default:
                                    System.out.println("Invalid option. Please try again.");
                                    break;
                            }
                        } while (userOption != 0);
                            } else {
                                System.out.println("User with the provided Library Card Number not found.");
                            }
                            break;

                case 3:
                    // Gather book information from the user
                    System.out.print("Enter the title of the book: ");
                    String title = scanner.nextLine();

                    System.out.print("Is the book a reference (true/false): ");
                    boolean isReference = scanner.nextBoolean();

                    System.out.print("Is the book a bestseller (true/false): ");
                    boolean isBestSeller = scanner.nextBoolean();

                    // Create the book object with user-provided information
                    Book newBook = new Book(title, isReference, isBestSeller);

                    // Add the book to the library system
                    librarySystem.addBook(newBook);
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
                    scanner.nextLine();

                    System.out.print("Is Best Seller (true/false): ");
                    boolean avIsBestSeller = scanner.nextBoolean();
                    scanner.nextLine();

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
