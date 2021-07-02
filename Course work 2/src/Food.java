public class Food extends Product {
    // Fields
    protected boolean isHotFood;

    //Constructor
    public Food(String productId, String name, int price, boolean isHotFood) throws InvalidProductException{
        super(productId, name, price);
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.isHotFood = isHotFood;

        /* Exception Handling */
        //checks the start tag of the product ID
        if (productId.indexOf("F-") != 0){
            throw new InvalidProductException("Invalid Product ID Tag in " +getName());
        }
        // checks the numeric part of the product ID to ensure they are all numbers
        isNumeric(productId.substring(2,9));
    }

    // Accessor Methods
    public boolean getIsHotFood(){
        return isHotFood;
    }

    // Methods
    @Override
    public int calculatePrice(){
        if (isHotFood){
            return (int) Math.round((double)price * 1.1);
        }
        else{
            return price;
        }
    }
    @Override
    public String toString() {
        return "Product ID: " + getProductId() + ", Name: " + getName() + ", Price: " + calculatePrice() + "p, IsHotFood: " + getIsHotFood();
    }

    public static void main(String[] args) throws InvalidProductException {
        Food rice = new Food("F-1000000", "Rice", 500, true);
        Food bread = new Food("F-1000001", "Bread", 110, false);
        Food milk = new Food("F-1000009", "Milk", 110, false);
        System.out.println(rice);
        System.out.println(bread);
        System.out.println(milk);
    }
}
