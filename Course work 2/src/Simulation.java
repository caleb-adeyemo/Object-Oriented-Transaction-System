import java.io.*;
import java.util.HashMap;

public class Simulation {


    public static SnackShop initialiseShop(String shopName , File productFile, File customerFile){
        SnackShop snackShop = new SnackShop(shopName);
        try{
            BufferedReader productsFile = new BufferedReader(new FileReader(productFile));
            BufferedReader customersFile = new BufferedReader(new FileReader(customerFile));

            // creating products for the Snack shop

            String productInfo;

            while ((productInfo = productsFile.readLine()) != null){
                String [] info = productInfo.split("@");
                try{
                    snackShop.addProduct(info[0], info[1], info[2], Integer.parseInt(info[3]));
                }
                catch (InvalidProductException e){
                    System.err.println(e + "Product ID: " + info[0]);
                }

            }

            // Creating Customer for the Snack Shop

            String customerInfo;

            while ((customerInfo = customersFile.readLine()) != null){
                String[] info = customerInfo.split("#");
                try {
                    if (info.length == 5){
                        snackShop.addCustomer(info[0],info[1],Integer.parseInt(info[2]), info[3],info[4]);
                    }
                    else if (info.length == 4){
                        snackShop.addCustomer(info[0], info[1], Integer.parseInt(info[2]), info[3]);
                    }
                    else if (info.length == 3){
                        snackShop.addCustomer(info[0], info[1],Integer.parseInt(info[2]));
                    }
                }
                catch (InvalidCustomerException e){
                    System.err.println(e + " | Customer ID: " + info[0]);
                }
            }
        }
        catch (FileNotFoundException e){
            System.err.println("File Not Found");
        } catch (IOException e) {
            System.err.println(e + "IOException");
        }
        // print out a statement to instantiate when the transactions begin. Simply for aesthetics
        return snackShop;
    }

    public static void simulateShopping(SnackShop shop, File transactionFile){
        try {
            BufferedReader transactionsFile = new BufferedReader( new FileReader(transactionFile));
            String transactions;
            while ((transactions = transactionsFile.readLine()) != null){
                String[] info = transactions.split(",");
                // info[0] = command i.e PURCHASE, NEW_CUSTOMER OR ADD_FUNDS,
                // info[1] = Customer ID
                // info[2] = Product ID
                try {
                    switch (info[0]) {
                        case "PURCHASE":
                            shop.processPurchase(info[1], info[2]);
                            System.out.println("Transaction successful customerID: " + info[1] +", productID: "+ info[2]);
                            break;
                        case "ADD_FUNDS":
                            shop.getCustomer(info[1]).addFunds(Integer.parseInt(info[2]));
                            System.out.println("Successfully added " + info[2] +"p funds" + " to Customer of customerID: " + info[1]);
                            break;
                        case "NEW_CUSTOMER":
                            if (info.length == 6) {
                                shop.addCustomer(info[1], info[2], Integer.parseInt(info[5]), info[3], info[4]);
                                System.out.println("successfully created customer with ID: " + info[1]);
                            } else if (info.length == 5) {
                                shop.addCustomer(info[1], info[2], Integer.parseInt(info[4]), info[3]);
                                System.out.println("successfully created customer with ID: " + info[1]);
                            } else if (info.length == 4) {
                                shop.addCustomer(info[1], info[2], Integer.parseInt(info[3]));
                                System.out.println("successfully created customer with ID: " + info[1]);
                            }
                            break;
                    }
                } catch (InvalidCustomerException e) {
                    System.err.println("Customer Not Found In Shop's Database");
                } catch (InvalidProductException e) {
                    System.err.println("product not found In Shop's Database") ;
                }
            }
        }
        catch (FileNotFoundException e){
            System.err.println("File Not Found");
        }catch (IOException e){
            System.err.println(e + "IOException");
        }
    }

    public static void main(String[] args) throws InvalidProductException, InvalidCustomerException {
        File openProductFile = new File("products.txt");
        File openCustomerFile = new File("customers.txt");
        File openTransactionFile = new File("transactions.txt");
        SnackShop shop = initialiseShop("Tesco", openProductFile,openCustomerFile);
        System.out.println("------Transactions Begin-----------");
        simulateShopping(shop, openTransactionFile);
        System.out.println("The largest base product price: " + shop.findLargestBasePrice());
        System.out.println("The Number of negative balances: " + shop.countNegativeAccounts());
        System.out.println("The Median customer balance: " + shop.calculateMedianCustomerBalance());
        System.out.println(shop.getShopName() + " turnover: " + shop.getTurnover() + "p");

    }
}
