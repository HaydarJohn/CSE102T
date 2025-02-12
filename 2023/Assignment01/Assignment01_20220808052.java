import java.util.ArrayList;

public class Assignment01_20220808052
{
    public static void main(String[] args) {
        Product seaSalt = new Product("00", "Sea Salt", 69, 69.69);
        Product artificialSalt = new Product("01", "Industrial Salt", 3, 69);
        System.out.println(seaSalt.equals(artificialSalt));
    }
}

class Product
{
    private String ID;
    private String Name;
    private int Quantity;
    private double Price;

    Product(String ID,String Name,int Quantity,double Price)
    {
        this.ID = ID;
        this.Name = Name;
        this.Quantity = Quantity;
        this.Price = Price;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public double getPrice() {
        return Price;
    }
    public void setPrice(double Price)
    {
        if(Price > 0)
        this.Price = Price;
    }
    public int remaining()
    {
        return Quantity;
    }

    public int addToInventory(int amount)
    {
        if(amount < 0)
        {
            return remaining();
        }
        Quantity += amount;
        return Quantity;
    }
    public double purchase(int amount)
    {
        if(amount < 0 || amount > remaining())
        {
            return 0;
        }
        Quantity -= amount;
        return amount * getPrice();
    }
    @Override
    public String toString()
    {
        return "Product " + getName() + " has " + remaining() + " remaining.";
    }

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Product)
        {
            return ((Product)o).getPrice() == this.getPrice();
        }

        return false;
    }

    
}

class FoodProduct extends Product
{
    private int Calories;
    private boolean Dairy;
    private boolean Eggs;
    private boolean Peanuts;
    private boolean Gluten;

    FoodProduct(String ID,String Name,int Quantity,double Price,int Calories,boolean Dairy,boolean Eggs,boolean Peanuts,boolean Gluten)
    {
        super(ID, Name, Quantity, Price);
        this.Calories = Calories;
        this.Dairy = Dairy;
        this.Eggs = Eggs;
        this.Gluten = Gluten;
        this.Peanuts = Peanuts;
    }
    public int getCalories() {
        return Calories;
    }
    public boolean containsDairy()
    {
        return Dairy;
    }
    public boolean containsEggs()
    {
        return Eggs;
    }
    public boolean containsGluten()
    {
        return Gluten;
    }
    public boolean containsPeanuts()
    {
        return Peanuts;
    }

}


class CleaningProduct extends Product
{
    private boolean Liquid;
    private String WhereToUse;
    
    CleaningProduct(String ID,String Name,int Quantity,double Price,boolean Liquid,String WhereToUse)
    {
        super(ID, Name, Quantity, Price);
        this.Liquid = Liquid;
        this.WhereToUse = WhereToUse;
    }
    public String getWhereToUse() {
        return WhereToUse;
    }
    public boolean isLiquid() {
        return Liquid;
    }
}

class Customer
{
    private String Name;
    Customer(String Name)
    {
        this.Name = Name;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    @Override
    public String toString()
    {
        return Name;
    }
}

class ClubCustomer extends Customer
{
    private String Phone;
    private int Points;
    
    ClubCustomer(String Name,String Phone ,int Points)
    {
        super(Name);
        this.Phone = Phone;
        this.Points = Points;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }
    public int getPoints() {
        return Points;
    }
    public void addPoints(int Points)
    {
        if(Points < 0)
        {
            return;
        }

        this.Points += Points;
    }
    @Override
    public String toString()
    {
        return this.getName() + " has " + this.getPoints() + " points.";
    }

}

class Store
{
    private String Name;
    private String Website;
    private ArrayList<Product> Inventory = new ArrayList<Product>();

    Store(String Name,String Website)
    {
        this.Name = Name;
        this.Website = Website;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getWebsite() {
        return Website;
    }
    public void setWebsite(String website) {
        Website = website;
    }
    public void addProduct(Product product,int index)
    {
        if(index < 0 || (Inventory.size()- 1) < index)
        {
            return;
        }
        Inventory.add(index, product);
    }
    public void addProduct(Product product)
    {
        Inventory.add(product);
    }
    public Product getProduct(int index) 
    {
        if(index < 0 || (Inventory.size() - 1) < index)
        {
            return null;
        }
        return Inventory.get(index);
    }

    public int getProductIndex(Product p)
    {
        if(Inventory.contains(p))
        {
            for (int i = 0; i < (Inventory.size() -1) ; i++) {
                if(p == Inventory.get(i))
                {
                    return i;
                }        
            }   
        }

        return -1;
            
    }

}

class CustomerNotFoundException extends IllegalArgumentException
{
    private String phone;

    public CustomerNotFoundException(String phone)
    {
        this.phone = phone;
    }

   @Override
   public String toString() 
   {
       return super.toString() + ": " + phone;
   }
}

class InsufficientFundsException extends RuntimeException
{
    private double total;
    private double payment;
    public InsufficientFundsException(double total,double payment)
    {
        this.total = total;
        this.payment = payment;
    }

    @Override
    public String toString() 
    {
        return super.toString() + ": " + total;
    }

}
