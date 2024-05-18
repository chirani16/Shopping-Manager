public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    // Constructor for Electronics
    public Electronics(String productId, String productName, int noOfAvailableItems, double price, String brand, int warrantyPeriod) {
        super(productId, productName, noOfAvailableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public Electronics() {

    }

    public Electronics(String brand, int warrantyPeriod) {
    }

    public Electronics(String brand, int warrantyPeriod, String productName, int noOfAvailableItems, double price) {
    }

    public Electronics(String Id, String brand, int warrantyPeriod, String productName, int noOfAvailableItems, double price) {
    }

    // Getters and setters for Electronics
    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "Electronics{" +
                "Brand='" + brand + '\'' +
                ", Warranty period=" + warrantyPeriod +
                '}';
    }

}