
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;

    private JComboBox<String> categoryComboBox;
    private JPanel mainPanel;

    private JButton addToCartButton;
    private JButton shoppingCartButton;

    private ArrayList<Product> productList;
    private ShoppingCart shoppingCart;

    // Constructor for Gui class
    public Gui(ArrayList<Product>productList){
        this.productList=productList;
        this.shoppingCart=new ShoppingCart();
        initialize(productList);

    }
    // Getter method for the JFrame
    public JFrame getFrame(){
        return frame;
    }

    // Initialize the GUI components
    private void initialize(ArrayList<Product>productList) {
        frame = new JFrame("Westminster Shopping Centre");
        frame.setSize(800, 600);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainPanel=new JPanel(new BorderLayout());
        //create a panel for the top section
        JPanel topPanel=new JPanel(new FlowLayout());

        //Adding JLabel to the Panel
        JLabel categoryLabel = new JLabel("Select Product Category");
        topPanel.add(categoryLabel);
//        topPanel.add(addToCartButton); //

        //Adding JComboBox to select product category
        categoryComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }

        });

        topPanel.add(categoryComboBox);
        //Create Shopping cart button and adding in top panel
//        createTopPanel();
        shoppingCartButton=new JButton("Shopping Cart");
        shoppingCartButton.addActionListener(e->showShoppingCart());
        topPanel.add(shoppingCartButton);
        //Add topPanel to mainPanel
        mainPanel.add(topPanel,BorderLayout.NORTH);



//        topPanel.add(categoryComboBox, BorderLayout.NORTH);
        mainPanel.add(topPanel,BorderLayout.NORTH);

        model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Product ID");
        model.addColumn("Name");
        model.addColumn("Category");
        model.addColumn("Price($)");
        model.addColumn("Info");

        JScrollPane scrollPane=new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel detailsPanel=createDetailsPanel();
        mainPanel.add(detailsPanel,BorderLayout.SOUTH);



        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Initial population of the table
        this.productList=productList;
        populateTable(productList);

        //Add a selection listener to the table
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                showDetails(productList.get(selectedRow));
            }
        });

    }
    // Populate the table with products
    private void populateTable(ArrayList<Product>productList) {
        model.setRowCount(0);
        for (Product product : productList) {
            addRowToTable(product);
        }
    }
    // Update the table based on the selected category
    private void updateTable() {
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        ArrayList<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if ((selectedCategory.equals("All")) ||
                    (selectedCategory.equals("Electronics") && product instanceof Electronics) ||
                    (selectedCategory.equals("Clothing") && product instanceof Clothing)) {
                filteredList.add(product);
            }
        }
        populateTable(filteredList);
    }
    // Add a row to the table
    private void addRowToTable(Product product) {
        String category;
        String info;

        if (product instanceof Electronics) {
            category = "Electronics";
            info = "Brand:" + ((Electronics) product).getBrand();
        } else if (product instanceof Clothing) {
            category = "Clothing";
            info = "Size:" + ((Clothing) product).getSize() +
                    ",Colour:" + ((Clothing) product).getColor();
        } else {
            category = "Unknown";
            info = "N/A";
        }

        model.addRow(new Object[]{product.getProductId(), product.getProductName(), category, product.getPrice(), info});

    }

    // Show details of the selected product
    private void showDetails(Product product){
        JTextArea detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.setText("Product ID:" + product.getProductId() + "\n"+
                "Name:" + product.getProductName() + "\n"+
                "Category:" + product.getCategoryLabel(product) + "\n"+
                "Price:$" + product.getPrice() + "\n"+
                "Items Available "+product.getNoOfAvailableItems()+"\n"+getAdditionalInfo(product));
        // Remove the existing details panel
        mainPanel.remove(mainPanel.getComponentCount() - 1);

        // Create a new details panel with "Add to Cart" button
        JPanel detailsPanel = createDetailsPanel();
        detailsPanel.add(new JScrollPane(detailsTextArea), BorderLayout.CENTER);

        // Add the new details panel to the main Panel
        mainPanel.add(detailsPanel, BorderLayout.SOUTH);

        mainPanel.add(detailsPanel,BorderLayout.SOUTH);
        //Revalidate and repaint the mainPanel
        mainPanel.revalidate();
        mainPanel.repaint();

    }
    private String getCategoryLabel(Product product){
        if (product instanceof Electronics) {
            return "Electronics";

        } else if (product instanceof Clothing) {
            return "Clothing";
        } else {
            return "Unknown";
        }
    }
    private String getAdditionalInfo(Product product){
        if (product instanceof Electronics) {
            return "Brand:" + ((Electronics) product).getBrand() +
                    ",Warranty Period:" + ((Electronics) product).getWarrantyPeriod();
        }else if(product instanceof Clothing) {
            return "Size:" + ((Clothing) product).getSize() +
                    ",Colour:" + ((Clothing) product).getColor();
        }else{
            return"N/A";
        }
    }
    private JPanel createDetailsPanel(){
        JPanel detailsPanel=new JPanel(new BorderLayout());
        JTextArea detailsTextArea=new JTextArea();
        detailsTextArea.setEditable(false);
        JScrollPane detailsScrollPane=new JScrollPane(detailsTextArea);
        addToCartButton=new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> addToCart());
        detailsPanel.add(addToCartButton, BorderLayout.SOUTH);

        return detailsPanel;
    }
    // Create a panel for displaying details and "Add to Cart" button
    private void addToCart(){
        int selectedRow=table.getSelectedRow();
        if(selectedRow!=-1){
            Product selectedProduct=productList.get(selectedRow);
            shoppingCart.addProduct(selectedProduct);
        }
    }
    private void createTopPanel(){
        JPanel topPanel=new JPanel(new FlowLayout());
        shoppingCartButton=new JButton("Shopping Cart");
        shoppingCartButton.addActionListener(e->showShoppingCart());
        topPanel.add(shoppingCartButton);
        // shoppingCartButton.setBounds(10,10,120,30);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        // Optionally, refresh the GUI
        mainPanel.repaint();

    }
    // Add the selected product to the shopping cart
    private void showShoppingCart(){
        ShoppingCartWindow cartWindow=new ShoppingCartWindow(shoppingCart);
        cartWindow.setVisible(true);
    }
    public static void main(String[]args){
        SwingUtilities.invokeLater(()->{
            ArrayList<Product>productList=new ArrayList<>();
            Gui window=new Gui(productList);
            window.getFrame().setVisible(true);
        });
    }

}