
//Aleena Siddiqui
//501101812

/* A Shoes IS A product that has additional information - e.g. size, color

 	 A shoes also comes with different choices ("Brown 6", "Black 9", etc)

 	 The options is specified as a specific "stock type" in get/set/reduce stockCount methods.

 */
public class Shoes extends Product
{
	private String size;
	private String colour;
	int StockBL6, StockBL7, StockBL8, StockBL9, StockBL10;
	int StockBR6, StockBR7, StockBR8, StockBR9, StockBR10;
	int blackStock;
	int brownStock;

	public Shoes(String name, String id, double price, int blackStock, int brownStock)
	{ 
		// Make use of the constructor in the super class Product. Initialize additional Book instance variables. 
		// Set category to BOOKS 
		super("Shoes", id, price,100000, Product.Category.SHOES);

		this.StockBL10=blackStock; //10
		this.StockBL9=blackStock;
		this.StockBL8=blackStock;
		this.StockBL7=blackStock;
		this.StockBL6=blackStock;

		this.StockBR10=brownStock;
		this.StockBR9=brownStock;
		this.StockBR8=brownStock;
		this.StockBR7=brownStock;
		this.StockBR6=brownStock;
	}

	// Check if a valid options  
	public boolean validOptions(String productOptions)
	{
		// check productOptions for "Black 6" or "Brown 6" etc 
		// if it is one of these, return true, else return false
		if ((productOptions.substring(0,5).equalsIgnoreCase("Brown")||productOptions.substring(0,5).equalsIgnoreCase("Black"))&&(productOptions.substring(6).equals("6")||productOptions.substring(6).equals("7")||productOptions.substring(6).equals("8")||productOptions.substring(6).equals("9")||productOptions.substring(6).equals("10")))
			return true;
		return false;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String productOptions) {
		this.size = productOptions.substring(6);
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String productOptions) {
		this.colour = productOptions.substring(0,6);
	}

	// Override getStockCount() in super class.
	public int getStockCount(String productOptions)
	{	//Checks all the black shoe sizes and get stockCount
		if(productOptions.equalsIgnoreCase("Black 6"))
			return StockBL6;
		else if(productOptions.equalsIgnoreCase("Black 7"))
			return StockBL7;
		else if(productOptions.equalsIgnoreCase("Black 8"))
			return StockBL8;
		else if(productOptions.equalsIgnoreCase("Black 9"))
			return StockBL9;
		else if(productOptions.equalsIgnoreCase("Black 10"))
			return StockBL10;

		//checks all the brown shoes sizes and get stockCount
		else if(productOptions.equalsIgnoreCase("Brown 6"))
			return StockBR6;
		else if(productOptions.equalsIgnoreCase("Brown 7"))
			return StockBR7;
		else if(productOptions.equalsIgnoreCase("Brown 8"))
			return StockBR8;
		else if(productOptions.equalsIgnoreCase("Brown 9"))
			return StockBR9;
		else 
			return StockBR10;

	}

	public void setStockCount(int stockCount, String productOptions)
	{
		//checks all the black shoes and set stockCount
		if(productOptions.equalsIgnoreCase("Black 6"))
			StockBL6= stockCount;
		else if(productOptions.equalsIgnoreCase("Black 7"))
			StockBL7= stockCount;
		else if(productOptions.equalsIgnoreCase("Black 8"))
			StockBL8= stockCount;
		else if(productOptions.equalsIgnoreCase("Black 9"))
			StockBL9= stockCount;
		else if(productOptions.equalsIgnoreCase("Black 10"))
			StockBL10= stockCount;

		//checks all the brown shoes sizes and set stockCount
		else if(productOptions.equalsIgnoreCase("Brown 6"))
			StockBR6= stockCount;
		else if(productOptions.equalsIgnoreCase("Brown 7"))
			StockBR7= stockCount;
		else if(productOptions.equalsIgnoreCase("Brown 8"))
			StockBR8= stockCount;
		else if(productOptions.equalsIgnoreCase("Brown 9"))
			StockBR9= stockCount;
		else 
			StockBR10= stockCount;



	}


	public void reduceStockCount(String productOptions)
	{	//Checks all the black shoe sizes and reduce stockCount
		if(productOptions.equalsIgnoreCase("Black 6"))
			StockBL6--;
		else if(productOptions.equalsIgnoreCase("Black 7"))
			StockBL7--;
		else if(productOptions.equalsIgnoreCase("Black 8"))
			StockBL8--;
		else if(productOptions.equalsIgnoreCase("Black 9"))
			StockBL9--;
		else if(productOptions.equalsIgnoreCase("Black 10"))
			StockBL10--;

		//checks all the brown shoes sizes and reduce stockCount
		else if(productOptions.equalsIgnoreCase("Brown 6"))
			StockBR6--;
		else if(productOptions.equalsIgnoreCase("Brown 7"))
			StockBR7--;
		else if(productOptions.equalsIgnoreCase("Brown 8"))
			StockBR8--;
		else if(productOptions.equalsIgnoreCase("Brown 9"))
			StockBR9--;
		else 
			StockBR10--;

	}
	/*
	 * Print product information in super class and append Shoes specific information size and colour
	 */
	public void print()
	{

		super.print();
		System.out.print(" Shoe size: 6/7/8/9/10 "+" Colour: Black/Brown"+ colour );
	}
}

