
//Aleena Siddiqui
//501101812

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{



	private Map<String , Integer>  stats = new TreeMap<String , Integer>();
	private Map<String , Product>  products = new TreeMap<String , Product>();
	private ArrayList<Customer> customers = new ArrayList<Customer>();	
	private ArrayList<Cart> cart = new ArrayList <Cart>();	
	private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
	private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();

	// These variables are used to generate order numbers, customer id's, product id's 
	private int orderNumber = 500;
	private int customerId = 900;
	private int productId = 700;

	// General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
	String errMsg = null;

	// Random number generator
	Random random = new Random();

	public ECommerceSystem()
	{
		// NOTE: do not modify or add to these objects!! - the TAs will use for testing
		// If you do the class Shoes bonus, you may add shoe products
		// Create some products. Notice how generateProductId() method is used


		products = ProductsFromFile("C:\\Users\\aleen\\eclipse-workspace\\ComputerScience\\CPS209\\bin\\Assignment_1\\products.txt");
		///Users/aleen/eclipse-workspace/ComputerScience/CPS209/src/Assignment_1/products.txt

		// Create some customers. Notice how generateCustomerId() method is used
		customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
		customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
		customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
		customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));
	}

	private Map<String, Product> ProductsFromFile(String filename) 
	{ 

		Map<String, Product> products = new TreeMap<String, Product>();
		String ProductType = "", ProductName="", prodInfo = "";
		int stock = 0;
		double price = 0;
		String author = "", bookName = "";
		int HardcoverStock = 0;
		int PaperStock =0;
		int count = 0;
		try
		{


			FileInputStream fis = new FileInputStream(filename);
			Scanner in =new Scanner(fis); //file to be scanned
			//returns true if there is another line to read
			while(in.hasNextLine())
			{
				String temp = in.nextLine();
				count++;
				if (count == 1) 
				{


					ProductType = temp;
				}
				if(count == 2) 
				{
					ProductName = temp;
				}
				if(count == 3) 
				{
					price = Double.parseDouble(temp);
				}
				if(count == 4) 
				{

					if(ProductType.equalsIgnoreCase("BOOKS") == false ) 
					{
						stock = Integer.parseInt(temp);
					}
					else if (ProductType.equalsIgnoreCase("BOOKS") == true ) 
					{
						String s1 =temp.substring(0,1);
						String s2 = temp.substring(2);	
						PaperStock= Integer.parseInt(s1);
						HardcoverStock = Integer.parseInt(s2);
					}
				}
				if(count == 5) 
				{
					prodInfo = temp;
					if (!ProductType.equals("BOOKS") && !ProductType.equals("SHOES")) 
					{
						String id = generateProductId();
						if(ProductType.equals("COMPUTERS"))
							products.put(id,new Product(ProductName, id, price,  stock,Product.Category.COMPUTERS));
						if(ProductType.equals("GENERAL"))
							products.put(id,new Product(ProductName,id,price, stock,Product.Category.GENERAL));
						if(ProductType.equals("CLOTHING"))
							products.put(id,new Product(ProductName,id, price, stock,Product.Category.CLOTHING));
						if(ProductType.equals("FURNITURE"))
							products.put(id,new Product(ProductName,id, price, stock,Product.Category.FURNITURE));
						if(ProductType.equals("SHOES"))
							products.put(id,new Product(ProductName,id, price,stock,Product.Category.SHOES));

					}
					else if (ProductType.equals("BOOKS") || ProductType.equals("SHOES")) 
					{

						Scanner scan = new Scanner(temp);
						scan.useDelimiter(":");
						int index = 0;
						while(scan.hasNext()) 
						{ index++;
						String t = scan.next();
						if(index ==1) 
							bookName = t;

						else if(index ==2) 
							author = t;

						}
						String id = generateProductId();
						products.put(id,new Book(ProductName, id, price, PaperStock,HardcoverStock ,bookName, author));
					}
					count = 0;
				}

			}
			in.close();
		}
		catch(IOException e) 
		{
			System.out.println(e.getLocalizedMessage());
			System.exit(1);
		}
		return products;





	}
	private String generateOrderNumber()
	{
		return "" + orderNumber++;
	}
	private String generateCustomerId()
	{
		return "" + customerId++;
	}
	private String generateProductId()
	{
		return "" + productId++;
	}
	public String getErrorMessage()
	{
		return errMsg;
	}
	public void printAllProducts()
	{
		Set<String> keySet = products.keySet();
		for (String key : keySet)
		{
			Product value =products.get(key);
			value.print();
		}
	}
	// Print all products that are books. See getCategory() method in class Product
	public void printAllBooks()
	{
		Set<String> keySet = products.keySet();
		for (String key : keySet)
		{
			Product value =products.get(key);
			if(value.getCategory().equals(Product.Category.BOOKS))
			{
				value.print();	
			}

		}

	}

	// Print all current orders
	public void printAllOrders()
	{
		for (ProductOrder p: orders) 
		{
			p.print();
		}
	}
	// Print all shipped orders
	public void printAllShippedOrders()
	{
		for (ProductOrder p: shippedOrders ) 
		{
			p.print();
		}


	}
	// Print all customers
	public void printCustomers()
	{ 
		for (Customer c : customers ) {
			c.print();
		}
	}
	/*
	 * Given a customer id, print all the current orders and shipped orders for them (if any)
	 */
	public void printOrderHistory(String customerId)
	{
		// Make sure customer exists - check using customerId
		// If customer does not exist, set errMsg String and return false
		// see video for an appropriate error message string
		// ... code here
		if (customerId==null ||customerId.equals("") ) 
		{
			throw new UnknownCustomerException("Customer not found");
		}
		Customer custs = null;
		boolean pFound = false;
		boolean cFound = false;
		for (Customer c: customers) 
		{
			if(c.getId().equals(customerId))
				cFound = true;
			break;
		}
		for (ProductOrder p : orders) 
		{
			if(p.getCustomer().getId().equals(customerId)) 
			{
				custs=p.getCustomer();
				pFound = true;
				break;
			}
		}
		if (pFound == false && cFound == false) 
		{
			throw new UnknownCustomerException("Customer not found");
		}
		if(cFound == true && pFound == false) 
		{
			throw new InvalidOrderNumberException("Customer has no order ");
		}

		// Print current orders of this customer 
		System.out.println("Current Orders of Customer " + customerId);
		// enter code here
		for (ProductOrder p: orders) 
		{
			if(p.getCustomer().equals(custs)) {
				p.print();}
		}
		// Print shipped orders of this customer 
		System.out.println("\nShipped Orders of Customer " + customerId);
		//enter code here
		for (ProductOrder p: shippedOrders) 
		{
			if(p.getCustomer().equals(custs)) 
			{
				p.print();
			}
		}
	}

	public String orderProduct(String productId, String customerId, String productOptions)
	{
		Customer custs = null;
		Product prods = null;
		// First check to see if customer object with customerId exists in array list customers
		// if it does not, set errMsg and return null (see video for appropriate error message string)
		// else get the Customer object
		boolean cFound = false, pFound = false ;
		for (Customer c : customers ) 
		{
			if(c.getId().equals(customerId)) 
			{
				custs = c;
				cFound = true;
				break;
			}
		}
		if (cFound == false) 
		{

			throw new UnknownCustomerException("Customer not found");

		}
		// Check to see if product object with productId exists in array list of products
		// if it does not, set errMsg and return null (see video for appropriate error message string)
		// else get the Product object 
		Set<String> keySet = products.keySet();
		for (String key : keySet)
		{
			Product value =products.get(key);
			if(value.getId().equals(productId))
			{
				prods = value;
				pFound= true;
				if((prods.getCategory().equals(Product.Category.BOOKS) && productOptions== "")||(prods.getCategory().equals(Product.Category.SHOES) && productOptions== ""))
					pFound= false;
				break;
			}

		}
		if(pFound==false) 
		{
			throw new UnknownProductException ("Product id "+productId+" not found");
		}
		// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
		// See class Product and class Book for the method vaidOptions()
		// If options are not valid, set errMsg string and return null;
		if(prods.getCategory().equals(Product.Category.BOOKS)||prods.getCategory().equals(Product.Category.SHOES)) 
		{
			if(!prods.validOptions(productOptions))
			{
				throw new InvalidProductOptionsException("Product option " +productOptions+" is not valid");
			}
		}
		// Check if the product has stock available (i.e. not 0)
		// See class Product and class Book for the method getStockCount()
		// If no stock available, set errMsg string and return null
		if(prods.getStockCount(productId)<=0) 
		{
			throw new ProductOutOfStockException("No Stock available");
		}
		// Create a ProductOrder, (make use of generateOrderNumber() method above)
		// reduce stock count of product by 1 (see class Product and class Book)
		// Add to orders list and return order number string
		String orderNumber= generateOrderNumber();
		ProductOrder order = new ProductOrder(orderNumber,prods, custs, productOptions);
		prods.reduceStockCount(productOptions);
		orders.add(order);
		return orderNumber; // replace this line
	}
	/*
	 * Create a new Customer object and add it to the list of customers
	 */

	public void AddToCart( String productId, String customerId, String productOptions, int quantity) 
	{
		Customer custs = null;
		Product prods = null;
		// First check to see if customer object with customerId exists in array list customers
		// if it does not, set errMsg and return null (see video for appropriate error message string)
		// else get the Customer object
		boolean cFound = false, pFound = false ;
		for (Customer c : customers ) 
		{
			if(c.getId().equals(customerId)) 
			{
				custs = c;
				cFound = true;
				break;
			}
		}
		if (cFound == false) 
		{
			throw new UnknownCustomerException("Customer not found");
		}
		// Check to see if product object with productId exists in array list of products
		// if it does not, set errMsg and return null (see video for appropriate error message string)
		// else get the Product object 


		Set<String> keySet = products.keySet();
		for (String key : keySet)
		{
			Product value =products.get(key);
			if(value.getId().equals(productId))
			{
				prods = value;
				pFound= true;
				if((prods.getCategory().equals(Product.Category.BOOKS) && productOptions== "")||(prods.getCategory().equals(Product.Category.SHOES) && productOptions== ""))
					pFound= false;
				break;
			}

		}

		if(pFound==false) 
		{
			throw new UnknownProductException ("Product id "+productId+" not found");
		}

		// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
		// See class Product and class Book for the method vaidOptions()
		// If options are not valid, set errMsg string and return null;

		if(prods.getCategory().equals(Product.Category.BOOKS)||prods.getCategory().equals(Product.Category.SHOES)) 
		{
			if(!prods.validOptions(productOptions))
			{
				throw new InvalidProductOptionsException("Product option " +productOptions+" is not valid");

			}
		}
		// Check if the product has stock available (i.e. not 0)
		// See class Product and class Book for the method getStockCount()
		// If no stock available, set errMsg string and return null
		if(prods.getStockCount(productId)<=0) 
		{
			throw new ProductOutOfStockException("No Stock available");

		}

		//ADD TO CARTITEM
		String ordernumber= orderProduct(productId, customerId, productOptions);
		CartItems item = new CartItems(prods, productOptions, quantity);
		Cart c = new Cart(ordernumber, custs, item);
		cart.add(c);
	}
	public void PrintCart( String customerId) 
	{
		System.out.println("Current items in the cart for Customer:"+ customerId);
		boolean cFound = false;
		for (Cart c : cart) 
		{
			if(c.getCustomer().getId().equals(customerId)) 
			{
				c.output();
				cFound = true;
			}
		}
		if (cFound== false) 
		{
			throw new UnknownCustomerException("Customer not found");

		}
	}
	public void OrderItems(String customerId) 
	{
		double sum = 0;
		boolean cFound = false;
		for (Cart c : cart) 
		{
			if(c.getCustomer().getId().equals(customerId)) 
			{
				c.print();
				cFound = true;
				ProductOrder order = new ProductOrder(c.getOrderNumber(),c.getItems().getProds(), c.getCustomer(), c.getItems().getProductOptions());
				c.getItems().getProds().reduceStockCount(c.getItems().getProductOptions());
				orders.add(order);
				sum = sum + c.getItems().getProds().getPrice();
				calculateStats(c.getItems().getProds().getId());
				//cart.remove(c);

			}
		}
		if (cFound== false) 
		{
			throw new UnknownCustomerException("Customer not found");

		}

		System.out.println("Your total is: $"+sum);
	}

	public void EmptyCart(String customerID) 
	{
		for (Cart c2 : cart) 
		{
			if(c2.getCustomer().getId().equals(customerID)) 
			{
				cart.remove(c2);
			}

		}
	}

	public void RemCartItem(String customerId, String productId) 
	{
		boolean cFound = false, pFound = false ;
		for (Customer c : customers ) 
		{
			if(c.getId().equals(customerId)) 
			{
				cFound = true;
				break;
			}
		}
		if (cFound == false) 
		{
			throw new UnknownCustomerException("Customer not found");
		}


		Set<String> keySet = products.keySet();
		for (String key : keySet)
		{
			Product value =products.get(key);
			if(value.getId().equals(productId))
			{
				pFound= true;
				break;
			}

		}
		if(pFound==false) 
		{
			throw new UnknownProductException ("Product id "+productId+" not found");
		}
		for (Cart c : cart) 
		{
			if(c.getItems().getProds().getId().equals(productId)) 
			{
				if(c.getItems().getCount()>1) 
				{
					c.getItems().setCount((c.getItems().getCount()-1));
					break;
				}
				else 
				{
					cart.remove(c);
					break;
				}

			}
		}
	}


	public void createCustomer(String name, String address)
	{
		if (name.equals("") || name.equals(null)) 
		{
			throw new InvalidCustomerNameException("Invalid Customer Name");
		}
		if (address.equals("") || address.equals(null))
		{
			throw new InvalidCustomerAddressException("Invalid Customer Address");
		}


		// Check name parameter to make sure it is not null or ""
		// If it is not a valid name, set errMsg (see video) and return false
		// Repeat this check for address parameter

		// Create a Customer object and add to array list
		customers.add(new Customer(generateCustomerId(),name, address));

	}

	public ProductOrder shipOrder(String orderNumber)
	{
		// Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
		// and return false
		boolean orderFound= false; 
		ProductOrder order = null;
		for (ProductOrder p: orders) 
		{
			if (p.getOrderNumber().equals(orderNumber)) 
			{
				orderFound= true;
				order = p;
				break;
			}
		}
		if (orderFound == false) 
		{
			throw new InvalidOrderNumberException ("Order number "+orderNumber +" is invalid");
		}
		orders.remove(order);
		shippedOrders.add(order);
		// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
		// return a reference to the order
		return order;
	}

	/*
	 * Cancel a specific order based on order number
	 */
	public void cancelOrder(String orderNumber)
	{	

		// Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
		// and return false
		ProductOrder order=null;
		boolean pFound = false ;
		for (ProductOrder p: orders ) 
		{
			if(p.getOrderNumber().equals(orderNumber)) 
			{
				order = p;
				pFound = true;
				break;
			}
		}
		if (pFound == false) 
		{
			throw new InvalidOrderNumberException ("Order number "+orderNumber +" is invalid");
		}
		orders.remove(order);

	}

	// Sort products by increasing price

	public void sortByPrice()
	{
		ArrayList<Product> copiedProducts = new ArrayList<Product>();
		Set<String> keySet = products.keySet();
		for (String key : keySet)
		{
			Product value =products.get(key);
			copiedProducts.add(value);

		}
		Collections.sort( copiedProducts, new ProductPriceComparator() );
		for (Product p : copiedProducts)
			p.print();
	}


	// Sort products alphabetically by product name
	public void sortByName()
	{

		ArrayList<Product> copiedProducts = new ArrayList<Product>();
		Set<String> keySet = products.keySet();
		for (String key : keySet)
		{
			Product value =products.get(key);
			copiedProducts.add(value);

		}


		Collections.sort( copiedProducts, new ProductNameComparator() );
		for (Product p : copiedProducts)
			p.print();
	}


	// Sort products alphabetically by product name
	public void sortCustomersByName()
	{

		ArrayList<Customer> copiedCusts = new ArrayList<Customer>();
		copiedCusts.addAll(customers);
		Collections.sort(copiedCusts);
		for (Customer c : copiedCusts)
			c.print();


	}

	public void calculateStats(String id) 
	{		
		boolean keyFound= false;
		Set<String> keySet = stats.keySet();
		for (String key : keySet)
		{
			int value =stats.get(key);
			if(key.equals(id))
			{
				value ++;
				stats.put(id, value);
				keyFound= true;
			}
		}
		if (keyFound==false) 
		{
			stats.put(id, 1);
		}
		/*
		Set<String> Keys = stats.keySet();
		for (String key : Keys)
		{
			System.out.println(stats.get(key)+" "+ key);
		}
		 */

	}

	public void printStats() 
	{
		Product prods = null;
		ValueComparator bvc = new ValueComparator(stats);
		TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
		sorted_map.putAll(stats);
		Set<String> Keys = sorted_map.keySet();
		for (String key : Keys)
		{
			int value = stats.get(key);
			Set<String> ProductId = products.keySet();
			for(String id : ProductId) 
			{
				if(id.equals(key))
				{
					prods = products.get(id);
					System.out.println("Product ID: "+ prods.getId()+ " Product Name: "+prods.getCategory()+" "+prods.getName()+" Number of Product Ordered: "+value);

				}

			}

		}
		System.out.println(sorted_map);
	}

	class ValueComparator implements Comparator<String> 
	{
		Map<String, Integer> base;
		public ValueComparator(Map<String, Integer> stats)
		{
			this.base = stats;
		}
		// Note: this comparator imposes orderings that are inconsistent with
		// equals.
		public int compare(String a, String b) 
		{
			if (base.get(a) >= base.get(b)) 
			{
				return -1;
			} else {
				return 1;
			} // returning 0 would merge keys
		}

	}
	class ProductNameComparator implements Comparator<Product>
	{
		public int compare(Product a, Product b) 
		{
			if (a.getName().compareTo(b.getName())>0)
				return 1;
			else if (a.getName().compareTo(b.getName())<0)
				return -1;
			else
				return 0;
		}
	}

	class ProductPriceComparator implements Comparator<Product>
	{
		public int compare(Product a, Product b)
		{
			if(a.getPrice()<b.getPrice()) return -1;
			if(a.getPrice()>b.getPrice()) return 1;
			return 0;

		}
		class CustomerNameComparator implements Comparator<Customer>
		{
			public int compare(Customer a, Customer b) 
			{
				if (a.getName().compareTo(b.getName())>0)
					return 1;
				else if (a.getName().compareTo(b.getName())<0)
					return -1;
				else
					return 0;
			}
		}
	}
}

class UnknownCustomerException extends RuntimeException
{
	public UnknownCustomerException() {}
	public UnknownCustomerException(String message ) {

		super(message);
	}
}
class UnknownProductException extends RuntimeException
{
	public UnknownProductException(){}
	public UnknownProductException(String message ) {

		super(message);
	}
}

class InvalidProductOptionsException extends RuntimeException
{
	public InvalidProductOptionsException(){}
	public InvalidProductOptionsException(String message ) {

		super(message);
	}
}

class ProductOutOfStockException extends RuntimeException
{
	public ProductOutOfStockException() {}
	public ProductOutOfStockException(String message ) {

		super(message);
	}
}
class InvalidCustomerNameException extends RuntimeException
{
	public InvalidCustomerNameException(){}
	public InvalidCustomerNameException(String message ) {

		super(message);
	}
}

class InvalidCustomerAddressException extends RuntimeException
{
	public InvalidCustomerAddressException() {}
	public InvalidCustomerAddressException(String message ) {

		super(message);
	}
}


class InvalidOrderNumberException extends RuntimeException
{
	public InvalidOrderNumberException() {}
	public InvalidOrderNumberException(String message ) 
	{

		super(message);
	}
}
