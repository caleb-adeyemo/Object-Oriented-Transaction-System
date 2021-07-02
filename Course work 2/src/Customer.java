public class Customer {
    // Fields
    String accountId;
    String name;
    int accountBalance;


    // Constructor
    public Customer(String accountId, String name) throws InvalidCustomerException {
        this.accountId = accountId;
        this.name =name;
        this.accountBalance = 0;
        // checks to ensure the accountId is the right length
        if (String.valueOf(accountId).length() != 6){
            throw new InvalidCustomerException("Invalid Account ID Length");
        }
        // checks the numeric part of the product ID to ensure they are all numbers
        isNumeric(String.valueOf(accountId));
    }

    public Customer(String accountId, String name, int accountBalance) throws  InvalidCustomerException{
        this.accountId = accountId;
        this.name = name;
        this.accountBalance = accountBalance;

        // Exception Handling
        if (String.valueOf(accountId).length() != 6){
            throw new InvalidCustomerException("Invalid Account ID Length");
        }
        else if (accountBalance < 0){
            throw new InvalidCustomerException("Account Balance Can Not Be Negative");
        }
    }

    // Accessor Methods
    public String getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    // Methods
    public void addFunds(int amount){
        if (amount > 0){
            this.accountBalance = accountBalance + amount;
        }
        else{
            System.out.println("Amount must be greater than 0.");
        }
    }

    public int chargeAccount(int productPrice) throws InsufficientBalanceException{
        if (accountBalance >= productPrice){
            this.accountBalance = accountBalance - productPrice;
            return productPrice;
        }
        else{
            throw new InsufficientBalanceException("Insufficient Amount");
        }

    }

    /* This method checks to see if the last 7 digits of the customer ID are all numbers*/
    public void isNumeric(String stringNum) throws InvalidCustomerException {
        if (stringNum == null) {
            throw new InvalidCustomerException("7 Digits Not Provided");
        }
        try {
            Integer.parseInt(stringNum);
        } catch (NumberFormatException nfe) {
            throw new InvalidCustomerException("One/More Values Is Not a Number");
        }
    }


    @Override
    public String toString() {
        return "Account ID: " + getAccountId() + ", Name: " + getName() + ", Account Balance: " + getAccountBalance();
    }

    // Main
    public static void main(String[] args) throws Exception{
        Customer caleb = new Customer("54000A", "Caleb", 500);
        Customer mike = new Customer("540100", "Mike");
        Customer bill = new Customer("540200", "Bill", 6000);
        System.out.println(caleb);
        System.out.println(mike);
        //mike.chargeAccount(200);
        System.out.println(bill);
        caleb.chargeAccount(200);
    }
}

