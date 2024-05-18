abstract public class Product {
    // Instance variables for a product
    private String productId;
    private String productName;
    private int noOfAvailableItems;
    private double price;

    // Constructor for initializing a product with specific attributes
    public Product(String productId, String productName, int noOfAvailableItems, double price) {
        this.productId = productId;
        this.productName = productName;
        this.noOfAvailableItems = noOfAvailableItems;
        this.price = price;
    }

    public Product() {

    }

    // Getters and setters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getNoOfAvailableItems() {
        return noOfAvailableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNoOfAvailableItems(int availableItems) {
        this.noOfAvailableItems = availableItems;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Override the toString method to provide a string representation
    @Override
    public String toString() {
        return "Product{" +
                "Id=" + productId +
                ", name='" + productName + '\'' +
                ", no_of_available_items=" + noOfAvailableItems +
                ", price=" + price +
                '}';
    }

    public boolean size() {
        return false;
    }

    public Object getProduct() {
        return null;
    }

    // Method to get the category label of a product (Electronics, Clothing, or Unknown)
    public String getCategoryLabel(Product product) {
        if(this instanceof Electronics){
            return "Electronics";
        }else if(this instanceof Clothing){
            return "Clothing";
        }else{
            return "Unknown";
        }
    }

    public interface ShoppingManager{
        String getProductId();
    }
}
