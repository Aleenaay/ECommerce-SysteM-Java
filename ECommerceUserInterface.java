
//Aleena Siddiqui
//501101812

import java.util.ConcurrentModificationException;
import java.util.InputMismatchException;
// Simulation of a Simple E-Commerce System (like Amazon)
import java.util.Scanner;

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				boolean done = false;
				while(!done)
				{
					try 
					{
						String name = "";
						String address = "";

						System.out.print("Name: ");
						if (scanner.hasNextLine())
							name = scanner.nextLine();

						System.out.print("\nAddress: ");
						if (scanner.hasNextLine())
							address = scanner.nextLine();

						amazon.createCustomer(name, address);
						done = true;
					}
					catch (InvalidCustomerNameException e) 
					{
						System.out.println(e);
					}
					catch(InvalidCustomerAddressException e) 
					{
						System.out.println(e);
					}
				}
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
				boolean done = false;
				while(!done) 
				{
					try {
						String orderNumber = "";
						System.out.print("Order Number: ");
						orderNumber = scanner.next();
						// Get order number from scanner
						// Ship order to customer (see ECommerceSystem for the correct method to use
						amazon.shipOrder(orderNumber);
						System.out.println("Order sucessfully shipped ");
						done = true;
					}
					catch(InvalidOrderNumberException e) 
					{
						System.out.println(e);
					}
				}
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				boolean done = false;
				while(!done) 
				{
					try 
					{
						String customerId = "";

						System.out.print("Customer Id: ");
						// Get customer Id from scanner
						customerId = scanner.next();
						// Print all current orders and all shipped orders for this customerId
						amazon.printOrderHistory(customerId);
						done= true;
					}
					catch (InvalidOrderNumberException e1) 
					{
						System.out.println(e1);
					}
					catch (UnknownCustomerException e2) 
					{
						System.out.println(e2);
					}
				}
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				boolean done = false;
				while(!done) 
				{
					try
					{
						String productId = "";
						String customerId = "";

						System.out.print("Product Id: ");
						productId = scanner.next();

						System.out.print("\nCustomer Id: ");
						customerId = scanner.next();
						// Get customer Id from scanner

						// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
						String success =amazon.orderProduct(productId, customerId, "");

						System.out.println("Order number is :"+success);
						done= true;
					}
					catch(UnknownCustomerException e1) 
					{
						System.out.println(e1);
					}
					catch(UnknownProductException e2) 
					{
						System.out.println(e2);
					}
					catch(InvalidProductOptionsException e3) 
					{
						System.out.println(e3);
					}
					catch(ProductOutOfStockException e4) 
					{
						System.out.println(e4);
					}
					// Print Order Number string returned from method in ECommerceSystem
				}
			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{

				boolean done = false;

				while(!done) 
				{
					try
					{
						String productId = "";
						String customerId = "";
						String options = "";

						System.out.print("Product Id: ");
						// get product id
						productId = scanner.next();
						System.out.print("\nCustomer Id: ");
						// get customer id
						customerId = scanner.next();
						System.out.print("\nFormat [Paperback Hardcover EBook]: ");
						// get book format and store in options string
						options = scanner.next();
						String success = amazon.orderProduct(productId, customerId, options) ;

						System.out.println("Your Order number is : "+success);
						done = true;
					}
					// Order product. Check for error message set in ECommerceSystem

					// Print order number string if order number is not null
					catch(UnknownCustomerException e1) 
					{
						System.out.println(e1);
					}
					catch(UnknownProductException e2) 
					{
						System.out.println(e2);
					}
					catch(InvalidProductOptionsException e3) 
					{
						System.out.println(e3);
					}
					catch(ProductOutOfStockException e4) 
					{
						System.out.println(e4);
					}
				}
			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
			{
				boolean done = false;
				while(!done) 
				{
					try
					{
						String productId = "";
						String customerId = "";
						String options = "";

						System.out.print("Product Id: ");
						productId = scanner.next();
						// get product id

						System.out.print("\nCustomer Id: ");
						customerId= scanner.next();
						// get customer id

						System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
						// get shoe size and store in options	
						String size=scanner.next();
						System.out.print("\nColor: \"Black\" \"Brown\": ");
						String color= scanner.next();
						// get shoe color and append to options
						options = color+" "+ size;
						//order shoes
						String success = amazon.orderProduct(productId, customerId, options) ;
						System.out.println("Your Order number is : "+success);
						done = true;
					}
					catch(UnknownCustomerException e1) 
					{
						System.out.println(e1);
					}
					catch(UnknownProductException e2) 
					{
						System.out.println(e2);
					}
					catch(InvalidProductOptionsException e3) 
					{
						System.out.println(e3);
					}
					catch(ProductOutOfStockException e4) 
					{
						System.out.println(e4);
					}
				}
			}


			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				boolean done = false;
				while(!done) 
				{
					try
					{
						String orderNumber = "";

						System.out.print("Order Number: ");
						orderNumber = scanner.next();
						// get order number from scanner
						amazon.cancelOrder(orderNumber);
						System.out.println("Order "+orderNumber+" succesfully cancelled");

						// cancel order. Check for error
						done = true;
					}
					catch (InvalidOrderNumberException e) 
					{
						System.out.println(e);
					}
				}
			}
			else if (action.equalsIgnoreCase("SORTBYPRICE")) // sort products by price
			{
				amazon.sortByPrice();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.sortByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			else if (action.equalsIgnoreCase("ADDTOCART")) 
			{
				boolean done = false;
				while(!done) 
				{
					try
					{
						System.out.println("Customer ID: ");
						String customerID= scanner.next();
						System.out.println("Product ID:");
						String productID = scanner.next();
						System.out.println("Product Options (IF NO PRODUCT OPTION TYPE null :" );
						String options = scanner.next();
						System.out.println("Quantity: ");
						int quantity = scanner.nextInt();
						amazon.AddToCart(productID, customerID, options, quantity);
						done = true;
					}
					catch(UnknownCustomerException e1) 
					{
						System.out.println(e1);
					}
					catch(UnknownProductException e2) 
					{
						System.out.println(e2);
					}
					catch(InvalidProductOptionsException e3) 
					{
						System.out.println(e3);
					}
					catch(ProductOutOfStockException e4) 
					{
						System.out.println(e4);
					}
					catch(InputMismatchException e) 
					{
						System.out.println("Please enter integer");
					}
				}
			}
			else if (action.equalsIgnoreCase("PRINTCART"))
			{
				boolean done = false;
				while(!done) 
				{
					try
					{
						System.out.println("Customer ID: ");
						String customerID= scanner.next();
						amazon.PrintCart(customerID);
						done = true;

					}
					catch(UnknownCustomerException e1) 
					{
						System.out.println(e1);
					}
				}
			}
			else if(action.equalsIgnoreCase("REMCARTITEM")) 
			{
				boolean done = false;
				while(!done) 
				{
					try
					{
						System.out.println("Customer ID: ");
						String customerId = scanner.next();
						System.out.println("Product Id: ");
						String productId= scanner.next();
						amazon.RemCartItem(customerId, productId);
						done = true;
					}
					catch(UnknownCustomerException e1) 
					{
						System.out.println(e1);
					}
					catch(UnknownProductException e2) 
					{
						System.out.println(e2);
					}
				}
			}
			else if (action.equalsIgnoreCase("ORDERITEMS"))
			{
				boolean done = false;
				while(!done) 
				{
					try 
					{
						System.out.println("Customer ID: ");
						String customerID= scanner.next();
						amazon.OrderItems(customerID);
						done = true;
						amazon.EmptyCart(customerID);
					}
					catch (UnknownCustomerException e1) 
					{
						System.out.println(e1);
					}
					catch(ConcurrentModificationException e) 
					{
						done= true;
					}
					
				}
			}
			else if (action.equalsIgnoreCase("STATS")) 
			{
				amazon.printStats();
			}
			System.out.print("\n>");
		}
	}
}
