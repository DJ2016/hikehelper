package parser;


public class Product implements Comparable<Product>{

	public Product(){}
	public Product(String name){
		this(name, null);
	}
	
	public Product(String name, String price){
		this(name, price, null);
	}
	
	public Product(String name, String price, String imgsource){
		this.name = name;
		this.price = price;
		this.imgsource = imgsource;
	}
	
	private String name;
	private String price;
	private String imgsource;
	private String productID;
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getImgSource() {
		return imgsource;
	}
	
	public void setImgSource(String imgsource) {
		this.imgsource = imgsource;
	}
	
	public String toString(){
		return name + " " + price + "ð";
	}
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	@Override
	public int compareTo(Product arg0) {
		return Integer.parseInt(getPrice()) - Integer.parseInt(arg0.getPrice());
	}
}
