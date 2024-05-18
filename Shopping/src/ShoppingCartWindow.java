import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShoppingCartWindow extends JFrame {
    private JTable cartTable;
    private Table cartModel;
    private ShoppingCart shoppingCart;

    // Constructor for ShoppingCartWindow
    public ShoppingCartWindow(ShoppingCart shoppingCart){
        this.shoppingCart=shoppingCart;

        initialize();

    }
    // Initialize the components of the shopping cart window
    private void initialize(){
        setTitle("Shopping Cart");
        setSize(600,400);

        // Create a top panel with a table displaying the items in the shopping cart
        JPanel topPanel = new JPanel(new BorderLayout());
        cartModel = new Table(shoppingCart.getProductList());
        cartTable=new JTable(cartModel);
        cartTable.setRowHeight(55);
        cartTable.setPreferredScrollableViewportSize(new Dimension(400, 200));
        JScrollPane scrollPane=new JScrollPane(cartTable);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        // Create a details panel with information about the total cost and discounts
        JPanel detailsPanel = new JPanel(new GridLayout(4,2));
        detailsPanel.add(new JLabel("Total"));
        detailsPanel.add(new JLabel(String.valueOf(shoppingCart.calculateTotalCost())));
        detailsPanel.add(new JLabel("First Purchase Discount (10%)"));
        detailsPanel.add(new JLabel(String.valueOf(shoppingCart.getFirstPurchaseDiscount())));
        detailsPanel.add(new JLabel("Three items in same Category Discount (20%)"));
        detailsPanel.add(new JLabel(String.valueOf(shoppingCart.getCategoryDiscount())));
        detailsPanel.add(new JLabel("Final Total"));
        detailsPanel.add(new JLabel(String.valueOf(shoppingCart.calculateTotalCost())));

        // Add components to the window
        add(topPanel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
    }

    private String getProductInfo(Product product){
        if (product instanceof Clothing){
            return"product info";
        }

        return null;
    }
}
