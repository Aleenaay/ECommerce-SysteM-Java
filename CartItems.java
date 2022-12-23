//Aleena Siddiqui
public class CartItems {
	private Product prods;
	private String productOptions;
	private int count;


	public CartItems(Product prods, String productOptions, int count) 
	{
		this.prods=prods;
		this.productOptions= productOptions;
		this.count=count;
	}

	public Product getProds() {
		return prods;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int c) {
		this.count= c;
	}

	public void increaseCount( String productOptions) 
	{
		count++;
	}
	public void setProds(Product prods) {
		this.prods = prods;
	}

	public String getProductOptions() {
		return productOptions;
	}

	public void setProductOptions(String productOptions) {
		this.productOptions = productOptions;
	}
	public void print() 
	{
		if (this.productOptions == "null"|| this.productOptions == null) 
		{
			this.productOptions = " ";
		}

		System.out.println(" Product ID: "+ prods.getId()+ " Product Name: "+prods.getCategory()+" "+prods.getName()+" Options: "+ productOptions+ " Number of products: "+ count);	
	}
	public boolean equals(CartItems other) 
	{
		return this.prods.equals(other.prods);
	}
}
