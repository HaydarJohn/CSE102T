import java.util.ArrayList;

import javax.crypto.SealedObject;
import javax.lang.model.type.NullType;

public class Assignment02_20220808052
{
    public static void main(String[] args) {
       Product aaa = new Product(5L, "hello", 5, 5.55)
    }
}

class Product
{
    private Long ID;
    private String Name;
    private int Quantity;
    private double Price;

    Product(Long ID,String Name,int Quantity,double Price)
    {
        this.ID = ID;
        this.Name = Name;
        if(Quantity < 0)
        {
            throw new InvalidAmountException(Quantity);
        }
        this.Quantity = Quantity;
        if(Price < 0)
        {
            throw new InvalidPriceException(Price);
        }
        this.Price = Price;
    }
    public Long getID() {
        return ID;
    }
    public void setID(Long ID) {
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
    public void setPrice(double Price) {
        if(Price < 0)
        {
            throw new InvalidPriceException(Price);
        }
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
            throw new InvalidAmountException(amount);
        }
        Quantity += amount;
        return Quantity;
    }
    public double purchase(int amount)
    {
        if(amount < 0)
        {
            throw new InvalidAmountException(amount);
        }
        else if(amount > Quantity)
        {
            throw new InvalidAmountException(amount, remaining());
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

    FoodProduct(Long ID,String Name,int Quantity,double Price,int Calories,boolean Dairy,boolean Eggs,boolean Peanuts,boolean Gluten)
    {
        super(ID, Name, Quantity, Price);
        if(Calories < 0)
        {
            throw new InvalidAmountException(Calories);
        }
        this.Calories = Calories;
        this.Dairy = Dairy;
        this.Eggs = Eggs;
        this.Gluten = Gluten;
        this.Peanuts = Peanuts;
    }
    public int getCalories() {
        return Calories;
    }
    public void setCalories(int calories) {
        if(calories < 0)
        {
            throw new InvalidAmountException(calories);
        }
        Calories = calories;
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
    
    CleaningProduct(Long ID,String Name,int Quantity,double Price,boolean Liquid,String WhereToUse)
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
    private double costOfItems = 0;
    private ArrayList<Product> products;
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
    public void addToCart(Product product,int count)
    {
        try 
        {
            costOfItems += product.purchase(count);
            products.add(new Product(product.getID(),product.getName(), count, product.getPrice()));

        } 
        catch (InvalidAmountException e) {
            System.out.println("Error: ");
        }
    }

    public String receipt()
    {
        String returnValue = "";
        for(int i = 0; (i < products.size() -1);i++)
        {
            returnValue += products.get(i).getName() + " - " + products.get(i).getPrice() + " X " + products.get(i).remaining() + " = " + products.get(i).getPrice() * products.get(i).remaining() +"\n";
        }
        return returnValue + "Total is: " + costOfItems;
    }
    public double getTotalDue() {
        return costOfItems;
    }
    public double pay(double amount)
    {
        if(amount < getTotalDue())
        {
            throw new InsufficientFundsException(amount, getTotalDue());
        }
        System.out.println("Thank you");
        double placeHolder = costOfItems;
        products = new ArrayList<Product>();
        costOfItems = 0;
        return amount - costOfItems;

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
    private double costOfItems = 0;
    private ArrayList<Product> products;
    
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
    public double pay(double amount,boolean usePoints)
    {
        if(amount < getTotalDue())
        {
            throw new InsufficientFundsException(amount, getTotalDue());
        }
        if(usePoints)
        {
            if(costOfItems <= (Points * 0.01))
            {
                costOfItems = 0;
                Points -= (int)(costOfItems * 100);
            }
            else
            {
                costOfItems -= Points *0.01;
                Points = 0;
            }
            
        }
        System.out.println("Thank you");
        double placeHolder = costOfItems;
        products = new ArrayList<Product>();
        costOfItems = 0;
        return amount - costOfItems;

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
        return super.toString() + ": " + total + " due,but only " + payment + " given";
    }

}

class InvalidAmountException extends RuntimeException
{
    private int amount;
    private int quantity;
    private boolean isQuantityAssingned = false;

    public InvalidAmountException(int amount)
    {
        this.amount = amount;
    }
    public InvalidAmountException(int amount,int quantity)
    {
        this.amount = amount;
        this.quantity = quantity;
        isQuantityAssingned = true;
    }

    @Override
    public String toString() 
    {
        if(isQuantityAssingned)
        {
            return super.toString() +  ": " + amount;
        }
        else
        {
            return super.toString() + ": "+ amount + " was requested,but only " + quantity + " remaining";
        }
    }
}


class InvalidPriceException extends RuntimeException
{
    private double price;
    InvalidPriceException(double price)
    {
        this.price =price;
    }

    @Override
    public String toString() 
    {
        return super.toString() + ": " + price;
    }
}

class ProductNotFoundException extends IllegalArgumentException
{
    private Long ID;
    private String Name;
    private boolean isAssigned = false;
    public ProductNotFoundException(Long ID)
    {
        this.ID = ID;
    }
    public ProductNotFoundException(Long ID,String Name)
    {
        this.ID = ID;
        this.Name = Name;
        isAssigned = true;
    }

    @Override
    public String toString() 
    {
        if(isAssigned)
        {
            return super.toString() + ": " + Name;
        }
        return super.toString() + ": " + ID;
    }

}


