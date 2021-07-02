public class StaffCustomer extends Customer{
    // Fields
    enum sch{
        CMP,
        BIO,
        MTH,
        OTHERS
    }
    sch school;

    // Constructor
    public StaffCustomer(String accountId, String name, sch school) throws InvalidCustomerException {
        super(accountId, name);
        this.accountId = accountId;
        this.name = name;
        this.school = school;
        this.accountBalance = 0;
    }
    public StaffCustomer(String accountId, String name, sch school, int accountBalance) throws InvalidCustomerException {
        super(accountId, name, accountBalance);
        this.accountId = accountId;
        this.name = name;
        this.accountBalance = accountBalance;
        this.school = school;
    }

    // Methods
    @Override
    public int chargeAccount(int productPrice) throws InsufficientBalanceException{
        if ((school == sch.CMP) && (accountBalance >= (productPrice-(0.1 * productPrice)))){
            this.accountBalance = (int) Math.round(accountBalance - (productPrice-(0.1 * productPrice)));
            return (int)Math.round(accountBalance - (productPrice-(0.1 * productPrice)));
        }
        else if((school == sch.BIO) && (accountBalance >= (productPrice-(0.025 * productPrice)))){
            this.accountBalance = (int) Math.round(accountBalance - (productPrice-(0.025 * productPrice)));
            return (int) Math.round(accountBalance - (productPrice-(0.025 * productPrice)));
        }
        else if ((school == sch.MTH) && (accountBalance >= (productPrice-(0.025 * productPrice)))){
            this.accountBalance = (int) Math.round(accountBalance - (productPrice-(0.025 * productPrice)));
            return (int) Math.round(accountBalance - (productPrice-(0.025 * productPrice)));
        }
        else if((school == sch.OTHERS) && (accountBalance >= productPrice)){
            this.accountBalance = (accountBalance - productPrice);
            return productPrice;
        }
        else {
            throw new InsufficientBalanceException("Not Enough Money In " + getName() + "'s Account");
        }

    }
    @Override
    public String toString() {
        return "Staff Account ID: " + getAccountId() + ", Staff Name: " + getName() + ", Staff Account Balance: " + getAccountBalance();
    }

    public static void main(String[] args) throws InvalidCustomerException, InsufficientBalanceException{
        StaffCustomer teacher1 = new StaffCustomer("200100", "Teacher1",sch.CMP);
        StaffCustomer teacher2 = new StaffCustomer("200200", "Teacher2", sch.CMP, 2000);
        StaffCustomer teacher3 = new StaffCustomer("200300", "Teacher3", sch.MTH, 3000);
        StaffCustomer teacher4 = new StaffCustomer("200400", "Teacher4", sch.BIO, 4000);
        StaffCustomer teacher5 = new StaffCustomer("200500", "Teacher5", sch.OTHERS, 5000);
        System.out.println(teacher1);
        System.out.println(teacher2);
        System.out.println(teacher3);
        System.out.println(teacher4);
        System.out.println(teacher5);

        // Teacher 5
        teacher5.chargeAccount(4000);
        System.out.println("Teacher5 Account Balance: " + teacher5.getAccountBalance());

        // Teacher 1
        teacher1.chargeAccount(0);
        System.out.println("Teacher1 Account Balance: " + teacher1.getAccountBalance());

        // Teacher 2
        teacher2.chargeAccount(1000);
        System.out.println("Teacher2 Account Balance: " + teacher2.getAccountBalance());

        //Teacher3
        teacher3.chargeAccount(1000);
        System.out.println("Teacher3 Account Balance: " + teacher3.getAccountBalance());
        teacher3.addFunds(1000);
        System.out.println("Teacher3 Account Balance: " + teacher3.getAccountBalance());
        teacher3.chargeAccount(3000);
        System.out.println("Teacher3 Account Balance: " + teacher3.getAccountBalance());
    }
}
