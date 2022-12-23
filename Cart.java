//Aleena Siddiqui
public class Cart {
	private String 		orderNumber;
	private Customer 	customer;
	private CartItems items;

	public Cart(String orderNumber, Customer customer,  CartItems items)
	{
		this.orderNumber = orderNumber;
		this.items=items;
		this.customer = customer;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CartItems getItems() {
		return items;
	}

	public void setItems(CartItems items) {
		this.items = items;
	}
	public void output()
	{
	items.print();
	}
	public void print()
	{
		System.out.print("Order #"+ this.orderNumber);
	items.print();
	}
}
