import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.util.Collections;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {
    private ArrayList<Product> productList;
    private final int productsNo;

    public static void main(String[] args) {
        WestminsterShoppingManager ShoppingManager = new WestminsterShoppingManager();
        ShoppingManager.runMenu();
    }

    // Constructor for WestminsterShoppingManager
    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
        this.productsNo = 50;
    }

    public boolean runMenu() {
        while (true) {
            System.out.println();
            Scanner input = new Scanner(System.in);
            System.out.println("Enter 1 to add products to the list:");
            System.out.println("Enter 2 to remove products from the list:");
            System.out.println("Enter 3 to print lists of products:");
            System.out.println("Enter 4 to save products:");
            System.out.println("Enter 5 to exit the programme:");
            System.out.println("Enter 6 to show the GUI:");
            String option = input.next();

            switch (option) {
                case "1":
                    addProducts();
                    break;
                case "2":
                    deleteProducts();
                    break;
                case "3":
                    printList();
                    break;
                case "4":
                    saveFile();
                    break;
                case "5":
                    System.out.println("Exit the program");
                    return false;
                case "6":
                    showGUI();
                    break;
                default:
                    System.out.println("Enter a valid option");


            }
        }
    }

    public void addProducts() {
        if (productList.size() < productsNo) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter 1 to add electronics ,2 to add Clothing:");
            int option = input.nextInt();
            input.nextLine();
            if (option == 1) {
                System.out.println("Name of the product: ");
                String productName = input.nextLine();
                System.out.println("Product id: ");
                String productId = input.nextLine();
                System.out.println("Number of available items: ");
                int noOfAvailableItems = input.nextInt();
                System.out.println("Price: ");
                double price = input.nextDouble();
                input.nextLine();
                System.out.println("Brand: ");
                String brand = input.nextLine();
                System.out.println("Warranty period: ");
                int warrantyPeriod = input.nextInt();
                Electronics newObj = new Electronics(productId, productName, noOfAvailableItems, price, brand, warrantyPeriod);
                productList.add(newObj);
                System.out.println("Electronics product" + productId + "Added to the list");

            } else if (option == 2) {
                System.out.println("Name of the product: ");
                String productName = input.nextLine();
                System.out.println("Product id: ");
                String productId = input.nextLine();
                System.out.println("No of available items: ");
                int noOfAvailableItems = input.nextInt();
                System.out.println("Price");
                double price = input.nextDouble();
                System.out.println("Size of the clothing: ");
                String size = input.nextLine();
                System.out.println("Color: ");
                String colour = input.nextLine();
                System.out.println();
                Clothing newObj2 = new Clothing(productId, productName, noOfAvailableItems, price, size, colour);
                productList.add(newObj2);
                System.out.println("Clothing product" + productId + "Added to the list");
            } else {
                System.out.println("no more products can be added");

            }

        }
    }

    public void deleteProducts() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter product Id to Delete:");
        String productId = input.nextLine();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId().equals(productId)) {
                System.out.println(productList.get(i));
                System.out.println();
                System.out.println("Do you want to delete the products");
                String option2 = input.nextLine();
                if (option2.equals("Yes")) {
                    System.out.println(productList.get(i) + "Removed");
                    productList.remove(i);
                    System.out.println(productList.size() + "Left");
                } else {
                    System.out.println("Product deletion canceled");
                }
                break; //to exist the loop after removing the product
            }
        }
    }

    public void printList() {
        if (productList.isEmpty()) {
            System.out.println("No products");
            return;
        }
        //sort the productList alphabetically by product ID
        Collections.sort(productList, (p1, p2) -> p1.getProductId().compareTo(p2.getProductId()));
        System.out.println("List of Products");

        for (Product product : productList) {
            if (product instanceof Electronics) {
                System.out.println("Electronics product: ");
                System.out.println("ID: " + product.getProductId());
                System.out.println("Name:" + product.getProductName());
                System.out.println("Available Items: " + product.getNoOfAvailableItems());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Brand: " + ((Electronics) product).getBrand());
                System.out.println("Warranty Period: " + ((Electronics) product).getWarrantyPeriod());
            } else if (product instanceof Clothing) {
                System.out.println("Clothing product: ");
                System.out.println("ID: " + product.getProductId());
                System.out.println("Name: " + product.getProductName());
                System.out.println("Available Items: " + product.getNoOfAvailableItems());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Size: " + ((Clothing) product).getSize());
                System.out.println("Color: " + ((Clothing) product).getColor());

            }
            System.out.println("-------------------------------------------------------------");
        }
    }

    @Override
    public void saveFile() {

    }
    public void showGUI(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gui window = new Gui(productList);
                    window.getFrame().setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




}