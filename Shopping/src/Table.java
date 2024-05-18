import javax.swing.table.AbstractTableModel;
import java.util.List;

// Custom TableModel for displaying product information in a JTable
public class Table extends AbstractTableModel {
    // Column names for the table
    private String[] columnNames = {"Product", "Quantity", "Price"};
    // List of products to be displayed in the table
    private List<Product> productList;

    // Constructor for the Table class
    public Table(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int getRowCount() {
        return productList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // Get the value at a specific row and column in the table
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = productList.get(rowIndex);
        Object tempory= null;

        if (columnIndex == 0) {
            tempory = "<html>" +
                    product.getProductId() + "<br/>" +
                    product.getProductName() + "<br/>";

            if (product instanceof Electronics) {
                tempory += ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod() + "</html>";
            } else if (product instanceof Clothing) {
                tempory += ((Clothing) product).getSize() + ", " + ((Clothing) product).getColor() + "</html>";
            }

        } else if (columnIndex == 1) {
            tempory = 1;
        } else if (columnIndex == 2) {
            tempory = product.getPrice();
        }

        return tempory;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}

