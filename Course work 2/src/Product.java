public abstract class Product {
    // Fields
    protected String productId;
    protected String name;
    protected int price;

    //Constructor
    public Product(String productId, String name, int price) throws InvalidProductException{
        this.productId = productId;
        this.name = name;
        this.price = price;

        // Exception Handling
        // checks to ensure the product ID has a length of 9 digits
        if (productId.length() != 9){
            throw new InvalidProductException("Invalid Product ID Length");
        }
        // checks to ensure the price is never a negative amount
        else if (price < 0){
            throw new InvalidProductException("Product Price Is Negative");
        }
    }

    // Methods
    public abstract int calculatePrice();

    /* This method checks to see if the last 7 digits of the product ID are all numbers*/
    protected void isNumeric(String stringNum) throws InvalidProductException {
        if (stringNum == null) {
            throw new InvalidProductException("7 Digits Not Provided");
        }
        try {
            Integer.parseInt(stringNum);
        } catch (NumberFormatException nfe) {
            throw new InvalidProductException("One/More Values Aren't Numbers");
        }
    }

    // Accessor Methods
    public String getProductId(){
        return productId;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
}
