import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LibraryManagementApp {

    private LibrarySystem library;
    private JFrame frame;
    private JTextField nameTextField, addressTextField, phoneTextField, cardTextField;
    private JList<String> usersListView, availableItemsListView, checkedOutItemsListView;
    private JTextArea historyTextArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementApp().createAndShowGUI());
    }

    private void createAndShowGUI() {

        library = new LibrarySystem();

        frame = new JFrame("Library Management App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        addressTextField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneTextField = new JTextField();
        JLabel cardLabel = new JLabel("Library Card Number:");
        cardTextField = new JTextField();
        JLabel userLabel = new JLabel("User:");
        JLabel availableItemsLabel = new JLabel("Available Items:");
        JLabel checkedOutItems = new JLabel("Checked Out Items:");
        JButton addUserButton = new JButton("Add User");
        JButton checkOutButton = new JButton("Check Out Item");
        JButton returnButton = new JButton("Return Item");
        JButton renewButton = new JButton("Renew Item");

        //why isnt this working

        usersListView = new JList<>();
        availableItemsListView = new JList<>();
        checkedOutItemsListView = new JList<>();

        historyTextArea = new JTextArea(10, 30);
        historyTextArea.setEditable(false);
        JScrollPane historyScrollPane = new JScrollPane(historyTextArea);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        mainPanel.add(nameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(addressTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(phoneTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(cardLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(cardTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        mainPanel.add(addUserButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        mainPanel.add(userLabel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        mainPanel.add(availableItemsLabel, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        mainPanel.add(checkedOutItems, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 8;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        mainPanel.add(new JScrollPane(usersListView), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        mainPanel.add(new JScrollPane(availableItemsListView), gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        mainPanel.add(new JScrollPane(checkedOutItemsListView), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        mainPanel.add(checkOutButton, gbc);

        gbc.gridy = 6;
        mainPanel.add(returnButton, gbc);

        gbc.gridy = 7;
        mainPanel.add(renewButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        mainPanel.add(historyScrollPane, gbc);

        frame.add(mainPanel);
        updateLists();
        frame.setVisible(true);

        // Add event listeners
        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser(nameTextField.getText(), addressTextField.getText(),
                        phoneTextField.getText(), cardTextField.getText());
            }
        });

        checkOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkOutItem(usersListView.getSelectedValue(), availableItemsListView.getSelectedValue());
                displayHistoryLog(usersListView.getSelectedValue() + " checked out " +
                        availableItemsListView.getSelectedValue() + " at " + getCurrentTime());
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnItem(usersListView.getSelectedValue(), checkedOutItemsListView.getSelectedValue());
                displayHistoryLog(usersListView.getSelectedValue() + " returned " +
                        checkedOutItemsListView.getSelectedValue() + " at " + getCurrentTime());
            }
        });

        renewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renewItem(usersListView.getSelectedValue(), checkedOutItemsListView.getSelectedValue());
                displayHistoryLog(usersListView.getSelectedValue() + " renewed " +
                        checkedOutItemsListView.getSelectedValue() + " at " + getCurrentTime());
            }
        });

    }

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    private void displayHistoryLog(String logMessage) {
        historyTextArea.append(logMessage + "\n");
        historyTextArea.setCaretPosition(historyTextArea.getDocument().getLength());
    }

    private void addUser(String name, String address, String phoneNumber, String cardNumber) {
        User user = new User(name, address, phoneNumber, cardNumber);
        library.addUser(user);
        updateLists();
    }

    private void checkOutItem(String userName, String itemTitle) {
        User user = library.getUserByName(userName);
        LibraryItem item = library.getItemByTitle(itemTitle);
        if (user != null && item != null) {
            library.checkOutItem(user, item);
            updateLists();
        }
    }

    private void returnItem(String userName, String itemTitle) {
        User user = library.getUserByName(userName);
        LibraryItem item = library.getItemByTitle(itemTitle);
        if (user != null && item != null) {
            library.returnItem(user, item);
            updateLists();
        }
    }

    private void renewItem(String userName, String itemTitle) {
        User user = library.getUserByName(userName);
        LibraryItem item = library.getItemByTitle(itemTitle);
        if (user != null && item != null) {
            library.renewItem(user, item);
            updateLists();
        }
    }

    private void updateLists() {
        DefaultListModel<String> usersListModel = new DefaultListModel<>();
        for (User user : library.getUsers()) {
            usersListModel.addElement(user.getName());
        }
        usersListView.setModel(usersListModel);

        DefaultListModel<String> availableItemsListModel = new DefaultListModel<>();
        DefaultListModel<String> checkedOutItemsListModel = new DefaultListModel<>();
        for (LibraryItem item : library.getItems()) {
            if (item.isAvailable()) {
                availableItemsListModel.addElement(item.getTitle());
            } else {
                checkedOutItemsListModel.addElement(item.getTitle() + " (Due: " + item.getDueDate() + ")");
            }
        }
        availableItemsListView.setModel(availableItemsListModel);
        checkedOutItemsListView.setModel(checkedOutItemsListModel);
    }
}
