public class StudentCustomer extends Customer{
    // Constructor
    public StudentCustomer(String accountId, String name) throws InvalidCustomerException {
        super(accountId, name);
        this.accountId = accountId;
        this.name = name;
        this.accountBalance = 0;
    }
    public StudentCustomer(String accountId, String name, int accountBalance) throws InvalidCustomerException {
        super(accountId, name, accountBalance);
        this.accountId = accountId;
        this.name = name;
        this.accountBalance = accountBalance;
    }

    // Methods
    @Override
    public int chargeAccount(int productPrice) throws InsufficientBalanceException{
        // checks to see if the student would have over -500p after the 5% discounted product is removed from the account balance
        if(((accountBalance) - (productPrice-(0.05 * productPrice))) >= -500){
            accountBalance = (int) Math.round(accountBalance - (productPrice-(0.05 * productPrice)));
            return (int) Math.round(productPrice-(0.05 * productPrice));
        }
        else{
            throw new InsufficientBalanceException("Insufficient Amount");
        }


    }
    @Override
    public String toString() {
        return "Student Account ID: " + getAccountId() + ", Student Name: " + getName() + ", Student Account Balance: " + getAccountBalance();
    }

    // Main
    public static void main(String[] args) throws InvalidCustomerException,InsufficientBalanceException{
        StudentCustomer lucas = new StudentCustomer("540200", "Lucas", 600);
        StudentCustomer alvina = new StudentCustomer("540300", "Alvina");
        System.out.println(lucas);
        System.out.println(alvina);
        lucas.addFunds(100);
        System.out.println("Lucas Account Balance: " + lucas.getAccountBalance());
        lucas.chargeAccount(10);
        System.out.println("Lucas Account Balance: " + lucas.getAccountBalance());
        lucas.chargeAccount(800);
        System.out.println("Lucas Account Balance: " + lucas.getAccountBalance());
        alvina.chargeAccount(200);
        System.out.println("Alvina Account Balance: " + alvina.getAccountBalance());
        alvina.chargeAccount(200);
        System.out.println("Alvina Account Balance: " + alvina.getAccountBalance());
        alvina.addFunds(200);
        System.out.println("Alvina Account Balance: " + alvina.getAccountBalance());
        alvina.chargeAccount(200);
        System.out.println("Alvina Account Balance: " + alvina.getAccountBalance());
    }
}

