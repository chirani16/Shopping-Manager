// Clothing class extends Product, representing a clothing product in the shopping manager system
public class Clothing extends Product {
    // Instance variables for Clothing class
    private String size;
    private String color;

    // Constructor for Clothing
    public Clothing(String productId, String productName, int noOfAvailableItems, double price, String size, String color) {
        // Call the constructor of the superclass (Product) using 'super'
        super(productId, productName, noOfAvailableItems, price);
        // Initialize the attributes for Clothing
        this.size = size;
        this.color = color;
    }

    public Clothing() {


    }

    // Getters and setters for Clothing
    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Override display method
    @Override
    public String toString() {
        return "Clothing{" +
                "size=" + size +
                ", color='" + color + '\'' +
                '}';
    }
}