import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SnackShop {
    // Fields
    private final String shopName;
    private double turnover = 0;
    private final HashMap<String, Customer> shopCustomers = new HashMap<>();
    private final HashMap<String, Product> shopProducts = new HashMap<>();

    // Constructor
    public SnackShop(String shopName){
        this.shopName = shopName;
    }

    // Accessor Methods
    public String getShopName() {
        return shopName;
    }

    public double getTurnover() {
        return turnover;
    }

    // Methods
    // Add customers
    //Customers with no specified title (i.e staff) and no school (i.e CMP)
    public void addCustomer(String accountId, String name, int accountBalance) throws InvalidCustomerException{
        shopCustomers.put(accountId, new StudentCustomer(accountId, name, accountBalance));
    }
    // Customers with specified title ONLY (i.e Student/staff)
    public void addCustomer(String accountId, String name, int accountBalance, String title) throws InvalidCustomerException{
        if (title.equals("STAFF")){
            shopCustomers.put(accountId, new StaffCustomer(accountId, name,StaffCustomer.sch.OTHERS,accountBalance));
        }
        else if (title.equals("STUDENT")){
            shopCustomers.put(accountId, new StudentCustomer(accountId, name, accountBalance));
        }
    }
    public void addCustomer(String accountId, String name, int accountBalance, String title, String school) throws InvalidCustomerException{
        // Title not relevant in adding customers into Database
        switch (school) {
            case "MTH":
                shopCustomers.put(accountId, new StaffCustomer(accountId, name, StaffCustomer.sch.MTH, accountBalance));
                break;
            case "CMP":
                shopCustomers.put(accountId, new StaffCustomer(accountId, name, StaffCustomer.sch.CMP, accountBalance));
                break;
            case "BIO":
                shopCustomers.put(accountId, new StaffCustomer(accountId, name, StaffCustomer.sch.BIO, accountBalance));
                break;
        }
    }
    // Get Customers
    public Customer getCustomer(String customerId) throws InvalidCustomerException{
        if (shopCustomers.containsKey(customerId)){
            return shopCustomers.get(customerId);
        }
        else {
            throw new InvalidCustomerException("Customer not found in Database.");
        }
    }


    // Add Products
    public void addProduct(String productId, String name, String sort, int price ) throws InvalidProductException {
        switch (sort) {
            case "cold":
                shopProducts.put(productId, new Food(productId, name, price, false));
                break;
            case "hot":
                shopProducts.put(productId, new Food(productId, name, price, true));
                break;
            case "low":
                shopProducts.put(productId, new Drink(productId, name, price, Drink.sugarLevel.LOW));
                break;
            case "high":
                shopProducts.put(productId, new Drink(productId, name, price, Drink.sugarLevel.HIGH));
                break;
            case "none":
                shopProducts.put(productId, new Drink(productId, name, price, Drink.sugarLevel.NONE));
                break;
        }
    }
    // Get Products
    public Product getProduct(String productId) throws InvalidProductException {
        if (shopProducts.containsKey(productId)){
            return shopProducts.get(productId);
        }
        else {
            throw new InvalidProductException("Product Not found in shop Database");
        }
    }

    public void processPurchase(String customerId, String productId) throws InvalidCustomerException, InvalidProductException{
        try{
            getCustomer(customerId).chargeAccount(getProduct(productId).calculatePrice());
            turnover = turnover + getProduct(productId).calculatePrice();

        }
        catch (InsufficientBalanceException e){
            System.err.println("Insufficient Amount");
        }
    }
    public int findLargestBasePrice() throws InvalidProductException {
        ArrayList<Integer> prices = new ArrayList<>();
        int largestNumber = 0;

        for (String code : shopProducts.keySet()){
            prices.add(getProduct(code).getPrice());
        }
        for (Integer price : prices) {
            if (price > largestNumber) {
                largestNumber = price;
            }
        }
        return largestNumber;
    }
    public int countNegativeAccounts() throws InvalidCustomerException{
        ArrayList<Integer> balances = new ArrayList<>();
        int negativeAccounts = 0;
        for (String code : shopCustomers.keySet()){
            balances.add(getCustomer(code).accountBalance);
        }
        for (Integer balance : balances){
            if (balance < 0 ){
                negativeAccounts++;
            }
        }
        return negativeAccounts;
    }
    public double calculateMedianCustomerBalance() throws InvalidCustomerException {
        ArrayList<Integer> set = new ArrayList<>();
        for (String code : shopCustomers.keySet()){
            set.add(getCustomer(code).accountBalance);
        }
        Collections.sort(set);
        double value = 0;
        if (set.size() % 2 == 1 && set.size() != 1){
            value = set.get(((set.size() + 1) / 2) -1);
        }
        else if (set.size() % 2 == 0){
            value = (set.get((set.size())/2) + set.get(((set.size())/2) - 1))/2.0;
        }
        else if (set.size() == 1){
            value = set.get(0);
        }
        return value;
    }

    public static void main(String[] args) throws InvalidCustomerException, InvalidProductException {
        SnackShop tesco = new SnackShop("Tesco");
        //Customers
        tesco.addCustomer("5000A4", "Caleb Adeyemo",4000);
        tesco.addCustomer("5000A3", "Koyin", 4000, "STUDENT");
        // Add drinks

        // Add food
        tesco.addProduct("F-1234567", "Rice", "hot",500); // Rice
        tesco.addProduct("F-1234566", "Best Food In The World", "cold", 4000); // Over Priced food
        tesco.addProduct("D-1234565", "Milkshakes", "none",4500); // Milkshakes
        // Get Results

        // Transactions
        // Caleb
        tesco.processPurchase("5000A4", "F-1234567");
        tesco.processPurchase("5000A4", "F-1234567");
        tesco.processPurchase("5000A4","F-1234566" );
        // Koyin
        tesco.processPurchase("5000A3", "D-1234565");

        System.out.println(tesco.getCustomer("5000A3").getName() + "'s Account Balance: " + tesco.getCustomer("5000A3").accountBalance);
        System.out.println(tesco.getTurnover());
        System.out.println(tesco.getShopName());
        System.out.println(tesco.findLargestBasePrice());
        System.out.println(tesco.countNegativeAccounts());
        System.out.println(tesco.calculateMedianCustomerBalance());
    }

}
