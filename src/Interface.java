import entities.Product;
import entities.Supplier;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;

import javax.swing.*;
import javax.swing.JOptionPane.*;
import java.text.DecimalFormat;

public class Interface {

    private final Supplier[] supplier = new Supplier[5];
    private final Product[] product = new Product[10];
    private int indexProduct = 0;
    private int indexSupplier = 0;

    public void menu() {
        int opt, answer;
        String aux = "1. Register product\n"
                + "2. Search product by name\n"
                + "3. Search supplier by CNPJ\n"
                + "4. Exit \n";
        while (true) {
            opt = parseInt(showInputDialog(aux));
            if (opt == 4) {
                answer = showConfirmDialog(null, "Do you really wanna exit the program?");
                if (answer == 0) {
                    break;
                }
            }
            if (opt < 1 || opt > 4) {
                showMessageDialog(null, "Invalid Option");
            } else {
                switch (opt) {
                    case 1:
                        registerProduct();
                        break;
                    case 2:
                        researchProduct();
                        break;
                    case 3:
                        research();
                        break;
                }
            }
        }

    }

    private void research() {
        Supplier supplier = researchSupplier();
        if (supplier != null) {
            String aux = "Supplier : " + supplier.getName() + "\n";
            aux+= "CPNJ: " + supplier.getCnpj();
            showMessageDialog(null, aux);
        }
    }

    private void registerProduct() {
        Supplier supplier = researchSupplier();
        String name;
        Double price;
        int quantity;
        if (supplier == null) {
            supplier = registerSupplier();
        }
        name = showInputDialog("Type product name: :");
        price = parseDouble(showInputDialog("Type product price:"));
        quantity = parseInt(showInputDialog("Quantity in stock:"));
        product[indexProduct] = new Product(name, price, quantity, supplier);
        indexProduct++;
    }

    private void researchProduct() {
        DecimalFormat df = new DecimalFormat("0.00");
        String name = showInputDialog("Product Name:");
        String aux = "";

        for (int i = 0; i < indexProduct; i++) {
            if (product[i].getName().equalsIgnoreCase(name)) {
                aux += "Product name: " + name + "\n";
                aux += "Product price: "+ df.format(product[i].getPrice())+ "\n";
                aux += "Quantity in stock: " + product[i].getQuantity()+ "\n";
                aux += "Supplier name: " + product[i].getSupplier().getName();
                break;
            }
        }
        showMessageDialog(null, aux);
    }

    private Supplier researchSupplier() {
        long cnpj = parseLong(showInputDialog("Enter CPNJ number: "));
        for (int i = 0; i < indexSupplier; i++) {
            if (cnpj == supplier[i].getCnpj()) {
                return supplier[i];
            }
        }
        showMessageDialog(null, cnpj + " not found");
        return null;
    }

    private Supplier registerSupplier() {
        String name;
        long cpnj;
        Supplier f = null;
        if (indexSupplier < supplier.length) {
            name = showInputDialog("Supplier Name:");
            cpnj = parseLong(showInputDialog("Type supplier CNPJ number: "));
            f = new Supplier(name, cpnj);
            supplier[indexSupplier] = f;
            indexSupplier++;
        }
        return f;

    }
}
