public class Drink extends Product{
    // Fields
    enum sugarLevel{
        HIGH,
        LOW,
        NONE
    }
    sugarLevel level;

    //Constructor
    public Drink(String productId, String name, int price) throws InvalidProductException {
        super(productId, name, price);
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.level = sugarLevel.LOW;
        // Exception Handling
        if (productId.indexOf("D-") != 0){
            throw new InvalidProductException("Invalid Product ID Tag");
        }
        // checks to ensure the numeric part of the product ID are all numbers, this method is from the product.java class
        isNumeric(productId.substring(2,9));
    }
    public Drink(String productId, String name, int price , sugarLevel level) throws InvalidProductException {
        super(productId, name, price);
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.level = level;

        // Exception Handling
        if (productId.indexOf("D-") != 0){
            throw new InvalidProductException("Invalid Product ID Tag");
        }
        // checks to ensure the numeric part of the product ID are all numbers
        isNumeric(productId.substring(2,9));
    }

    //Accessor Methods
    public sugarLevel getSugarLevel(){
        return level;
    }
    //Methods
    @Override
    public int calculatePrice(){
        if (level == sugarLevel.HIGH){
            return (price + 24);
        }
        else if(level == sugarLevel.LOW){
            return (price + 18);
        }
        else {
            return price;
        }
    }

    @Override
    public String toString() {
        return "Product ID: " + getProductId() + ", Name: " + getName() + ", Price: " + calculatePrice() + "p, Sugar Level: " + getSugarLevel();
    }

    public static void main(String[] args) {
        try {
            Drink fizzy = new Drink("D-0000002", "Fizzy-Drink", 250,sugarLevel.HIGH);
            System.out.println(fizzy);
            Drink fijiWater = new Drink("D-0000003", "Fiji-Water", 200);
            System.out.println(fijiWater);
            // product ID for water has 6 characters rather than 7. Therefore throws an exception
            Drink water = new Drink("D-000000", "Water", 100, sugarLevel.NONE);
            System.out.println(water);
            // Unsure of why the code breaks at the exception rather than continue this part of the code
            Drink tonicWater = new Drink("D-0000004", "Tonic Water", 200);
            System.out.println(tonicWater);
        }
        catch (InvalidProductException e){
            System.err.println(e + "");
        }
    }
}
