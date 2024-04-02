import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TMAWarehouseApp {
    public static void main(String[] args) {
        try {
            // Java Database Connection to MySQL connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmawarehouse", "root", "");

            // Initializing the scanner for user to input the desired values
            Scanner scanner = new Scanner(System.in);

            // Displaying the welcome message and getting user input
            System.out.println("Who are you? Click 'E' if you are an Employee or click 'C' if you are a Coordinator");
            char userType = scanner.next().toUpperCase().charAt(0);

            // Checking the user input and directing the user to appropriate menu
            switch (userType) {
                case 'E':
                    employeeFunctionality(con, scanner);
                    break;
                case 'C':
                    coordinatorFunctionality(con, scanner);
                    break;
                default:
                    System.out.println("We can't recognize who are you. Please try again.");
            }

            // Closing the connection with the database
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Function created to describe what an employee does
    public static void employeeFunctionality(Connection con, Scanner scanner) {
        ItemService itemService = new ItemServiceImpl(new ItemDAO(con));
        System.out.println("Hello, dear employee. Welcome!");
        List<Item> goodsList = itemService.getAllItems();
        System.out.println("List of Goods:");
        displayItems(goodsList);

        // Employee makes order for a specific items
        System.out.println("Enter the ID of the item you want to order:");
        int itemId = scanner.nextInt(); // Assuming item ID is entered by the user
        scanner.nextLine(); // Consuming character entered by the user
        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt(); // Assuming quantity is entered by the user
        System.out.println("Enter price without VAT:");
        double priceWithoutVAT = scanner.nextDouble(); // Assuming price without VAT is entered by the user
        scanner.nextLine(); // Consuming character entered by the user
        System.out.println("Enter comment:");
        String comment = scanner.nextLine(); // Assuming comment is entered by the user

        // Submitting the order from the employee
        createRequest(con, itemId, quantity, priceWithoutVAT, comment);
        System.out.println("Congratulations. The request has been created");
    }

    // Function created to describe what a coordinator does
    public static void coordinatorFunctionality(Connection con, Scanner scanner) {
        ItemService itemService = new ItemServiceImpl(new ItemDAO(con));
        TMARequestService requestService = new TMARequestServiceImpl(new TMARequestDAO(con));

        System.out.println("Hello, dear coordinator. Welcome!");
        // Displaying list of goods/items
        List<Item> goodsList = itemService.getAllItems();
        System.out.println("List of Goods:");
        displayItems(goodsList);

        // Displaying list of purchase requests created by the employee
        List<TMARequest> requestList = requestService.getAllRequests();
        System.out.println("List of Purchase Requests:");
        displayRequests(requestList);

        System.out.println("Enter the ID of the request you want to open:");
        int requestId = scanner.nextInt();
        scanner.nextLine(); // Consuming character entered by the user

        // Opening a request
        TMARequest request = requestService.getRequestById(requestId);
        if (request != null) {
            System.out.println("Request Details:");
            System.out.println(request);
        } else {
            System.out.println("Request not found.");
        }
    }

    
    // Method that creates a request
    private static void createRequest(Connection con, int itemId, int quantity, double priceWithoutVAT, String comment) {
        TMARequestDAO requestDAO = new TMARequestDAO(con);
        requestDAO.createRequest(itemId, quantity, priceWithoutVAT, comment);
    }

    // Method that displays list of items
    private static void displayItems(List<Item> items) {
        for (Item item : items) {
            System.out.println(item);
        }
    }

    // Method that displays list of purchase requests
    private static void displayRequests(List<TMARequest> requests) {
        for (TMARequest request : requests) {
            System.out.println(request);
        }
    }
}
